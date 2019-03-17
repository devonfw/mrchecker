package com.capgemini.mrchecker.selenium.pages.projectY;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class ElementalSeleniumPage extends BasePage {
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains(GetEnvironmentParam.ELEMENTAL_SELENIUM_PAGE.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'Elemental Selenium' page.");
		getDriver().get(GetEnvironmentParam.ELEMENTAL_SELENIUM_PAGE.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
	}
}