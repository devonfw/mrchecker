package com.capgemini.ntc.test.core.unit;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.capgemini.ntc.test.core.BaseTest;

public class BasicBuildTest extends BaseTest {

	@Test
	public void test() {
		assertTrue(true);
		System.out.println("BasicBuildTest.test()");
		BaseTest.getAnalytics().pageView("http://www.capgemini.com", "BasicBuildTest.test()", "\\BasicBuildTest").post();;
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
		
	}

}
