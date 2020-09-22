package com.capgemini.mrchecker.selenium.mts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.mts.common.utils.Utils;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class ThaiSummaryPage extends BasePage {
	
	private static final By	textBoxSearch		= By.name("orderBookingID");
	private static final By	checkBoxSearch		= By.xpath("//mat-checkbox[@data-name='orderTerms']");
	private static final By	acceptButtonSearch	= By.name("orderSubmit");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForElementVisible(textBoxSearch);
		
		return true;
	}
	
	@Override
	public void load() {
		BFLogger.logError("MyThaiStar menu page was not loaded.");
	}
	
	@Override
	public String pageTitle() {
		return "";
	}
	
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
