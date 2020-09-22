package com.capgemini.mrchecker.selenium.mts.cucumber.stepdefs;

import com.capgemini.mrchecker.common.mts.data.User;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiHomePage;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiLoginPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginSteps {
	private final ThaiHomePage myThaiStarHome = new ThaiHomePage();
	
	private String loggedInUser;
	
	@Given("^The My Thai start page has been opened$")
	public void startPageIsOpen() {
		myThaiStarHome.load();
	}
	
	@When("^I login as Willy Waiter$")
	public void loginAsWillyWaiter() {
		ThaiLoginPage loginPage = myThaiStarHome.clickLogInButton();
		User waiter = User.waiterUser();
		loggedInUser = waiter.getUsername();
		loginPage.enterCredentials(loggedInUser, waiter.getPassword());
	}
	
	@Then("^My login name is shown$")
	public void loginNameIsShown() {
		myThaiStarHome.isUserLogged(loggedInUser);
	}
}