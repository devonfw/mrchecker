package com.capgemini.mrchecker.webapi.example.base;

import com.capgemini.mrchecker.test.core.BaseTest;

public abstract class BaseTestWebAPI extends BaseTest {
	
	// Each test tear down (post) method
	public abstract void tearDownTest();
	
	// Each test setup (pre) method
	public abstract void setUpTest();
	
	@Override
	// General setup (pre) method used in all tests
	// Helpful if there is a need to prepare common behavior - for example exceptions handling
	public final void setUp() {
		try {
			setUpTest();
		} catch (Throwable throwable) {
			// Change all fails on pre into Aborted Exception ("skipped" grayed tests on Allure report)
			// Add [PRE] to the exception message
			// throw new TestAbortedException("[PRE] " + throwable.getMessage(), throwable);
			throw throwable;
		}
	}
	
	@Override
	// General tear down (post) method used in all tests
	// Helpful if there is a need to prepare common behavior - for example exceptions handling
	public final void tearDown() {
		try {
			tearDownTest();
		} catch (Throwable throwable) {
			// Change all fails on post into Aborted Exception ("skipped" grayed tests on Allure report)
			// Add [POST] to the exception message
			// throw new TestAbortedException("[POST] " + throwable.getMessage(), throwable);
			throw throwable;
		}
	}
	
}