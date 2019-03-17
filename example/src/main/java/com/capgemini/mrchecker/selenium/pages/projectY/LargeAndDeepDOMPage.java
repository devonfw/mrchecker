package com.capgemini.mrchecker.selenium.pages.projectY;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class LargeAndDeepDOMPage extends BasePage {
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains(PageSubURLsProjectYEnum.LARGE_AND_DEEP_DOM.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'Large & Deep DOM' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.LARGE_AND_DEEP_DOM.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
	}
	
}
