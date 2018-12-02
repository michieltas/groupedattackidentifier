package com.bitsensor.attack;

import com.bitsensor.attack.main.Main;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class MainTest extends TestCase {

	/**
	 * Test 1 run.
	 */
	public void testRun() {
		// given
		String[] args = new String[] { "test" };

		// when run
		Main.main(args);

		// then at this point no exceptions
	}

	/**
	 * Creation test.
	 */
	public void testInstantiation() {
		// given
		Main main;

		// when instantiate
		main = new Main();

		// then test not null
		assertNotNull(main);
	}

}
