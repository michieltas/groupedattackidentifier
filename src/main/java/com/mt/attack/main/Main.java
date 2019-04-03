package com.mt.attack.main;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.EventTimeSessionWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import com.mt.attack.create.AttacksGenerator;
import com.mt.attack.domain.Attack;
import com.mt.attack.domain.AttackType;
import com.mt.attack.domain.Attacker;
import com.mt.attack.domain.GroupedAttack;

/**
 * Grouping attacks from a stream.
 *
 */
public class Main {
	private static Logger logger = Logger.getLogger(Main.class.getName());

	private static AscendingTimestampExtractor<Tuple4<Attacker, AttackType, Long, String>> timestampAndWatermarkAssigner = new AscendingTimestampExtractor<Tuple4<Attacker, AttackType, Long, String>>() {
		private static final long serialVersionUID = 5226835842597175034L;

		@Override
		public long extractAscendingTimestamp(Tuple4<Attacker, AttackType, Long, String> element) {
			return element.f2 == null ? 0L : element.f2;
		}
	};

	/**
	 * <code>
	 * Keyed Windows stream
	 *  .keyBy(...)          			    <-  keyed versus non-keyed windows: this program uses keyed
	 *  .window(...)   	   		            <-  required: "assigner"
	 * [.allowedLateness(...)]    			<-  optional: "lateness" (this program uses lateness indeed - because we can)
	 *  .reduce/aggregate/fold/apply()      <-  required: "function" (this program uses apply)
	 * </code>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// create the stream execution environment
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

		// "generate" for demo purposes the list attacks
		List<Tuple4<Attacker, AttackType, Long, String>> attacks = new ArrayList<>();
		new AttacksGenerator(attacks).generateDemoAttacks();

		// convert the list of attacks into a stream
		SingleOutputStreamOperator<Tuple4<Attacker, AttackType, Long, String>> source = env.fromCollection(attacks)
				.assignTimestampsAndWatermarks(timestampAndWatermarkAssigner);

		// use keyed stream
		KeyedStream<Tuple4<Attacker, AttackType, Long, String>, Tuple> keyed = source.keyBy(0);

		// put the windows using a gap to determine the end of the window (and the
		// beginning of the next window)
		WindowedStream<Tuple4<Attacker, AttackType, Long, String>, Tuple, TimeWindow> windowedStream = keyed
				.window(EventTimeSessionWindows.withGap(Time.minutes(1L))).allowedLateness(Time.seconds(1L));

		// apply the apply function to collect the stream of attackers and types into a
		// stream of groupedAttack
		SingleOutputStreamOperator<GroupedAttack> outputStream = apply(windowedStream);

		try {
			// print the stream to the console
			outputStream.print();

			// not to forget: kick off the environment execution
			env.execute();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}

	private static SingleOutputStreamOperator<GroupedAttack> apply(
			WindowedStream<Tuple4<Attacker, AttackType, Long, String>, Tuple, TimeWindow> windowedStream) {
		return windowedStream.apply(
				new WindowFunction<Tuple4<Attacker, AttackType, Long, String>, GroupedAttack, Tuple, TimeWindow>() {

					private static final long serialVersionUID = -372437077379964323L;

					@Override
					public void apply(Tuple key, TimeWindow window,
							Iterable<Tuple4<Attacker, AttackType, Long, String>> input, Collector<GroupedAttack> out)
							throws Exception {
						GroupedAttack group = new GroupedAttack();
						for (Tuple4<Attacker, AttackType, Long, String> record : input) {
							group.getAttacks().add(new Attack(record.f0, record.f1, record.f2, record.f3));
							group.getAttackers().add(record.f0);
							group.getAttackTypes().add(record.f1);
						}
						out.collect(group);

					}

				});
	}

}
