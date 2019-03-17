package com.capgemini.mrchecker.selenium.pages.projectY.gmail;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;

public class GmailAboutPage extends BasePage {
	
	private static final By selectorSignInButton = By.cssSelector("a.gmail-nav__nav-link__sign-in");
	
	private static final String	GMAIL_ABOUT_URL			= "https://www.google.com/gmail/about/#";
	private static final String	GMAIL_ABOUT_PAGE_TITLE	= "Gmail - Free Storage and Email from Google";
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains("gmail/about/#");
	}
	
	@Override
	public void load() {
		getDriver().get(GMAIL_ABOUT_URL);
	}
	
	@Override
	public String pageTitle() {
		return GMAIL_ABOUT_PAGE_TITLE;
	}
	
	/**
	 * Performs left mouse button click on 'Sign in' button element.
	 * 
	 * @return new GmailSignInPage object.
	 */
	public GmailSignInPage clickSignInButton() {
		getDriver().elementButton(selectorSignInButton)
						.click();
		return new GmailSignInPage();
	}
	
}
