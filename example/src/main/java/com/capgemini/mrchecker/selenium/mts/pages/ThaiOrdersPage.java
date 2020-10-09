package com.capgemini.mrchecker.selenium.mts.pages;

import org.openqa.selenium.By;

public class ThaiOrdersPage extends MyThaiStarBasePage {
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		
		return getDriver().getCurrentUrl()
				.contains("orders");
	}
	
	@Override
	protected By getDisplayableElementSelector() {
		return null;
	}
	
	@Override
	public String pageTitle() {
		return "";
	}
	
}
