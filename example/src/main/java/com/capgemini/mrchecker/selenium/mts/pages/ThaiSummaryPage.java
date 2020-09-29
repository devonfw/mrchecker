package com.capgemini.mrchecker.selenium.mts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.capgemini.mrchecker.common.mts.utils.Utils;

import io.qameta.allure.Step;

public class ThaiSummaryPage extends MyThaiStarBasePage {
	
	private static final By	textBoxSearch		= By.name("orderBookingID");
	private static final By	checkBoxSearch		= By.xpath("//mat-checkbox[@data-name='orderTerms']");
	private static final By	acceptButtonSearch	= By.name("orderSubmit");
	
	@Override
	protected By getDisplayableElementSelector() {
		return textBoxSearch;
	}
	
	@Override
	public String pageTitle() {
		return "";
	}
	
	@Step("Order menu")
	public void orderMenu(String bookingId) {
		WebDriverWait driverWait = new WebDriverWait(getDriver(), 10);
		WebElement textBox = getDriver().findElementDynamic(textBoxSearch);
		WebElement checkBox = getDriver().findElementDynamic(checkBoxSearch);
		WebElement acceptButton = getDriver().findElementDynamic(acceptButtonSearch);
		Utils.sendKeysWithCheck(bookingId, textBoxSearch, getDriver(), getWebDriverWait());
		
		checkBox.click();
		acceptButton.click();
	}
	
}
