package com.capgemini.ntc.selenium.pages.projectY;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class ABtestPage extends BasePage {
	
	private static final By selectorSeleniumLink = By.cssSelector("div > div > a");
	
	@Override
	public boolean isLoaded() {
		return getDriver().getCurrentUrl()
				.contains("abtest");
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("load()");
	}
	
	@Override
	public String pageTitle() {
		return "The Internet";
	}
	
	public ElementalSeleniumPage clickElementalSeleniumLink() {
		WebElement elementSeleniumClickLink = getDriver().findElementDynamic(selectorSeleniumLink);
		elementSeleniumClickLink.click();
		getDriver().waitForPageLoaded();
		return new ElementalSeleniumPage();
	}
	
	public void switchToNextTab() {
		ArrayList<String> tabsList = new ArrayList<String>(getDriver().getWindowHandles());
		getDriver().switchTo()
				.window(tabsList.get(1));
	}
}