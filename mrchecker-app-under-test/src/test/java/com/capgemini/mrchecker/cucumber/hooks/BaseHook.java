package com.capgemini.mrchecker.cucumber.hooks;

import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class BaseHook {
	
	/*
	 * PLEASE DO NOT MODIFY THIS FILE.
	 * For any Hook file, please create separate one
	 */
	
	private BaseTest baseTestHooks;
	
	public BaseHook() {
		baseTestHooks = new BaseTest() {
			@Override
			public void setUp() {
				// Include any global actions before Scenario
				// If no global action needed. Please create separate Hook class with Cucumber Before
			}
			
			@Override
			public void tearDown() {
				// Include any global actions after Scenario.
				// If no global action needed. Please create separate Hook class with Cucumber After
			}
		};
	}
	
	@Before(order = 0)
	public void setup(Scenario scenario) throws Exception {
		String testName = scenario.getName();
		BFLogger.logInfo("Starting Scenario: \"" + testName + "\"");
		baseTestHooks.getBaseTestWatcher()
				.startingTestWatcher(testName);
	}
	
	@After(order = Integer.MAX_VALUE)
	public void tearDown(Scenario scenario) throws Exception {
		String testName = scenario.getName();
		if (scenario.isFailed()) {
			baseTestHooks.getBaseTestWatcher()
					.failedTestWatcher(testName);
		} else {
			baseTestHooks.getBaseTestWatcher()
					.succeededTestWatcher(testName);
		}
		baseTestHooks.getBaseTestWatcher()
				.finishedTestWatcher(testName);
		BFLogger.logInfo(String.format("Ending Scenario: \"%s\"", scenario.getName()) + " result: " + (scenario.isFailed() ? "FAILED" : "PASSED"));
		
	}
	
}