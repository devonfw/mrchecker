package com.capgemini.frameworkExamples;

import com.capgemini.framework.assertions.ResponseAssert;
import com.capgemini.framework.enums.PrjEpics;
import com.capgemini.framework.enums.User;
import com.capgemini.framework.logger.AllureStepLogger;
import com.capgemini.framework.playwright.BaseTest;
import com.capgemini.framework.tags.Status;
import com.capgemini.pages.demoqaforms.DemoQALoginPage;
import com.microsoft.playwright.APIResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;

import static com.capgemini.framework.actions.ActionAPI.getApiResponse;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Epic(PrjEpics.EXAMPLES)
@Story("AssertJ example")
class AssertJWithAllureStepExample extends BaseTest {
	
	@TmsLink("Test Management System ID")

	@Description("AssertJ in API tests")
	@Test
	@Tag("API")
	@Tag(Status.DONE)
	void assertJWithAllureStep_APItest() throws IOException {
		APIResponse apiResponse = getApiResponse("https://demoqa.com/BookStore/v1/Books");
		ResponseAssert.assertThat(apiResponse)
				.hasStatusCode(200)
				.returnedJsonContainsText("Git Pocket Guide")
				// Please see how to construct paths and test it here https://jsonpath.com/
				// Create more assert functions like this if you need
				.returnedJsonHasCorrectValueInListOfElements("$['books'][*]['title']", "Speaking JavaScript");
	}
	
	@TmsLink("Test Management System ID")
	@Description("AssertJ in API tests")
	@Test
	@Tag("API")
	@Tag(Status.DONE)
	void assertJWithAllureStep_GUItest() {
		DemoQALoginPage demoQALoginPage = new DemoQALoginPage();
		
		final String userLogin = User.USER_ADMIN_QA.getLogin();
		final String invalidUserLogin = "invalidUserLogin";
		final String expectedOutputText = "Invalid username or password!";
		
		demoQALoginPage.startPage();
		demoQALoginPage.fillUsername(userLogin);
		demoQALoginPage.fillPassword(invalidUserLogin);
		demoQALoginPage.clickLoginButton();
		String outputText = demoQALoginPage.getOutputText();
		
		assertThat(outputText)
				.as("Output text should not be empty")
				.withFailMessage("Expected: " + expectedOutputText)
				.isNotEmpty();

		Assertions.assertThat(outputText)
				.as("Output text should be: " + expectedOutputText)
				.withFailMessage("but is: " + outputText)
				.isEqualTo(expectedOutputText);
		
		AllureStepLogger.step("Output text is correct: " + outputText);
		
	}
}
