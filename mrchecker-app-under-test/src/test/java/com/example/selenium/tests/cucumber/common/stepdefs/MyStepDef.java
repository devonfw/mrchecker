package com.example.selenium.tests.cucumber.common.stepdefs;

import static org.junit.Assert.assertTrue;

import com.capgemini.mrchecker.test.core.logger.BFLogger;

import cucumber.api.java.en.Given;

public class MyStepDef {
	
	@Given("^I have (\\d+) cukes in my belly$")
	public void i_have_n_cukes_in_my_belly(int cukes) {
		BFLogger.logDebug("Cukes: " + cukes);
		assertTrue(false);
	}
}