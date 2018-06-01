package com.capgemini.mrchecker.selenium.pages.projectY.gmail;

import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class GmailSignInPage extends BasePage {
	
	private static final By	selectorEmailInputField	= By.id("identifierId");
	private static final By	selectorNextButton		= By.cssSelector("div#identifierNext > content");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains("signin/v2/identifier") && pageTitle().equals("Gmail");
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
	 * Inserts an email address into text input web element.
	 * 
	 * @param emailAddress
	 *            An email address given by user.
	 * @return this.
	 */
	public GmailSignInPage enterEmailAddress(String emailAddress) {
		getDriver().elementInputText(selectorEmailInputField)
						.setInputText(emailAddress);
		return this;
	}
	
	/**
	 * Performs left mouse button click on 'Next' button element.
	 * 
	 * @return new GmailWelcomePage object.
	 */
	public GmailWelcomePage clickNextButton() {
		getDriver().elementButton(selectorNextButton)
						.click();
		WebDriverWait wait = new WebDriverWait(getDriver(), 2);
		wait.until((Function<? super WebDriver, Boolean>) ExpectedConditions.urlContains("pwd"));
		return new GmailWelcomePage();
	}
	
}
