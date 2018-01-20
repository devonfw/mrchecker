package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.By;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class KeyPressesPage extends BasePage {
	
	private static final By selectorResult = By.cssSelector("#result");
	
	@Override
	public boolean isLoaded() {
		BFLogger.logDebug("The KeyPresses page is loaded.");
		return getDriver().getCurrentUrl()
						.equals("http://the-internet.herokuapp.com/key_presses");
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("load");
		getDriver().get("http://the-internet.herokuapp.com/key_presses");
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
	
	public String getSendKeyPress(String keyToPress) {
		getAction().sendKeys(keyToPress)
						.perform();
		return getDriver().findElementDynamic(selectorResult)
						.getText();
	}
	
}
