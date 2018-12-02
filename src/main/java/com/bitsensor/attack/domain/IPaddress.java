package com.bitsensor.attack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bitsensor.attack.predefinedstrings.ExceptionMessages;

public class IPaddress {

	private Integer[] partsOfAddress = new Integer[] { null, null, null, null };

	/**
	 * Constructor.
	 * 
	 * @param partsOfAddress
	 *            Integer[] with length 4 (if not length 4 =>
	 *            illegalArgumentException ).
	 * 
	 */
	public IPaddress(Integer[] partsOfAddress) {
		super();

		this.partsOfAddress = performNumberChecks(partsOfAddress);
	}

	/**
	 * Constructor.
	 * 
	 * @param ipAddress
	 *            String with content, content must have length 4 if split on '.'.
	 * 
	 */
	public IPaddress(String ipAddress) {
		super();

		List<Integer> parts = performChecksOnIpAddressString(ipAddress);

		this.partsOfAddress = performNumberChecks(parts.toArray(new Integer[] { 0 }));
	}

	private List<Integer> performChecksOnIpAddressString(String ipAddress) {
		// identify parts in between the dots and make sure these are numbers

		List<Integer> parts = new ArrayList<>();
		if (ipAddress == null || !ipAddress.contains(".")) {
			throw new IllegalArgumentException(ExceptionMessages.MUST_CONTAIN_DOTS);
		} else {
			String[] nrs = ipAddress.split("\\.");
			for (String nr : nrs) {
				parts.add(Integer.parseInt(nr));
			}
		}
		return parts;
	}

	public Integer[] getParts() {
		return partsOfAddress;
	}

	public void setParts(Integer[] partsOfAddress) {
		this.partsOfAddress = performNumberChecks(partsOfAddress);
	}

	public Integer getFirstPart() {
		return partsOfAddress[0];
	}

	public Integer getSecondPart() {
		return partsOfAddress[1];
	}

	public Integer getThirdPart() {
		return partsOfAddress[2];
	}

	public Integer getFourthPart() {
		return partsOfAddress[3];
	}

	private Integer[] performNumberChecks(Integer[] parts) {

		checkRequiredNrOfArgumentsIs4(parts);
		checkRangeOfArgumentsIsBetween0And255(parts);

		return parts;
	}

	private void checkRequiredNrOfArgumentsIs4(Integer[] parts) {
		if (parts == null || parts.length != 4) {
			throw new IllegalArgumentException(ExceptionMessages.FOUR_ARGS_REQUIRED);
		}
	}

	private void checkRangeOfArgumentsIsBetween0And255(Integer[] parts) {
		for (Integer s : parts) {
			if (s == null || s < 0 || s > 255) {
				throw new IllegalArgumentException(ExceptionMessages.RANGE_FROM_0_TO_255);
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(partsOfAddress);
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
		IPaddress other = (IPaddress) obj;
		if (!Arrays.equals(partsOfAddress, other.partsOfAddress))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return partsOfAddress[0] + "." + partsOfAddress[1] + "." + partsOfAddress[2] + "." + partsOfAddress[3];
	}

}
