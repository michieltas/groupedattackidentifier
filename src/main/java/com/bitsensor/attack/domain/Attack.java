package com.bitsensor.attack.domain;

/**
 * An attack is executed by an attacker, and an attack has a certain type.
 * 
 * @author MT
 *
 */
public class Attack {
	private Attacker attacker;
	private AttackType attackType;
	private Long timestamp;
	private String label; // label does not play a role in the hashCode, equals or the compareTo

	/**
	 * Constructor.
	 * 
	 * @param attacker
	 *            Attacker
	 * @param attackType
	 *            AttackType
	 */
	public Attack(Attacker attacker, AttackType attackType) {
		this(attacker, attackType, null);
	}

	/**
	 * Constructor.
	 * 
	 * @param attacker
	 *            Attacker
	 * @param attackType
	 *            AttackType
	 * @param timestamp
	 *            long
	 */
	public Attack(Attacker attacker, AttackType attackType, Long timestamp) {
		super();
		this.attacker = attacker;
		this.attackType = attackType;
		this.timestamp = timestamp;
	}

	public Attack(Attacker attacker, AttackType attackType, Long timestamp, String label) {
		this(attacker, attackType, timestamp);
		this.label = label;
	}

	public Attacker getAttacker() {
		return attacker;
	}

	public void setAttacker(Attacker attacker) {
		this.attacker = attacker;
	}

	public AttackType getAttackType() {
		return attackType;
	}

	public void setAttackType(AttackType attackType) {
		this.attackType = attackType;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attackType == null) ? 0 : attackType.hashCode());
		result = prime * result + ((attacker == null) ? 0 : attacker.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attack other = (Attack) obj;
		if (attackType != other.attackType)
			return false;
		if (attacker == null) {
			if (other.attacker != null)
				return false;
		} else if (!attacker.equals(other.attacker))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Attack [attacker=" + attacker + ", attackType=" + attackType + ", timestamp=" + timestamp + ", label="
				+ label + "]";
	}

}
