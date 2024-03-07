package com.capgemini.frameworkExamples;

import com.capgemini.framework.enums.PrjEpics;
import com.capgemini.framework.playwright.BaseTest;
import com.capgemini.framework.tags.Status;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.capgemini.framework.actions.ActionAPI.getApiResponse;
import static org.junit.jupiter.api.Assertions.fail;


class AllureKnownBugExampleTest extends BaseTest {
	
	@TmsLink("JIRA-0")
	@Epic(PrjEpics.EXAMPLES)
	@Story("Known bug example")
	@Description("Please check allure report -> categories. There should be known bug category and this test inside")
	@Test
	@Tag("Allure")
	@Tag(Status.DONE)
	void allureKnownBug() throws IOException {
		var apiResponse = getApiResponse("https://demoqa.com/BookStore/v1/Books");
		fail( "This is known bug text putted in src/test/resources/bug/bug.json");
	}
}
