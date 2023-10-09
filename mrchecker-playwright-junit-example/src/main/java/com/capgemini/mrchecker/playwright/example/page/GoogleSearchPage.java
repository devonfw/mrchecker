package com.capgemini.mrchecker.playwright.example.page;

import com.capgemini.mrchecker.playwright.core.BasePage;
import com.capgemini.mrchecker.playwright.example.env.GetEnvironmentParam;

public class GoogleSearchPage extends BasePage {
	// Selectors for web elements
	private final String selectorGoogleSearchInput = "[type='search']";
	private final String selectorGoogleSearchButton = "[class='FPdoLc lJ9FBc'] .gNO89b";

	@Override
	public String pageTitle() {
		return null;
	}

	@Override
	public boolean isLoaded() {
		return false;
	}

	@Override
	public void load() {
		String url = GetEnvironmentParam.GOOGLE_URL.getValue();
		loadPage(url);
	}

	// Enter search query and submit the form
	public void enterGoogleSearchInput(String searchText) {
		// Fill the search input with the given text
		getDriver().currentPage()
				.locator(selectorGoogleSearchInput)
				.fill(searchText);

		// Click the Google Search button
		getDriver().currentPage()
				.locator(selectorGoogleSearchButton)
				.click();
	}
}
