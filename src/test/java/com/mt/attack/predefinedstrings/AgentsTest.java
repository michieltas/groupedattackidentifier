package com.mt.attack.predefinedstrings;

import java.util.List;

import com.mt.attack.domain.UserAgent;
import com.mt.attack.predefinedstrings.PredefinedAgents;

import junit.framework.Assert;
import junit.framework.TestCase;

public class AgentsTest extends TestCase {

	/**
	 * Simple test to keep track of the nr of combinations.
	 */
	public void testNumberOfCombinations() {
		// given
		int nr = 0;

		// when
		nr = PredefinedAgents.getExistingUserAgents().size();

		// then
		assertEquals(nr, 43);
	}

	/**
	 * Testing that the equals function is working properly.
	 */
	public void testEveryUserAgentIsUnique() {
		// given
		List<UserAgent> existingUserAgents = PredefinedAgents.getExistingUserAgents();

		for (int i = 0; i < existingUserAgents.size(); i++) {
			for (int j = 0; j < existingUserAgents.size(); j++) {
				// when
				if (i == j) {
					// then
					Assert.assertEquals(existingUserAgents.get(i), existingUserAgents.get(j));
				} else {
					// then
					Assert.assertNotSame(existingUserAgents.get(i), existingUserAgents.get(j));
				}
			}
		}
	}
}
