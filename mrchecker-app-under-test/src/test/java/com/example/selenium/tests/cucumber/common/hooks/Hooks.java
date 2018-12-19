package com.example.selenium.tests.cucumber.common.hooks;

import com.capgemini.mrchecker.test.core.logger.BFLogger;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	
	@Before
	public void setup(Scenario scenario) throws Exception {
		BFLogger.logInfo("Starting Scenario: \"" + scenario.getName());
	}
	
	@After
	public void tearDown(Scenario scenario) throws Exception {
		if (scenario.isFailed()) {
			BFLogger.logInfo("Scenario has failed: " + scenario.getName());
		}
		BFLogger.logInfo(String.format("Ending Scenario: \"%s\"", scenario.getName()) + " result: " + (scenario.isFailed() ? "FAILED" : "PASSED"));
		
	}
	
}