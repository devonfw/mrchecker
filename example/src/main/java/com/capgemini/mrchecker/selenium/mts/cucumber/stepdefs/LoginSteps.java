package com.capgemini.mrchecker.selenium.mts.cucumber.stepdefs;

import com.capgemini.mrchecker.common.mts.data.User;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiHomePage;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiLoginPage;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginSteps {
	private final ThaiHomePage	myThaiStarHome	= PageFactory.getPageInstance(ThaiHomePage.class);
	private final ThaiLoginPage	loginPage		= PageFactory.getPageInstance(ThaiLoginPage.class);
	private String				loggedInUser;
	
	@Given("^The My Thai start page has been opened$")
	public void startPageIsOpen() {
		myThaiStarHome.load();
	}
	
	@When("^I login as Willy Waiter$")
	public void loginAsWillyWaiter() {
		User waiter = User.waiterUser();
		loggedInUser = waiter.getUsername();
		myThaiStarHome.clickLogInButton();
		loginPage.enterCredentials(loggedInUser, waiter.getPassword());
	}
	
	@Then("^My login name is shown$")
	public void loginNameIsShown() {
		myThaiStarHome.isUserLogged(loggedInUser);
	}
}