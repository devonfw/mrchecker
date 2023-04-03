package com.capgemini.mrchecker.webapi.example.controllers;

import static com.capgemini.mrchecker.webapi.example.env.GetEnvironmentParam.DEMO_QA_API_URL;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.capgemini.mrchecker.webapi.example.base.BasePageWebAPI;
import com.capgemini.mrchecker.webapi.example.base.PerformApiAction;
import com.capgemini.mrchecker.webapi.example.enums.Endpoints;
import com.capgemini.mrchecker.webapi.example.env.User;
import com.capgemini.mrchecker.webapi.example.models.GetUserResultModel;
import com.capgemini.mrchecker.webapi.example.models.RegisterViewModel;
import com.capgemini.mrchecker.webapi.example.models.UserModel;

public class AccountEndpointsController extends BasePageWebAPI {
	@Override
	public String getEndpoint() {
		return Endpoints.ACCOUNT_CONTROLLER_ENDPOINT.getEndpoint();
	}
	
	public UserModel loginUser(User user) {
		RegisterViewModel userRegistration = new RegisterViewModel() {
			{
				setUserName(user.getUsername());
				setPassword(user.getPassword());
			}
		};
		UserModel userDetails = PerformApiAction.postToLoginHost(DEMO_QA_API_URL.getValue(), Endpoints.ACCOUNT_LOGIN_ENDPOINT.getEndpoint(), userRegistration)
				.as(UserModel.class);
		assertNotNull(userDetails, "No response was returned");
		assertNotNull(userDetails.getUserId(), "No valid userID");
		PerformApiAction.setToken(userDetails.getToken());
		return userDetails;
	}
	
	public GetUserResultModel getUserDetails(String userID) {
		GetUserResultModel userModel = PerformApiAction.getFromHostLoggedUser(DEMO_QA_API_URL.getValue(), Endpoints.ACCOUNT_GET_USER_ENDPOINT.getEndpoint(userID))
				.as(GetUserResultModel.class);
		assertNotNull(userModel, "No response was returned");
		return userModel;
	}
}
