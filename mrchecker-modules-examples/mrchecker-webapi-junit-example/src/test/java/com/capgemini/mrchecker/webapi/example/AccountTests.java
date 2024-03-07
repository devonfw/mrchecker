package com.capgemini.mrchecker.webapi.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.test.core.utils.PageFactory;
import com.capgemini.mrchecker.test.core.utils.StepLogger;
import com.capgemini.mrchecker.webapi.example.base.BaseTestWebAPI;
import com.capgemini.mrchecker.webapi.example.controllers.AccountEndpointsController;
import com.capgemini.mrchecker.webapi.example.env.User;
import com.capgemini.mrchecker.webapi.example.models.GetUserResultModel;
import com.capgemini.mrchecker.webapi.example.models.UserModel;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;

public class AccountTests extends BaseTestWebAPI {
	private final AccountEndpointsController accountEndpointsController = PageFactory.getPageInstance(AccountEndpointsController.class);
	
	@Override
	public void tearDownTest() {
		StepLogger.step("This is example step inside tearDownTest()");
	}
	
	@Override
	public void setUpTest() {
		StepLogger.step("This is example step inside setUpTest()");
	}
	
	@TmsLink("Test Management System ID")
	@Epic("Epic name")
	@Story("Story name #2")
	@Description("Test case description")
	// JUnit annotations
	@Test
	@Tag("JUnit_Tag")
	@DisplayName("Example test - Demo QA Account API #1")
	public void getAllBooksFromBookstore_test() {
		UserModel user = accountEndpointsController.loginUser(User.EXAMPLE_USER);
		GetUserResultModel userDetails = accountEndpointsController.getUserDetails(user.getUserId());
		assertEquals(userDetails.getUserId(), user.getUserId(), "Incorrect user id");
		assertEquals(userDetails.getUsername(), user.getUsername(), "Incorrect username");
		StepLogger.info("User " + user.getUsername() + " is found");
	}
}
