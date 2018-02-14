package com.capgemini.ntc.selenium.pages.projectY;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class ForgotPasswordPage extends BasePage {
	
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
	
}
