package com.capgemini.mrchecker.selenium.pages.projectY;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class ForgotPasswordEmailSentPage extends BasePage {
	
	private static final By		selectorInfoLabel	= By.cssSelector("div#content");
	private static final String	successMessage		= "Your e-mail's been sent!";
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains(PageSubURLsProjectYEnum.FORGOT_PASSWORD_EMAIL_SENT.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'Email sent' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.FORGOT_PASSWORD_EMAIL_SENT.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
	}
	
	/**
	 * Returns information if email message was successfully sent.
	 * 
	 * @return True if email was sent, false otherwise.
	 */
	public boolean isEmailSentSuccessfully() {
		return getDriver().findElementDynamic(selectorInfoLabel)
						.getText()
						.trim()
						.equals(successMessage);
	}
	
}
