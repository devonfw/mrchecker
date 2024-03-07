package com.capgemini.framework.actions;

import com.capgemini.framework.logger.AllureStepLogger;
import com.capgemini.framework.logger.Logger;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import io.qameta.allure.Step;

import static com.capgemini.framework.playwright.PlaywrightFactory.getPage;

public final class ActionGui {
	@Step("Fill text field \"{fieldName}\" with \"{inputValue}\"")
	public static void fillTextBox(String fieldName, String inputValue, Locator locator) {
		locator.click();
		locator.fill(inputValue);
	}
	
	public static void fillTextBox(String fieldName, String inputValue, String selector) {
		fillTextBox(fieldName, inputValue, getPage().locator(selector));
	}
	
	@Step("Set radiobutton \"{fieldName}\"")
	public static void setRadioButton(String fieldName, Locator locator) {
		locator.click();
	}
	
	public static void setRadioButton(String fieldName, String selector) {
		setRadioButton(fieldName, getPage().locator(selector));
	}
	
	@Step("Click \"{fieldName}\"")
	public static void click(String fieldName, Locator locator) {
		locator.click();
	}
	
	public static void pressEnter(Locator locator) {
		locator.press("Enter");
	}
	
	@Step("Verify {fieldName} is visible")
	public static void verifyElementVisible(String fieldName, Locator locator) {
		locator.isVisible();
	}
	
	public static void verifyElementVisible(String fieldName, String selector) {
		verifyElementVisible(fieldName, getPage().locator(selector));
	}
	
	@Step("Open page {url}")
	public static void navigate(String url, int pageLoadingTimeout) {
		getPage().navigate(url, new Page.NavigateOptions().setTimeout(pageLoadingTimeout));
		getPage().onLoad(p -> AllureStepLogger.info("Page loaded!"));
	}

	
	public void waitForPageToLoad() {
		getPage().waitForLoadState(LoadState.NETWORKIDLE);
	}
	
	public String getPageTitle() {
		return getPage().title();
	}
	
	public static void waitMilliseconds(Integer milliseconds) {
		Logger.logDebug("[waitMilliseconds] " + milliseconds);
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			Logger.logError(e.getMessage());
			Thread.currentThread().interrupt();
		}
	}
	
}
