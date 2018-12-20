package com.example.selenium.tests.cucumber.common.hooks;

import com.capgemini.mrchecker.test.core.BaseTestHooks;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends BaseTestHooks {
	
	@Override
	public void setUp() {
		// Cucumber runner doesn't support JUnit Before.
		// Please use this method as Before Scenario.
		BFLogger.logDebug("Hooks.setUp()");
	}
	
	@Override
	public void tearDown() {
		// Cucumber runner doesn't support JUnit After.
		// Please use this method as After Scenario.
		BFLogger.logDebug("Hooks.tearDown()");
	}
	
	@Before
	public void before() {
		BFLogger.logDebug("Hooks.before()");
	}
	
	@After
	public void after() {
		BFLogger.logDebug("Hooks.after()");
	}
	
}