package com.capgemini.mrchecker.selenium.pages.projectY;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

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
	
	/**
	 * Presses given keyboard key.
	 * 
	 * @param keyToPress
	 *            Key to be pressed on keyboard
	 */
	public void pressKey(String keyToPress) {
		getAction().sendKeys(keyToPress)
						.perform();
	}
	
	/**
	 * Returns information from web page about pressed keyboard key.
	 * 
	 * @return Information from web page about pressed key
	 */
	public String getPressedKeyInformation() {
		return getDriver().findElementDynamic(selectorResult)
						.getText();
	}
	
}
