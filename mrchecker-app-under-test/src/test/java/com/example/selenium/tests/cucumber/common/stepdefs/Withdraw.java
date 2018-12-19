package com.example.selenium.tests.cucumber.common.stepdefs;

import static org.junit.Assert.assertTrue;

import com.capgemini.mrchecker.test.core.logger.BFLogger;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java8.En;

public class Withdraw implements En {
	
	class Account {
		public Account(int openingBalance) {
		}
	}
	
	@cucumber.api.java.Before
	public void before() {
		
	}
	
	@cucumber.api.java.After
	public void after() {
		
	}
	
	@When("^Enter search term '(.*?)'$")
	public void searchFor(String searchTerm) {
		BFLogger.logDebug("Enter search: " + searchTerm);
		;
	}
	
	// public Withdraw() {
	// Given("I have deposited ${int} in my account", (Integer amount) -> {
	// // Write code here that turns the phrase above into concrete actions
	// new Account(amount);
	// });
	//
	// When("I request ${int}", (Integer arg1) -> {
	// // Write code here that turns the phrase above into concrete actions
	// BFLogger.logDebug("I've requested " + arg1);
	// });
	//
	// Then("${int} should be dispensed", (Integer int1) -> {
	// // Write code here that turns the phrase above into concrete actions
	// assertTrue(true);
	// // throw new cucumber.api.PendingException();
	// });
	// }
	
	@Given("^I have deposited \\$(\\d+) in my account$")
	public void iHaveDeposited$InMyAccount(int amount) throws Throwable {
		new Account(amount);
	}
	
	@When("^I request \\$(\\d+)$")
	public void iRequest$(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		BFLogger.logDebug("I've requested " + arg1);
		// throw new PendingException();
	}
	
	@Then("^\\$(\\d+) should be dispensed$")
	public void shouldBeDispensed(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		assertTrue(true);
		// throw new PendingException();
	}
	
}
