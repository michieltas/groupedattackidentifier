package com.mt.attack.create;

import com.mt.attack.domain.Attack;
import com.mt.attack.domain.AttackType;
import com.mt.attack.domain.Attacker;
import com.mt.attack.domain.IPaddress;
import com.mt.attack.domain.UserAgent;
import com.mt.attack.predefinedstrings.OperatingSystems;
import com.mt.attack.predefinedstrings.PredefinedAgents;

/**
 * Builds from raw data an attack instance in a convenient way.
 * 
 * @author MT
 *
 */
public final class AttackInstanceBuilder {

	private AttackInstanceBuilder() {
		// empty
	}

	/**
	 * Builder function.
	 * 
	 * @param attackType
	 *            AttackType
	 * @param ipAddress
	 *            as String
	 * @param nameOfAgent
	 *            See values() of PredefinedAgents
	 * @param version
	 *            as String
	 * @param os
	 *            The operating system as String
	 * @param label
	 *            String, just a name for the printing
	 * @param timestamp
	 *            A long, like the good old Date object
	 * 
	 * @return Attack with all fields according to above input.
	 */
	public static Attack buildAttackInstance(AttackType attackType, String ipAddress, PredefinedAgents nameOfAgent,
			String version, OperatingSystems os, String label, Long timestamp) {
		UserAgent userAgent = new UserAgent(nameOfAgent.toString(), version, os);
		IPaddress ip = new IPaddress(ipAddress);
		Attacker attacker = new Attacker(ip, userAgent);

		return new Attack(attacker, attackType, timestamp, label);
	}

	/**
	 * Builder function.
	 * 
	 * @param attackType
	 *            AttackType
	 * @param ipAddress
	 *            as String
	 * @param nameOfAgent
	 *            See values() of PredefinedAgents
	 * @param version
	 *            as String
	 * @param os
	 *            The operating system as String
	 * @param label
	 *            String, just a name for the printing
	 * 
	 * @return Attack with all fields according to above input.
	 */
	public static Attack buildAttackInstance(AttackType attackType, String ipAddress, PredefinedAgents nameOfAgent,
			String version, OperatingSystems os, String label) {

		return buildAttackInstance(attackType, ipAddress, nameOfAgent, version, os, label, null);
	}

	/**
	 * 
	 * @param differentTimestamp
	 *            long
	 * @param repeatedAttack
	 *            Attack which already occurred, but this time it occurs again with
	 *            different time stamp.
	 * @return Attack
	 */
	public static Attack attackRepetition(long differentTimestamp, Attack repeatedAttack) {
		return buildAttackInstance(repeatedAttack.getAttackType(),
				repeatedAttack.getAttacker().getIpAddress().toString(),
				PredefinedAgents.findPredefinedAgents(repeatedAttack.getAttacker().getUserAgent().getName()),
				repeatedAttack.getAttacker().getUserAgent().getVersion(),
				repeatedAttack.getAttacker().getUserAgent().getOs(), "anonymous", differentTimestamp);
	}
}
