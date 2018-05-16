package com.capgemini.ntc.selenium.pages.projectY;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.test.core.logger.BFLogger;

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