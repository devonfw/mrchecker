package com.capgemini.ntc.selenium.pages.projectY.gmail;

import org.openqa.selenium.By;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class GmailSignInPage extends BasePage {
	
	private static final By	selectorEmailInputField	= By.id("identifierId");
	private static final By	selectorNextButton		= By.cssSelector("div#identifierNext > content");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains("signin/v2/identifier") && pageTitle().equals("Gmail");
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("load page");
		
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
	
	public GmailSignInPage enterEmailAddress(String emailAddress) {
		getDriver().elementInputText(selectorEmailInputField)
						.setInputText(emailAddress);
		return this;
	}
	
	public GmailWelcomePage clickNextButton() {
		getDriver().elementButton(selectorNextButton)
						.click();
		return new GmailWelcomePage();
	}
	
}
