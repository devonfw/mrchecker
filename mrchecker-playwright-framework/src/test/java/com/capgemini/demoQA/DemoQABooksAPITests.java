package com.capgemini.demoQA;

import com.capgemini.framework.assertions.ResponseAssert;
import com.capgemini.framework.enums.PrjEpics;
import com.capgemini.framework.playwright.BaseTest;
import com.capgemini.framework.tags.Status;
import com.microsoft.playwright.APIResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.capgemini.framework.actions.ActionAPI.getApiResponse;

public class DemoQABooksAPITests extends BaseTest {

    @TmsLink("Test Management System ID")
    @Epic(PrjEpics.DEMO_QA)
    @Feature("API")
    @Description("Test case description")
    @Test
    @Tag("API")
    @Tag(Status.DONE)
    void demoQAAPIBooks_getBooks_test() throws IOException {

        APIResponse apiResponse = getApiResponse("https://demoqa.com/BookStore/v1/Books");
        ResponseAssert.assertThat(apiResponse)
                .hasStatusCode(200)
                .returnedJsonContainsText("Git Pocket Guide")
                .returnedJsonHasCorrectValueInListOfElements("$['books'][*]['title']", "Speaking JavaScript");
    }
}