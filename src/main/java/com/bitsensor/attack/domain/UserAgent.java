
package com.bitsensor.attack.domain;

import com.bitsensor.attack.predefinedstrings.OperatingSystems;

public class UserAgent {

	private String name;
	private String version;

	// operating system
	private OperatingSystems os;

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            String
	 * @param version
	 *            String
	 * @param os
	 *            String
	 */
	public UserAgent(String name, String version, OperatingSystems os) {
		super();
		this.name = name;
		this.version = version;
		this.os = os;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public OperatingSystems getOs() {
		return os;
	}

	public void setOs(OperatingSystems os) {
		this.os = os;
	}

	@Override
	public String toString() {
		return "UserAgent [name=" + name + ", version=" + version + ", os=" + os + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((os == null) ? 0 : os.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		UserAgent other = (UserAgent) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (os != other.os)
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

}
