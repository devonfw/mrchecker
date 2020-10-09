package com.capgemini.mrchecker.cucumber.stepdefs;

import com.capgemini.mrchecker.test.core.logger.BFLogger;

import io.cucumber.java.en.Given;

public class BaseStepDef {
	
	@Given("^I have (\\d+) cukes in my belly$")
	public void i_have_n_cukes_in_my_belly(int cukes) {
		BFLogger.logDebug("Cukes: " + cukes);
	}
}