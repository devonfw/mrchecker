package com.capgemini.mrchecker.selenium.mts.pages;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class ThaiDateTimePage extends BasePage {
	
	private static final By		ticSearch	= By.className("owl-dt-control-button-content");
	private static final String	xpathArrow	= "//*[@id=\"owl-dt-picker-0\"]/div[2]/owl-date-time-timer/owl-date-time-timer-box[1]/button[1]";
	private static final By		arrowSearch	= By.xpath(xpathArrow);
	
	@Override
	public boolean isLoaded() {
		return getDriver().findElementDynamic(ticSearch)
				.isDisplayed();
	}
	
	@Override
	public void load() {
		BFLogger.logError("MyThaiStar page for introducing data and time was not loaded.");
	}
	
	@Override
	public String pageTitle() {
		return "";
	}
	
	public void setUpDateAndTime() {
		getDriver().findElementDynamic(arrowSearch)
				.click();
		getDriver().findElementDynamic(ticSearch)
				.click();
		;
	}
}
