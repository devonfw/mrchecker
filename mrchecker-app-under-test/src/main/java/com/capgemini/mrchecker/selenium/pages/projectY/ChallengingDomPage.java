package com.capgemini.mrchecker.selenium.pages.projectY;

import java.util.List;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.Button;
import com.capgemini.mrchecker.selenium.jsoupHelper.JsoupHelper;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class ChallengingDomPage extends TheInternetSubpage {
	
	private final By	selectorTableRows	= By.cssSelector(".large-10 > table > tbody > tr");
	private final By	selectorFirstButton	= By.cssSelector(".large-2.columns > .button:nth-child(1)");
	public final By		pageLinkSelector	= By.cssSelector("li > a[href*='challenging_dom']");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains(PageSubURLsProjectYEnum.CHALLENGING_DOM.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'Challenging DOM' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.CHALLENGING_DOM.getValue());
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
	 * Returns table text content as a list of String objects.
	 * 
	 * @return A list of table values.
	 */
	public List<String> getTableValues() {
		return JsoupHelper.findTexts(selectorTableRows);
	}
	
	/**
	 * Clicks top button on the page from available button set.
	 */
	public void clickFirstButton() {
		getDriver().elementButton(selectorFirstButton)
						.click();
		getDriver().waitForPageLoaded();
	}
	
}
