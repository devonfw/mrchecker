package com.capgemini.mrchecker.playwright.example.page;

import com.capgemini.mrchecker.playwright.example.base.BasePageGUI;
import com.capgemini.mrchecker.playwright.example.env.GetEnvironmentParam;

public class GoogleSearchPage extends BasePageGUI {
	// URL of the Google search page
	private final String url = GetEnvironmentParam.GOOGLE_URL.getValue();
	// Selectors for web elements
	private final String selectorGoogleSearchInput = "[type='search']";
	private final String selectorGoogleSearchButton = "[class='FPdoLc lJ9FBc'] .gNO89b";
	private final String selectorAcceptCookiesButton = "#L2AGLb";

	// Navigate to the Google search page
	public void startPage() {
		loadPage(url);
	}

	// Accept cookies by clicking the button
	public void acceptCookies() {
		getDriver().currentPage()
				.locator(selectorAcceptCookiesButton)
				.click();
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
