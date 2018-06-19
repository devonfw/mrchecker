package com.capgemini.mrchecker.selenium.pages.projectY;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.Button;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class MultipleWindowsPage extends TheInternetSubpage {
	
	private final static By	selectorLink		= By.cssSelector("#content > div > a");
	public final By			pageLinkSelector	= By.cssSelector("li > a[href*='windows']");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains(PageSubURLsProjectYEnum.WINDOW.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'Opening a new window' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.WINDOW.getValue());
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
	 * Clicks 'click here' link.
	 * 
	 * @return NewWindowPage object
	 */
	public NewWindowPage clickHereLink() {
		getDriver().findElementDynamic(selectorLink)
						.click();
		getDriver().waitForPageLoaded();
		return new NewWindowPage();
	}
}
