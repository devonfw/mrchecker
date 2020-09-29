package com.capgemini.mrchecker.webapi.mts.pages;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class SecurityPage extends MTSWebApiBasePage {
	
	@Override
	public String getEndpoint() {
		return "/services/rest/security/v1/currentuser";
	}
	
	@Step("Call GET check the current user")
	public Response checkCurrentUser() {
		return request().get(serviceBaseUrs + getEndpoint());
	}
}
