package com.capgemini.mrchecker.selenium.mts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.capgemini.mrchecker.common.mts.utils.Utils;

public class ThaiSummaryPage extends MyThaiStarBasePage {
	
	private static final By	selectorTextBoxSearch		= By.name("orderBookingID");
	private static final By	selectorCheckBoxSearch		= By.xpath("//mat-checkbox[@data-name='orderTerms']");
	private static final By	selectorAcceptButtonSearch	= By.name("orderSubmit");
	
	@Override
	protected By getDisplayableElementSelector() {
		return selectorTextBoxSearch;
	}
	
	@Override
	public String pageTitle() {
		return "";
	}
	
	public void orderMenu(String bookingId) {
		WebDriverWait driverWait = new WebDriverWait(getDriver(), 10);
		WebElement textBox = getDriver().findElementDynamic(selectorTextBoxSearch);
		WebElement checkBox = getDriver().findElementDynamic(selectorCheckBoxSearch);
		WebElement acceptButton = getDriver().findElementDynamic(selectorAcceptButtonSearch);
		
		Utils.sendKeysWithCheck(bookingId, selectorTextBoxSearch, getDriver(), getWebDriverWait());
		
		checkBox.click();
		acceptButton.click();
	}
	
}
