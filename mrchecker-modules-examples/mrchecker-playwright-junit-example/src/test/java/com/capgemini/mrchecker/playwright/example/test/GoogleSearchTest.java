package com.capgemini.mrchecker.playwright.example.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.playwright.example.page.GoogleResultPage;
import com.capgemini.mrchecker.playwright.example.page.GoogleSearchPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

public class GoogleSearchTest extends BaseTest {
	// Initialize page instances
	private final GoogleSearchPage	googleSearchPage	= PageFactory.getPageInstance(GoogleSearchPage.class);
	private final GoogleResultPage	googleResultPage	= PageFactory.getPageInstance(GoogleResultPage.class);
	
	@Override
	public void tearDown() {
		// Add any cleanup or finalization logic here
	}
	
	@Override
	public void setUp() {
		// Navigate to the Google Search page
		googleSearchPage.load();
		
		// Accept cookies, if Cookies Window displayed
		if (googleSearchPage.isCookiesWindowDisplayed()) {
			googleSearchPage.clickAcceptCookiesButton();
		}
	}
	
	@Test
	public void shouldFindAtLeastOneResult_test() {
		// Enter "Test" and perform search
		googleSearchPage.enterGoogleSearchInput("Test");
		
		// Assert that at least one search result is found
		assertTrue(googleResultPage.getResultsNumber() > 0, "No results found");
	}
}
