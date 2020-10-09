package com.capgemini.mrchecker.webapi.mts.cucumber.stepdefs;

import com.capgemini.mrchecker.common.mts.data.User;
import com.capgemini.mrchecker.test.core.utils.PageFactory;
import com.capgemini.mrchecker.webapi.mts.common.Context;
import com.capgemini.mrchecker.webapi.mts.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class ApiLoginStepdefs {
	private final LoginPage	loginPage	= PageFactory.getPageInstance(LoginPage.class);
	private final Context	context		= Context.getInstance();
	
	@Step("The user is logged-out")
	@Given("The user is logged-out")
	public void logOut() {
		loginPage.logout();
	}
	
	@Step("I login with {username} user and {password} password")
	@When("I login with {string} and {string}")
	public void iLoginWithAnd(String username, String password) {
		Response response = loginPage.login(new User(username, password));
		context.setResponse(response);
	}
	
	@Step("The user is logged-in")
	@Given("The user is logged-in")
	public void theUserIsLoggedIn() {
		if (!loginPage.isUserLogged()) {
			throw new IllegalArgumentException("No user is logged-in");
		}
	}
}