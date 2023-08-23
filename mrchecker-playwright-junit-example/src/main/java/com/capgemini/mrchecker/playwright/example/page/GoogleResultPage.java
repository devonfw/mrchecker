package com.capgemini.mrchecker.playwright.example.page;

import com.capgemini.mrchecker.playwright.example.base.BasePageGUI;
import com.microsoft.playwright.Page;

public class GoogleResultPage extends BasePageGUI {
	// Selector for search result items
	private final String selectorSearchResult = "#search .v7W49e > div";

	// Retrieve the number of search results
	public int getResultsNumber() {
		// Get the current page
		Page currentPage = getDriver().currentPage();

		// Wait for the search result selector to appear
		currentPage.waitForSelector(selectorSearchResult, new Page.WaitForSelectorOptions().setTimeout(5000));

		// Count the number of search result items
		return currentPage.querySelectorAll(selectorSearchResult)
				.size();
	}
}

