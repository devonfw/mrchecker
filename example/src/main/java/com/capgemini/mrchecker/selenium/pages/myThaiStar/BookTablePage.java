package com.capgemini.mrchecker.selenium.pages.myThaiStar;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.exceptions.BFElementNotFoundException;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsMyThaiStar;
import com.capgemini.mrchecker.selenium.pages.environment.PageTitlesEnumMyThaiStar;

public class BookTablePage extends BasePage {
	private static final By selectorDateInputBooking = By.cssSelector("input[id='mat-input-1']");
	
	private static final By selectorNameInputBooking = By.cssSelector("input[id='mat-input-2']");
	
	private static final By selectorEmailInputBooking = By.cssSelector("input[id='mat-input-3']");
	
	private static final By selectorGuestsNumberInput = By.cssSelector("input[id='mat-input-4']");
	
	private static final By selectorDateInputInvitation = By.cssSelector("input[id='mat-input-5']");
	
	private static final By selectorNameInputInvitation = By.cssSelector("input[id='mat-input-6']");
	
	private static final By selectorEmailInputInvitation = By.cssSelector("input[id='mat-input-7']");
	
	private static final By selectorInvitationEmailInput = By.cssSelector("input[id='mat-input-8']");
	
	private static final By selectorAcceptTermsCheckboxBooking = By.cssSelector("mat-checkbox[data-name='bookTableTerms']");
	
	private static By selectorBookTableButton = By.name("bookTableSubmit");
	
	private static final By selectorConfirmationDialog = By.className("bgc-green-600");
	
	private static final By selectorInviteFriendsTab = By.cssSelector("div[id='mat-tab-label-0-1']");
	
	private static final By selectorAcceptTermsCheckboxInvitation = By.cssSelector("mat-checkbox[data-name='inviteFriendsTerms']");
	
	private static By selectorInviteFriendsButton = By.name("inviteFriendsSubmit");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
				.equals(GetEnvironmentParam.MY_THAI_STAR_URL.getValue() + PageSubURLsMyThaiStar.BOOK_TABLE.getValue());
	}
	
	@Override
	public void load() {
		getDriver().get(GetEnvironmentParam.MY_THAI_STAR_URL.getValue() + PageSubURLsMyThaiStar.BOOK_TABLE.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return PageTitlesEnumMyThaiStar.MAIN_PAGE.toString();
	}
	
	public void enterTimeAndDateInputBooking(String date) {
		getDriver().waitForElement(selectorDateInputBooking);
		getDriver().findElementDynamic(selectorDateInputBooking)
				.sendKeys(date);
	}
	
	public void enterNameInputBooking(String name) {
		getDriver().findElementDynamic(selectorNameInputBooking)
				.sendKeys(name);
	}
	
	public void enterEmailInputBooking(String email) {
		getDriver().findElementDynamic(selectorEmailInputBooking)
				.sendKeys(email);
	}
	
	public void enterGuestsNumberInput(String amountOfGuests) {
		getDriver().findElementDynamic(selectorGuestsNumberInput)
				.sendKeys(amountOfGuests);
	}
	
	public void clickAcceptTermsCheckboxBooking() {
		WebElement checkbox = getDriver().findElementDynamic(selectorAcceptTermsCheckboxBooking);
		WebElement square = checkbox.findElement(By.className("mat-checkbox-inner-container"));
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].click()", square);
	}
	
	public void clickBookTableButton() {
		getDriver().waitUntilElementIsClickable(selectorBookTableButton);
		getDriver().findElementDynamic(selectorBookTableButton)
				.click();
	}
	
	public boolean isConfirmationDialogDisplayed() {
		return getDriver().findElementQuietly(selectorConfirmationDialog)
				.isDisplayed();
	}
	
	public ConfirmBookPage enterBookingDataAndBookTable(String date, String name, String email, String guests) {
		
		enterBookingData(date, name, email, guests);
		
		clickBookTableButton();
		
		return new ConfirmBookPage();
	}
	
	public void enterBookingData(String date, String name, String email, String guests) {
		
		enterTimeAndDateInputBooking(date);
		enterNameInputBooking(name);
		enterEmailInputBooking(email);
		enterGuestsNumberInput(guests);
		
		clickAcceptTermsCheckboxBooking();
	}
	
	public void clickInviteFriendsTab() {
		getDriver().findElementDynamic(selectorInviteFriendsTab)
				.click();
	}
	
	public void enterInvitationEmailInput(String email) {
		
		getDriver().findElementDynamic(selectorInvitationEmailInput)
				.sendKeys(email);
		
		getDriver().findElementDynamic(selectorInvitationEmailInput)
				.sendKeys(Keys.ENTER);
		
	}
	
	public void clickAcceptTermsCheckboxInvitation() {
		WebElement checkbox = getDriver().findElementDynamic(selectorAcceptTermsCheckboxInvitation);
		WebElement square = checkbox.findElement(By.className("mat-checkbox-inner-container"));
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].click()", square);
	}
	
	public void clickInviteFriendsButton() {
		getDriver().waitUntilElementIsClickable(selectorInviteFriendsButton);
		getDriver().findElementDynamic(selectorInviteFriendsButton)
				.click();
	}
	
	public void enterTimeAndDateInputInvitation(String date) {
		getDriver().findElementDynamic(selectorDateInputInvitation)
				.sendKeys(date);
	}
	
	public void enterNameInputInvitation(String name) {
		getDriver().findElementDynamic(selectorNameInputInvitation)
				.sendKeys(name);
	}
	
	public void enterEmailInputInvitation(String email) {
		getDriver().findElementDynamic(selectorEmailInputInvitation)
				.sendKeys(email);
	}
	
	public boolean isBookTableButtonPresent() {
		try {
			getDriver().waitUntilElementIsClickable(selectorBookTableButton);
			
			getDriver().findElementDynamic(selectorBookTableButton);
		} catch (BFElementNotFoundException e) {
			return false;
		}
		return true;
	}
	
	public boolean isInviteFriendsButtonPresent() {
		try {
			getDriver().waitUntilElementIsClickable(selectorInviteFriendsButton);
			
			getDriver().findElementDynamic(selectorInviteFriendsButton);
		} catch (BFElementNotFoundException e) {
			return false;
		}
		return true;
	}
}
