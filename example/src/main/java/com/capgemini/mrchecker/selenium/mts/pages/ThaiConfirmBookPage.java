package com.capgemini.mrchecker.selenium.mts.pages;

import org.openqa.selenium.By;

public class ThaiConfirmBookPage extends MyThaiStarBasePage {
	
	private static final By	confirmationDialogSearch	= By.className("mat-dialog-container");
	private static final By	sendButtonSearch			= By.name("bookTableConfirm");
	
	@Override
	protected By getDisplayableElementSelector() {
		return confirmationDialogSearch;
	}
	
	@Override
	public String pageTitle() {
		return "";
	}
	
	public void confirmBookingData() {
		getDriver().findElementDynamic(sendButtonSearch)
				.click();
	}
}
