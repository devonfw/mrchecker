package com.capgemini.mrchecker.selenium.mts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.common.mts.data.Reservation;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.InputTextElement;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

public class ThaiBookPage extends MyThaiStarBasePage {
	
	private static final By		selectorDateSearch			= By.cssSelector("input[formcontrolname='bookingDate']");
	private static final By		selectorNameSearch			= By.cssSelector("input[formcontrolname='name']");
	private static final By		selectorEmailSearch			= By.cssSelector("input[formcontrolname='email']");
	private static final By		selectorGuestsSearch		= By.cssSelector("input[formcontrolname='assistants']");
	private static final By		selectorCheckboxSearch		= By.cssSelector("mat-checkbox[data-name='bookTableTerms']");
	private static final By		selectorDialogSearch		= By.className("bgc-green-600");
	private static final By		selectorMessageBar			= By.cssSelector(".mat-snack-bar-container");
	private static final String	TABLE_SUCCESSFULLY_BOOKED	= "Stolik zarezerwowany";
	
	@Override
	protected By getDisplayableElementSelector() {
		return selectorDateSearch;
	}
	
	@Override
	public String pageTitle() {
		return "";
	}
	
	public void enterTimeAndDate(String Date) {
		InputTextElement dateInput = getDriver().elementInputText(selectorDateSearch);
		dateInput.clearInputText();
		dateInput.setInputText(Date);
	}
	
	public void enterName(String name) {
		InputTextElement nameInput = getDriver().elementInputText(selectorNameSearch);
		nameInput.clearInputText();
		nameInput.setInputText(name);
	}
	
	public void enterEmail(String email) {
		InputTextElement emailInput = getDriver().elementInputText(selectorEmailSearch);
		emailInput.clearInputText();
		emailInput.setInputText(email);
	}
	
	public void enterGuests(String amountOfGuests) {
		InputTextElement guestsInput = getDriver().elementInputText(selectorGuestsSearch);
		guestsInput.clearInputText();
		guestsInput.setInputText(amountOfGuests);
	}
	
	public void enterGuests(int amountOfGuests) {
		enterGuests(String.valueOf(amountOfGuests));
	}
	
	public void acceptTerms() {
		WebElement checkbox = getDriver().findElementDynamic(selectorCheckboxSearch);
		WebElement square = checkbox.findElement(By.className("mat-checkbox-inner-container"));
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].click()", square);
	}
	
	public void acceptTerms(boolean accept) {
		WebElement checkbox = getDriver().findElementDynamic(selectorCheckboxSearch);
		String checked = checkbox.findElement(By.cssSelector(".mat-checkbox-input"))
				.getAttribute("aria-checked");
		if (accept != Boolean.parseBoolean(checked)) {
			acceptTerms();
		}
	}
	
	public ThaiConfirmBookPage clickBookTable() {
		getDriver().findElementDynamic(By.name("bookTableSubmit"))
				.click();
		return PageFactory.getPageInstance(ThaiConfirmBookPage.class);
	}
	
	public ThaiConfirmBookPage enterBookingData(Reservation reservation) {
		enterTimeAndDate(reservation.getDate());
		enterName(reservation.getName());
		enterEmail(reservation.getEmail());
		enterGuests(reservation.getGuestsNumber());
		acceptTerms();
		clickBookTable();
		
		return PageFactory.getPageInstance(ThaiConfirmBookPage.class);
	}
	
	public boolean checkConfirmationDialog() {
		return getDriver().findElementDynamic(selectorDialogSearch)
				.isDisplayed();
	}
	
	public boolean isBookingEnabled() {
		return getDriver().findElementDynamic(By.name("bookTableSubmit"))
				.isEnabled();
	}
	
	public boolean isSuccessMessageShown() {
		String message = getSnackbarMessage();
		
		int retry = 0;
		while (!message.startsWith(TABLE_SUCCESSFULLY_BOOKED) && retry < 10) {
			BFLogger.logInfo(message);
			message = getSnackbarMessage();
			retry++;
		}
		
		return message.startsWith(TABLE_SUCCESSFULLY_BOOKED);
	}
	
	private String getSnackbarMessage() {
		getDriver().waitForElementVisible(selectorMessageBar);
		WebElement messageBar = getDriver().findElementDynamic(selectorMessageBar)
				.findElement(By.cssSelector(".mat-simple-snackbar"));
		String message = messageBar.getText();
		return null == message ? "" : message;
	}
	
}
