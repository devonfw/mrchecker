package com.capgemini.mrchecker.test.core;

import com.capgemini.mrchecker.test.core.logger.BFLogger;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public abstract class BaseTestHooks extends BaseTest {
	
	@Before
	public void setup(Scenario scenario) throws Exception {
		String testName = scenario.getName();
		BFLogger.logInfo("Starting Scenario: \"" + testName);
		this.baseTestWatcher.startingTestWatcher(testName);
	}
	
	@After
	public void tearDown(Scenario scenario) throws Exception {
		String testName = scenario.getName();
		if (scenario.isFailed()) {
			this.baseTestWatcher.failedTestWatcher(testName);
		} else {
			this.baseTestWatcher.succeededTestWatcher(testName);
		}
		this.baseTestWatcher.finishedTestWatcher(testName);
		BFLogger.logInfo(String.format("Ending Scenario: \"%s\"", scenario.getName()) + " result: " + (scenario.isFailed() ? "FAILED" : "PASSED"));
		
	}
	
}