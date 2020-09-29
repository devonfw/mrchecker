package com.capgemini.mrchecker.webapi.mts.cucumber.stepdefs;

import com.capgemini.mrchecker.test.core.utils.PageFactory;
import com.capgemini.mrchecker.webapi.mts.common.Context;
import com.capgemini.mrchecker.webapi.mts.pages.SecurityPage;

import cucumber.api.java.en.When;
import io.restassured.response.Response;

public class ApiSecurityStepdefs {
	private final SecurityPage	securityPagePage	= PageFactory.getPageInstance(SecurityPage.class);
	private final Context		context				= Context.getInstance();
	
	@When("I check for currently logged-in user")
	public void iCheckForCurrentlyLoggedInUser() {
		Response response = securityPagePage.checkCurrentUser();
		context.setResponse(response);
	}
}
