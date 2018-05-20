package com.capgemini.ntc.selenium.pages.projectY;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class JQueryUIMenuPage extends BasePage {
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains(PageSubURLsProjectYEnum.JQUERY_UI.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'JQueryUI - Menu' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.JQUERY_UI.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
	}
	
}
