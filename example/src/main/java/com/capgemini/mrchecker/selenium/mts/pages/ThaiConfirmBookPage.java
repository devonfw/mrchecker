package com.capgemini.mrchecker.selenium.mts.pages;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class ThaiConfirmBookPage extends BasePage {
	
	private static final By	confirmationDialogSearch	= By.className("mat-dialog-container");
	private static final By	sendButtonSearch			= By.name("bookTableConfirm");
	
	@Override
	public boolean isLoaded() {
		return getDriver().findElementDynamic(confirmationDialogSearch)
				.isDisplayed();
	}
	
	@Override
	public void load() {
		BFLogger.logError("MyThaiStar booking confirmation page was not loaded.");
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
