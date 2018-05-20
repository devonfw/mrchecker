package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.By;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class KeyPressesPage extends BasePage {
	
	private static final By selectorResult = By.cssSelector("#result");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains(PageSubURLsProjectYEnum.KEY_PRESS.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'Key Presses' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.KEY_PRESS.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
	}
	
	public String getSendKeyPress(String keyToPress) {
		getAction().sendKeys(keyToPress)
						.perform();
		return getDriver().findElementDynamic(selectorResult)
						.getText();
	}
	
}
