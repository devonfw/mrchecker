package com.capgemini.mrchecker.selenium.pages.projectY;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class ForgotPasswordPage extends BasePage {
	
	private static final By	selectorEmailInput				= By.cssSelector("input#email");
	private static final By	selectorRetrievePasswordButton	= By.cssSelector("button#form_submit > i");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains(PageSubURLsProjectYEnum.FORGOT_PASSWORD.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'Forgot Password' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.FORGOT_PASSWORD.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
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
