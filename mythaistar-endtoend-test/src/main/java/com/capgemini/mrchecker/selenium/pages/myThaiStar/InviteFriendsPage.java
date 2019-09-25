package com.capgemini.mrchecker.selenium.pages.myThaiStar;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.environment.PageSubURLsMyThaiStar;
import com.capgemini.mrchecker.selenium.environment.PageTitlesEnumMyThaiStar;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class InviteFriendsPage extends BasePage {
	
	private static final By selectorDateInputInvitation = By.cssSelector("input[id='mat-input-5']");
	
	private static final By selectorNameInputInvitation = By.cssSelector("input[id='mat-input-6']");
	
	private static final By selectorEmailInputInvitation = By.cssSelector("input[id='mat-input-7']");
	
	private static final By selectorInvitationEmailInput = By.cssSelector("input[id='mat-input-8']");
	
	private static final By selectorConfirmationDialog = By.className("bgc-green-600");
	
	private static final By selectorInviteFriendsTab = By.cssSelector("div[id='mat-tab-label-0-1']");
	
	private static final By selectorAcceptTermsCheckboxInvitation = By.cssSelector("mat-checkbox[data-name='inviteFriendsTerms']");
	
	private static By selectorInviteFriendsButton = By.name("inviteFriendsSubmit");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForElementVisible(selectorAcceptTermsCheckboxInvitation);
		return getDriver().getCurrentUrl()
				.equals(GetEnvironmentParam.MY_THAI_STAR_URL.getValue() + PageSubURLsMyThaiStar.BOOK_TABLE.getValue())
				&& getDriver().findElementDynamic(selectorInviteFriendsTab)
						.getAttribute("aria-selected")
						.equals("true");
	}
	
	@Override
	public void load() {
		BFLogger.logError("Invite friends tab is not selected");
	}
	
	@Override
	public String pageTitle() {
		return PageTitlesEnumMyThaiStar.MAIN_PAGE.toString();
	}
	
	public void enterInvitationEmailInput(String email) {
		
		getDriver().findElementDynamic(selectorInvitationEmailInput)
				.sendKeys(email, Keys.ENTER);
		
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
	
	public boolean isConfirmationDialogDisplayed() {
		return getDriver().findElementQuietly(selectorConfirmationDialog)
				.isDisplayed();
	}
	
	public boolean isInviteFriendsButtonClickable() {
		try {
			getWebDriverWait().withTimeout(100, TimeUnit.MILLISECONDS)
					.until(ExpectedConditions.elementToBeClickable(selectorInviteFriendsButton));
		} catch (TimeoutException e) {
			
		}
		return getDriver().findElementDynamic(selectorInviteFriendsButton)
				.isEnabled();
	}
	
}
