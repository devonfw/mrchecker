package com.capgemini.mrchecker.playwright.example.test;

import com.capgemini.mrchecker.playwright.example.base.BaseTestGUI;
import com.capgemini.mrchecker.test.core.utils.PageFactory;
import com.capgemini.mrchecker.playwright.example.page.GoogleResultPage;
import com.capgemini.mrchecker.playwright.example.page.GoogleSearchPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleSearchTest extends BaseTestGUI {
    // Initialize page instances
    private final GoogleSearchPage googleSearchPage = PageFactory.getPageInstance(GoogleSearchPage.class);
    private final GoogleResultPage googleResultPage = PageFactory.getPageInstance(GoogleResultPage.class);

    @Override
    public void tearDownTest() {
        // Add any cleanup or finalization logic here
    }

    @Override
    public void setUpTest() {
        // Navigate to the Google Search page
        googleSearchPage.startPage();
    }

    @Test
    public void shouldFoundAtLeastOneResult_test() {
        // Accept cookies
        googleSearchPage.acceptCookies();

        // Enter "Test" and perform search
        googleSearchPage.enterGoogleSearchInput("Test");

        // Assert that at least one search result is found
        assertTrue(googleResultPage.getResultsNumber() > 0, "No results found");
    }
}
