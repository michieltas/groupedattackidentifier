package com.mt.attack.predefinedstrings;

public enum OperatingSystems {
	WINDOWS, ANDROID, IOS, LINUX;
	// etc etc

	/**
	 * Find an os by name, which has to be the same as an enum literal.
	 * 
	 * @param name
	 *            String
	 * @return OperatingSystems literal, if found; null if not found.
	 */
	public static final OperatingSystems findOS(String os) {
		for (OperatingSystems literal : OperatingSystems.values()) {
			if (literal.toString().equals(os)) {
				return literal;
			}
		}
		return null;
	}
}
