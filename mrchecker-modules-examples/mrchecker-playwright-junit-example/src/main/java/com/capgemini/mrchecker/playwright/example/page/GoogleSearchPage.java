package com.capgemini.mrchecker.playwright.example.page;

import com.capgemini.mrchecker.playwright.core.BasePage;
import com.capgemini.mrchecker.playwright.example.env.GetEnvironmentParam;

public class GoogleSearchPage extends BasePage {
	// Selectors for web elements
	private final String	selectorCookiesWindow		= "div.dbsFrd";
	private final String	selectorAcceptCookiesButton	= "button#L2AGLb";
	private final String	selectorGoogleSearchInput	= "[type='search']";
	private final String	selectorGoogleSearchButton	= "[class='FPdoLc lJ9FBc'] .gNO89b";
	
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
	
	// Check if the Cookies Window is displayed
	public boolean isCookiesWindowDisplayed() {
		return getDriver().currentPage()
				.locator(selectorCookiesWindow)
				.isVisible();
	}
	
	// Click the "Accept Cookies" button
	public void clickAcceptCookiesButton() {
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
