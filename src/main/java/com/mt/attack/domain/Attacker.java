package com.mt.attack.domain;

/**
 * An attacker consists of an IP address and a user agent.
 * 
 * @author MT
 *
 */
public class Attacker implements Comparable<Attacker> {
	private IPaddress ipAddress;
	private UserAgent userAgent;

	/**
	 * Constructor.
	 * 
	 * @param ipAddress
	 *            IPaddress
	 * @param userAgent
	 *            UserAgent
	 */
	public Attacker(IPaddress ipAddress, UserAgent userAgent) {
		super();
		this.ipAddress = ipAddress;
		this.userAgent = userAgent;
	}

	public IPaddress getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(IPaddress ipAddress) {
		this.ipAddress = ipAddress;
	}

	public UserAgent getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(UserAgent userAgent) {
		this.userAgent = userAgent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ipAddress == null) ? 0 : ipAddress.hashCode());
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
		Attacker other = (Attacker) obj;
		if (ipAddress == null) {
			if (other.ipAddress != null)
				return false;
		} else if (!ipAddress.equals(other.ipAddress))
			return false;
		return true;
	}

	@Override
	public int compareTo(Attacker attacker) {
		return (this.ipAddress.toString() + this.userAgent.toString())
				.compareTo(attacker.getIpAddress().toString() + attacker.userAgent.toString());

	}

	@Override
	public String toString() {
		return "Attacker [ipAddress=" + ipAddress + ", userAgent=" + userAgent + "]";
	}

}
