package com.capgemini.ntc.selenium.pages.projectY;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class NewWindowPage extends BasePage {
	
	@Override
	public boolean isLoaded() {
		BFLogger.logDebug("is loaded");
		return getDriver().getCurrentUrl()
						.equals("http://the-internet.herokuapp.com/windows/new");
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("load page");
		getDriver().get("http://the-internet.herokuapp.com/windows/new");
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
}
