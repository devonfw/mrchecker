package com.capgemini.mrchecker.selenium.pages.projectY;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.base.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.Button;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.InputTextElement;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.LabelElement;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class FormAuthenticationPage extends BasePage {
	
	private final static By	selectorInputUsername		= By.cssSelector("#username");
	private final static By	selectorInputUserPassword	= By.cssSelector("#password");
	private final static By	selectorLoginMessage		= By.cssSelector("#flash");
	private final static By	selectorLoginButton			= By.cssSelector("#login > button > i");
	private final static By	selectorLogoutButton		= By.cssSelector("#content > div > a ");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains(PageSubURLsProjectYEnum.LOGIN.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'Login Page' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.LOGIN.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
	}
	
	/**
	 * Sets user name to designated form's field.
	 * 
	 * @param username
	 *            String representing user's name
	 * @return FormAuthenticationPage object with user name set to given one
	 */
	public FormAuthenticationPage setUsername(String username) {
		InputTextElement elementInputUsername = new InputTextElement(selectorInputUsername);
		elementInputUsername.clearInputText();
		elementInputUsername.setInputText(username);
		return this;
	}
	
	/**
	 * Sets user password to designated form's field.
	 * 
	 * @param userPassword
	 *            String representing user's password
	 * @return FormAuthenticationPage object with user's password set to given one
	 */
	public FormAuthenticationPage setUserPassword(String userPassword) {
		InputTextElement elementInputPassword = new InputTextElement(selectorInputUserPassword);
		elementInputPassword.clearInputText();
		elementInputPassword.setInputText(userPassword);
		return this;
	}
	
	/**
	 * Returns login message.
	 * 
	 * @return String object representing message returned after login operation is performed
	 */
	public String getLoginMessageText() {
		return new LabelElement(selectorLoginMessage).getText();
	}
	
	/**
	 * Clicks 'Login' button.
	 */
	public void clickLoginButton() {
		new Button(selectorLoginButton).click();
	}
	
	/**
	 * Clicks 'Logout' button.
	 */
	public void clickLogoutButton() {
		new Button(selectorLogoutButton).click();
	}
}
