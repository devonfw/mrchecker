package com.capgemini.mrchecker.selenium.pages.projectY;

import java.util.ArrayList;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class ABtestPage extends BasePage {
	
	private static final By elementalSeleniumLinkSelector = By.cssSelector("div > div > a");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains(PageSubURLsProjectYEnum.ABTEST.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'A/B Test Control' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.ABTEST.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
	}
	
	/**
	 * Clicks 'Elemental Selenium' link at the bottom of the page.
	 * 
	 * @return ElementalSeleniumPage object.
	 */
	public ElementalSeleniumPage clickElementalSeleniumLink() {
		getDriver().findElementDynamic(elementalSeleniumLinkSelector)
						.click();
		getDriver().waitForPageLoaded();
		return new ElementalSeleniumPage();
	}
	
	/**
	 * Switches window to the next one - different than the current.
	 */
	public void switchToNextTab() {
		ArrayList<String> tabsList = new ArrayList<String>(getDriver().getWindowHandles());
		getDriver().switchTo()
						.window(tabsList.get(1));
	}
	
}