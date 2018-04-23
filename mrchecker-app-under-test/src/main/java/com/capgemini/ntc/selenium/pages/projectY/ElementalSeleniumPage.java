package com.capgemini.ntc.selenium.pages.projectY;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class ElementalSeleniumPage extends BasePage {
	
	@Override
	public boolean isLoaded() {
		return pageTitle().contains("Elemental Selenium: Receive a Free, Weekly Tip on Using Selenium like a Pro");
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("load()");
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
}