package com.capgemini.mrchecker.selenium.mts.pages;

import org.openqa.selenium.By;

public class ThaiConfirmBookPage extends MyThaiStarBasePage {
	
	private static final By	selectorConfirmationDialogSearch	= By.className("mat-dialog-container");
	private static final By	selectorSendButtonSearch			= By.name("bookTableConfirm");
	
	@Override
	protected By getDisplayableElementSelector() {
		return selectorConfirmationDialogSearch;
	}
	
	@Override
	public String pageTitle() {
		return "";
	}
	
	public void confirmBookingData() {
		getDriver().findElementDynamic(selectorSendButtonSearch)
				.click();
	}
}
