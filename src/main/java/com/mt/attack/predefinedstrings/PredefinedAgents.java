package com.mt.attack.predefinedstrings;

import java.util.ArrayList;
import java.util.List;

import com.mt.attack.domain.UserAgent;

/**
 * Better to put possible combinations of agents, operating systems and versions
 * inside a database, but for now we simplify to an enumeration. Point is to
 * check whether the mentioned combinations would be possible, or if the user
 * agent has been faked, visible by an impossible combination.
 * 
 * @author MT
 *
 */
public enum PredefinedAgents {
	ANDROID, EDGE, IE, GECKO, SAFARI, GOOGLEBOT, C_URL, PYTHON_URLLIB, UNKNOWN;

	public static String[] getValidVersions(PredefinedAgents agent) {
		switch (agent) {
		case ANDROID:
			return new String[] { "8.0", "8.1", "9.0" }; // more versions are possible but to mention a few...

		case EDGE:
			return new String[] { "14", "15" }; // more versions are possible but to mention a few...

		case IE:
			return new String[] { "10", "11" }; // more versions are possible but to mention a few...

		case GECKO:
			return new String[] { "68", "69" }; // more versions are possible but to mention a few...

		case SAFARI:
			return new String[] { "70", "71", "72" }; // more versions are possible but to mention a few...

		case GOOGLEBOT:
			return new String[] { "1.0", "2.1" }; // more versions are possible but to mention a few...

		case C_URL:
			return new String[] { "7.61.0", "7.62.0" }; // more versions are possible but to mention a few...

		case PYTHON_URLLIB:
			return new String[] { "3.8", "3.7.1" }; // more versions are possible but to mention a few...

		default:
			return new String[] {};
		}
	}

	public static String[] getValidOperatingSystems(PredefinedAgents agent) {
		switch (agent) {
		case ANDROID:
			return new String[] { OperatingSystems.ANDROID.toString() };

		case EDGE:
			return new String[] { OperatingSystems.WINDOWS.toString() };

		case IE:
			return new String[] { OperatingSystems.WINDOWS.toString() };

		case GECKO:
			return new String[] { OperatingSystems.WINDOWS.toString(), OperatingSystems.ANDROID.toString(),
					OperatingSystems.IOS.toString(), OperatingSystems.LINUX.toString() };

		case SAFARI:
			return new String[] { OperatingSystems.WINDOWS.toString(), OperatingSystems.ANDROID.toString(),
					OperatingSystems.IOS.toString(), OperatingSystems.LINUX.toString() };

		case C_URL:
			return new String[] { OperatingSystems.WINDOWS.toString(), OperatingSystems.ANDROID.toString(),
					OperatingSystems.IOS.toString(), OperatingSystems.LINUX.toString() };

		case PYTHON_URLLIB:
			return new String[] { OperatingSystems.WINDOWS.toString(), OperatingSystems.ANDROID.toString(),
					OperatingSystems.IOS.toString(), OperatingSystems.LINUX.toString() };

		default:
			return new String[] {};

		}
	}

	/**
	 * Provides an example list of possible user agents; in reality, some
	 * combinations will not be possible, but for now this simplification.
	 * 
	 * @return List<UserAgent>
	 */
	public static List<UserAgent> getExistingUserAgents() {
		List<UserAgent> listOfPossibleAgents = new ArrayList<>();

		for (PredefinedAgents agent : PredefinedAgents.values()) {
			for (String version : PredefinedAgents.getValidVersions(agent)) {
				for (String os : PredefinedAgents.getValidOperatingSystems(agent)) {
					UserAgent userAgent = new UserAgent(agent.name(), version, OperatingSystems.findOS(os));
					// System.out.println(userAgent.toString());
					listOfPossibleAgents.add(userAgent);
				}
			}
		}

		return listOfPossibleAgents;
	}

	/**
	 * Find an agent by name, which has to be the same as an enum literal.
	 * 
	 * @param name
	 *            String
	 * @return PredefinedAgents literal, if found; null if not found.
	 */
	public static PredefinedAgents findPredefinedAgents(String name) {
		for (PredefinedAgents agent : PredefinedAgents.values()) {
			if (agent.toString().equals(name)) {
				return agent;
			}
		}
		return null;
	}
}
