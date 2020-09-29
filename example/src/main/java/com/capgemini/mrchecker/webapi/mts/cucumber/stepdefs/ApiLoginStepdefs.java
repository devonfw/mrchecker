package com.capgemini.mrchecker.webapi.mts.cucumber.stepdefs;

import com.capgemini.mrchecker.common.mts.data.User;
import com.capgemini.mrchecker.test.core.utils.PageFactory;
import com.capgemini.mrchecker.webapi.mts.common.Context;
import com.capgemini.mrchecker.webapi.mts.pages.LoginPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

public class ApiLoginStepdefs {
	private final LoginPage	loginPage	= PageFactory.getPageInstance(LoginPage.class);
	private final Context	context		= Context.getInstance();
	
	@Given("The user is logged-out")
	public void isLoggedOut() {
		loginPage.logout();
	}
	
	@When("I login with {string} and {string}")
	public void iLoginWithAnd(String username, String password) {
		Response response = loginPage.login(new User(username, password));
		context.setResponse(response);
	}
	
	@Given("The user is logged-in")
	public void theUserIsLoggedIn() {
		if (!loginPage.isUserLogged()) {
			throw new IllegalArgumentException("No user is logged-in");
		}
	}
}
