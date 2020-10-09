package com.capgemini.mrchecker.webapi.mts.pages;

import com.capgemini.mrchecker.common.mts.data.User;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class LoginPage extends MTSWebApiBasePage {
	
	@Step("Log-in")
	public Response login(User user) {
		Response response = getLoginRequestBuilder().body(user)
				.post(getEndpoint());
		accessToken = response.header("Authorization");
		
		return response;
	}
	
	@Step("Log-out a user")
	public void logout() {
		accessToken = "";
	}
	
	public boolean isUserLogged() {
		return !accessToken.isEmpty();
	}
	
	@Override
	public String getEndpoint() {
		return "/login";
	}
}
