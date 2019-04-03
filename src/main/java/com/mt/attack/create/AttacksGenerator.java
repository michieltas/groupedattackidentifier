package com.mt.attack.create;

import java.util.Date;
import java.util.List;

import org.apache.flink.api.java.tuple.Tuple4;

import com.mt.attack.domain.Attack;
import com.mt.attack.domain.AttackType;
import com.mt.attack.domain.Attacker;
import com.mt.attack.predefinedstrings.OperatingSystems;
import com.mt.attack.predefinedstrings.PredefinedAgents;

public class AttacksGenerator {
	private List<Tuple4<Attacker, AttackType, Long, String>> attacks;

	/**
	 * Constructor.
	 * 
	 * @param attacks
	 *            List<Tuple4<Attacker, AttackType, Long, String>>
	 */
	public AttacksGenerator(List<Tuple4<Attacker, AttackType, Long, String>> attacks) {
		this.attacks = attacks;
	}

	/**
	 * Function which manually generates a list of attacks. Every attack has a
	 * unique label, and the software does not use a label other than for printing
	 * purposes.
	 */
	public void generateDemoAttacks() {
		Attack attackA1 = AttackInstanceBuilder.buildAttackInstance(AttackType.DENIAL_OF_SERVICE, "123.123.123.123",
				PredefinedAgents.PYTHON_URLLIB, "3.7.1", OperatingSystems.WINDOWS, "A1");
		Attack attackA2 = AttackInstanceBuilder.buildAttackInstance(AttackType.DENIAL_OF_SERVICE, "123.123.123.123",
				PredefinedAgents.PYTHON_URLLIB, "3.7.1", OperatingSystems.WINDOWS, "A2");

		// different attack group due to more than 60 seconds delay (see below
		// assignment of time)
		Attack attackB1 = AttackInstanceBuilder.buildAttackInstance(AttackType.DENIAL_OF_SERVICE, "123.123.123.123",
				PredefinedAgents.PYTHON_URLLIB, "3.7.1", OperatingSystems.WINDOWS, "B1");
		Attack attackB2 = AttackInstanceBuilder.buildAttackInstance(AttackType.DENIAL_OF_SERVICE, "123.123.123.123",
				PredefinedAgents.PYTHON_URLLIB, "3.7.1", OperatingSystems.WINDOWS, "B2");

		// different attack group due to different IP address
		// Note that although user agents are varying, an attacker is only defined
		// uniquely by it's IP address, hence it's 1 group.
		Attack attackC1 = AttackInstanceBuilder.buildAttackInstance(AttackType.DENIAL_OF_SERVICE, "123.123.123.124",
				PredefinedAgents.GECKO, "3.7.1", OperatingSystems.WINDOWS, "C1");
		Attack attackC2 = AttackInstanceBuilder.buildAttackInstance(AttackType.DENIAL_OF_SERVICE, "123.123.123.124",
				PredefinedAgents.GECKO, "3.7.1", OperatingSystems.WINDOWS, "C2");
		Attack attackC3 = AttackInstanceBuilder.buildAttackInstance(AttackType.PORT_SCANNING, "123.123.123.124",
				PredefinedAgents.PYTHON_URLLIB, "3.7.1", OperatingSystems.WINDOWS, "C3");

		long now = new Date().getTime();

		// 4 attack groups: A, B, C
		attacks.add(Tuple4.of(attackC1.getAttacker(), attackC1.getAttackType(), now + 1000L * 0L, attackC1.getLabel()));
		attacks.add(Tuple4.of(attackA1.getAttacker(), attackA1.getAttackType(), now + 1000L * 1L, attackA1.getLabel()));
		attacks.add(Tuple4.of(attackA2.getAttacker(), attackA2.getAttackType(), now + 1000L * 5L, attackA2.getLabel()));
		attacks.add(
				Tuple4.of(attackC2.getAttacker(), attackC2.getAttackType(), now + 1000L * 11L, attackC2.getLabel()));
		attacks.add(
				Tuple4.of(attackC3.getAttacker(), attackC3.getAttackType(), now + 1000L * 12L, attackC3.getLabel()));
		attacks.add(
				Tuple4.of(attackB1.getAttacker(), attackB1.getAttackType(), now + 1000L * 66L, attackB1.getLabel()));
		attacks.add(
				Tuple4.of(attackB2.getAttacker(), attackB2.getAttackType(), now + 1000L * 81L, attackB2.getLabel()));

	}

}
