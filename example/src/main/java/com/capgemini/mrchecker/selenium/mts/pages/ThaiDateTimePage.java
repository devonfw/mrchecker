package com.capgemini.mrchecker.selenium.mts.pages;

import org.openqa.selenium.By;

public class ThaiDateTimePage extends MyThaiStarBasePage {
	
	private static final By	selectorTicSearch	= By.className("owl-dt-control-button-content");
	private static final By	selectorArrowSearch	= By.xpath("//*[@id=\\\"owl-dt-picker-0\\\"]/div[2]/owl-date-time-timer/owl-date-time-timer-box[1]/button[1]");
	
	@Override
	protected By getDisplayableElementSelector() {
		return selectorTicSearch;
	}
	
	@Override
	public String pageTitle() {
		return "";
	}
	
	public void setUpDateAndTime() {
		getDriver().findElementDynamic(selectorArrowSearch)
				.click();
		getDriver().findElementDynamic(selectorTicSearch)
				.click();
		;
	}
}
