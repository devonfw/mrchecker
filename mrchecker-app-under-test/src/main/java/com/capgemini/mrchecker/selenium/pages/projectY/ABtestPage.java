package com.capgemini.mrchecker.selenium.pages.projectY;

import java.util.ArrayList;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.Button;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class ABtestPage extends TheInternetSubpage {
	
	private static final By	elementalSeleniumLinkSelector	= By.cssSelector("div > div > a");
	public final By			pageLinkSelector				= By.cssSelector("li > a[href*='abtest']");
	
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
	
	@Override
	public void clickPageLink() {
		new Button(pageLinkSelector).click();
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