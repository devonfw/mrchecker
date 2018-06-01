package com.capgemini.mrchecker.selenium.pages.projectY.gmail;

import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class GmailWelcomePage extends BasePage {
	
	private static final By	selectorPasswordInputField	= By.cssSelector("div#password input");
	private static final By	selectorNextButton			= By.cssSelector("div#passwordNext > content");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains("signin/v2/sl/pwd") && pageTitle().equals("Gmail");
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("load page");
		
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
	
	/**
	 * Inserts a password into text input web element.
	 * 
	 * @param password
	 *            A password given by user.
	 * @return this.
	 */
	public GmailWelcomePage enterPassword(String password) {
		getDriver().elementInputText(selectorPasswordInputField)
						.setInputText(password);
		return this;
	}
	
	/**
	 * Performs left mouse button click on 'Next' button element.
	 * 
	 * @return new GmailInboxPage object.
	 */
	public GmailInboxPage clickNextButton() {
		getDriver().elementButton(selectorNextButton)
						.click();
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until((Function<? super WebDriver, Boolean>) ExpectedConditions.urlContains("#inbox"));
		return new GmailInboxPage();
	}
	
}
