package com.mt.attack.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupedAttack {
	private List<Attack> attacks = new ArrayList<>();
	private Set<AttackType> attackTypes = new HashSet<>();
	private Set<Attacker> attackers = new HashSet<>();

	public List<Attack> getAttacks() {
		return attacks;
	}

	public void setAttacks(List<Attack> attacks) {
		this.attacks = attacks;
	}

	public Set<AttackType> getAttackTypes() {
		return attackTypes;
	}

	public void setAttackTypes(Set<AttackType> attackTypes) {
		this.attackTypes = attackTypes;
	}

	public Set<Attacker> getAttackers() {
		return attackers;
	}

	public void setAttackers(Set<Attacker> attackers) {
		this.attackers = attackers;
	}

	@Override
	public String toString() {
		return "GroupedAttack:\n\rAttacks " + attacks + "\n\rAttackers " + attackers + "\n\rAttackTypes " + attackTypes
				+ "\n\r\n\r";
	}

}
