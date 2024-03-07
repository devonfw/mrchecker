package com.capgemini.demoQA;

import com.capgemini.framework.enums.PrjEpics;
import com.capgemini.framework.enums.User;
import com.capgemini.framework.logger.AllureStepLogger;
import com.capgemini.framework.playwright.BaseTest;
import com.capgemini.framework.tags.Status;
import com.capgemini.pages.demoqaforms.DemoQALoginPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Epic(PrjEpics.DEMO_QA)
public class DemoQALoginPageMultipleTests extends BaseTest {
	
	private final DemoQALoginPage demoQALoginPage    = new DemoQALoginPage();
	private final String          userLogin          = User.USER_ADMIN_QA.getLogin();
	private final String          invalidUserLogin   = "invalidUserLogin";
	private final String          expectedOutputText = "Invalid username or password!";
	
	@Step("[SETUP]")
	@BeforeEach
	public void beforeEach() {
		AllureStepLogger.step("This is example step inside setUpTest()");
		demoQALoginPage.startPage();
	}
	
	@TmsLink("Test Management System ID")
	@Feature("GUI")
	@Description("Test case description")
	//JUnit annotations
	@Test
	@Tag("JUnit_Tag")
	@Tag(Status.IN_PROGRESS)
	void demoQALoginPage_Login_test() {
		demoQALoginPage.fillUsername(userLogin);
		demoQALoginPage.fillPassword(invalidUserLogin);
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
	
	@TmsLink("Test Management System ID")
	@Story("GUI")
	@Description("Test case description")
	@Test
	@Tag("JUnit_Tag")
	void demoQALoginPage_Visibility_test() {
		verifyVisibilityOfElement("Username input", demoQALoginPage.isDisplayedUsernameInput());
		verifyVisibilityOfElement("Password input", demoQALoginPage.isDisplayedPasswordInput());
		verifyVisibilityOfElement("Login button", demoQALoginPage.isDisplayedLoginButton());
	}
	
	@Step("Verify visibility of {elementName}")
	private void verifyVisibilityOfElement(String elementName, boolean isVisible) {
		assertThat(isVisible).as(elementName + " is visible")
				.withFailMessage(elementName + " is not visible")
				.isTrue();
	}
}