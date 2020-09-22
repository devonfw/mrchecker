package com.capgemini.mrchecker.webapi.mts.pages;

import com.capgemini.mrchecker.common.mts.data.User;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LoginPage extends MTSWebApiBasePage {
	
	public Response login(User user) {
		Response response = RestAssured.with()
				.body(user)
				.post(serviceBaseUrs + getEndpoint());
		accessToken = response.header("Authorization");
		
		return response;
	}
	
	public void logout() {
		accessToken = "";
	}
	
	@Override
	public String getEndpoint() {
		return "/login";
	}
}
