package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.By;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class ForgotPasswordPage extends BasePage {
	
	private static final By	selectorEmailInput				= By.cssSelector("input#email");
	private static final By	selectorRetrievePasswordButton	= By.cssSelector("button#form_submit > i");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains("forgot_password");
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("load()");
		
	}
	
	@Override
	public String pageTitle() {
		return "The Internet";
	}
	
	/**
	 * Returns information if email input is visible or not.
	 * 
	 * @return true if email input was found on web page.
	 */
	public boolean isEmailInputVisible() {
		return getDriver().findElementDynamic(selectorEmailInput)
						.isDisplayed();
	}
	
	/**
	 * Enters an email address into email address text field.
	 * 
	 * @param emailAddress
	 *            An email address given by user.
	 */
	public ForgotPasswordPage enterEmailAddress(String emailAddress) {
		getDriver().elementInputText(selectorEmailInput)
						.setInputText(emailAddress);
		return this;
	}
	
	/**
	 * Clicks 'Retrieve password' button.
	 * 
	 * @return An instance of ForgotPasswordEmailSentPage.
	 */
	public ForgotPasswordEmailSentPage clickRetrievePasswordButton() {
		getDriver().elementButton(selectorRetrievePasswordButton)
						.click();
		return new ForgotPasswordEmailSentPage();
	}
	
}
