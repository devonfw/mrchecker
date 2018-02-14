package com.capgemini.ntc.selenium.pages.projectY.gmail;

import org.openqa.selenium.By;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class GmailWelcomePage extends BasePage {
	
	private static final By	selectorPasswordInputField	= By.cssSelector("div#password input");
	private static final By	selectorNextButton			= By.cssSelector("div#passwordNext > content");
	private static final By	selectorGoogleLetter		= By.cssSelector("#logo > div.Bt4Beb > span.uOGS3.LHvJZ");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains("signin/v2/sl/pwd") && pageTitle().equals("Gmail");
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("load page");
		
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
	
	public void enterPassword(String password) {
		getDriver().elementInputText(selectorPasswordInputField)
						.setInputText(password);
	}
	/*
	 * public GmailInboxPage clickNextButton() {
	 * getDriver().findDynamicElement(selectorNextButton)
	 * .click();
	 * return new GmailInboxPage();
	 * }
	 */
}
