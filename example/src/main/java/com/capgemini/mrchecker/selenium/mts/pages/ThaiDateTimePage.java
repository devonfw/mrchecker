package com.capgemini.mrchecker.selenium.mts.pages;

import org.openqa.selenium.By;

public class ThaiDateTimePage extends MyThaiStarBasePage {
	
	private static final By		ticSearch	= By.className("owl-dt-control-button-content");
	private static final String	xpathArrow	= "//*[@id=\"owl-dt-picker-0\"]/div[2]/owl-date-time-timer/owl-date-time-timer-box[1]/button[1]";
	private static final By		arrowSearch	= By.xpath(xpathArrow);
	
	@Override
	protected By getDisplayableElementSelector() {
		return ticSearch;
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
