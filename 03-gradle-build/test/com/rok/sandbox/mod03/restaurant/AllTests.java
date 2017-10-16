package com.rok.sandbox.mod03.restaurant;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(TableTest.class);
		suite.addTestSuite(VisitorTest.class);
		suite.addTestSuite(RestaurantTest.class);
		//$JUnit-END$
		return suite;
	}

}
