package com.mt.attack.domain;

import com.mt.attack.domain.IPaddress;
import com.mt.attack.predefinedstrings.ExceptionMessages;

import junit.framework.TestCase;

public class IPadressTest extends TestCase {

	public void testIPaddressSuccessfulCreation() {
		// given
		IPaddress ipAddress1;
		IPaddress ipAddress2;

		// when
		ipAddress1 = new IPaddress(new Integer[] { 255, 255, 255, 0 });
		ipAddress2 = new IPaddress("255.255.255.0");

		// then
		assertNotNull(ipAddress1);
		assertNotNull(ipAddress2);
	}

	public void testIpaddressCreationFailure1() {
		// given
		IPaddress ipAddress = null;

		try {
			// when 3 arguments instead of 4 (wrong nr of arguments)
			ipAddress = new IPaddress(new Integer[] { 255, 255, 255 });

			// then
			fail(); // remember this line, else 'may' false positive
		} catch (IllegalArgumentException e) {
			// then
			assertEquals(e.getMessage(), ExceptionMessages.FOUR_ARGS_REQUIRED);
			assertNull(ipAddress);
		}
	}

	public void testIpaddressCreationFailure2() {
		// given
		IPaddress ipAddress = null;

		try {
			// when 3 arguments instead of 4 (wrong nr of arguments)
			ipAddress = new IPaddress("255.255.255");

			// then
			fail(); // remember this line, else 'may' false positive
		} catch (IllegalArgumentException e) {
			// then
			assertEquals(e.getMessage(), ExceptionMessages.FOUR_ARGS_REQUIRED);
			assertNull(ipAddress);
		}
	}

	public void testIpaddressCreationFailure3() {
		// given
		IPaddress ipAddress = null;

		try {
			// when 3 arguments instead of 4 (wrong nr of arguments)
			ipAddress = new IPaddress(new Integer[] { 255, 255, 256, 0 });

			// then
			fail(); // remember this line, else 'may' false positive
		} catch (IllegalArgumentException e) {
			// then
			assertEquals(e.getMessage(), ExceptionMessages.RANGE_FROM_0_TO_255);
			assertNull(ipAddress);
		}
	}

	public void testIpaddressCreationFailure4() {
		// given
		IPaddress ipAddress = null;

		try {
			// when 3 arguments instead of 4 (wrong nr of arguments)
			ipAddress = new IPaddress("255.255.256.0");

			// then
			fail(); // remember this line, else 'may' false positive
		} catch (IllegalArgumentException e) {
			// then
			assertEquals(e.getMessage(), ExceptionMessages.RANGE_FROM_0_TO_255);
			assertNull(ipAddress);
		}
	}

	public void testIpaddressIsJibberish1() {
		// given
		IPaddress ipAddress = null;

		try {
			// when 3 arguments instead of 4 (wrong nr of arguments)
			ipAddress = new IPaddress("no dots");

			// then
			fail(); // remember this line, else 'may' false positive
		} catch (IllegalArgumentException e) {
			// then
			assertEquals(e.getMessage(), ExceptionMessages.MUST_CONTAIN_DOTS);
			assertNull(ipAddress);
		}
	}

	public void testIpaddressIsJibberish2() {
		// given
		IPaddress ipAddress = null;

		try {
			// when 3 arguments instead of 4 (wrong nr of arguments)
			ipAddress = new IPaddress("this.is.not.address");

			// then
			fail(); // remember this line, else 'may' false positive
		} catch (NumberFormatException e) {
			// then
			assertNull(ipAddress);
		}
	}
}
