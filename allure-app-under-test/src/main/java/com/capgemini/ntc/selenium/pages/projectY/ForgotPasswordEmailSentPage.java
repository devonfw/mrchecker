package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.By;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class ForgotPasswordEmailSentPage extends BasePage {
	
	private static final By		selectorInfoLabel	= By.cssSelector("div#content");
	private static final String	successMessage		= "Your e-mail's been sent!";
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains("email_sent");
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("load()");
		
	}
	
	@Override
	public String pageTitle() {
		return "The Internet";
	}
	
	public boolean isEmailSentSuccessfully() {
		return getDriver().findElementDynamic(selectorInfoLabel)
						.getText()
						.trim()
						.equals(successMessage);
	}
	
}
