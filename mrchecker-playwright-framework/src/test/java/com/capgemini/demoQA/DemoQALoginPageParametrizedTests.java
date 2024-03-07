package com.capgemini.demoQA;

import com.capgemini.framework.enums.PrjEpics;
import com.capgemini.framework.logger.AllureStepLogger;
import com.capgemini.framework.playwright.BaseTest;
import com.capgemini.framework.tags.Status;
import com.capgemini.pages.demoqaforms.DemoQALoginPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

//Multiple tests in single test class


public class DemoQALoginPageParametrizedTests extends BaseTest {
	
	private       DemoQALoginPage demoQALoginPage;
	private final String          expectedOutputText = "Invalid username or password!";
	
	@Step("[SETUP]")
	@BeforeEach
	public void beforeEach() {
		AllureStepLogger.step("This is example step inside setUpTest()");
		demoQALoginPage = new DemoQALoginPage();
		demoQALoginPage.startPage();
	}
	
	@TmsLink("Test Management System ID")
	@Epic(PrjEpics.DEMO_QA)
	@Feature("GUI Parametrized")
	@Description("Test case description")
	@ParameterizedTest(name = "demoQALoginPage_Login#USER[{0}]#PASSWORD[{1}}")
	@CsvSource({ "user1,user1Pass", "user2,user2Pass", "user3,user3Pass" })
	@Tag(Status.DONE)
	void demoQALoginPage_Login_test(String user, String password) {
		
		demoQALoginPage.fillUsername(user);
		demoQALoginPage.fillPassword(password);
		demoQALoginPage.clickLoginButton();
		
		String outputText = demoQALoginPage.getOutputText();
		assertThat(outputText)
				.as("Output text is empty")
				.withFailMessage("Output text is empty. It should be: \" + expectedOutputText")
				.isNotEmpty();
		assertThat(outputText)
				.as("Output text is " + expectedOutputText)
				.withFailMessage("Output text is not as expected.It should be: " + expectedOutputText + " but is " + outputText)
				.isEqualTo(expectedOutputText);
		
	}
}