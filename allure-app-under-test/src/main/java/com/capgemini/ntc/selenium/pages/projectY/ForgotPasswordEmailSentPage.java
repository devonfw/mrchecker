package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.By;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.ntc.test.core.logger.BFLogger;

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
	
	public boolean isEmailSentSuccessfully() {
		return getDriver().findElementDynamic(selectorInfoLabel)
						.getText()
						.trim()
						.equals(successMessage);
	}
	
}
