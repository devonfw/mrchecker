package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.By;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.base.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.Button;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.InputTextElement;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.LabelElement;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;

public class FormAuthenticationPage extends BasePage {
	
	private final String	validPageTitle				= "The Internet";
	private final static By	selectorInputUsername		= By.cssSelector("#username");
	private final static By	selectorInputUserPassword	= By.cssSelector("#password");
	private final static By	selectorLoginMessage		= By.cssSelector("#flash");
	private final static By	selectorLoginButton			= By.cssSelector("#login > button > i");
	private final static By	selectorLogoutButton		= By.cssSelector("#content > div > a ");
	
	@Override
	public boolean isLoaded() {
		return getDriver().getTitle()
						.equals(validPageTitle);
	}
	
	@Override
	public void load() {
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.LOGIN.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
	
	public FormAuthenticationPage setUsername(String username) {
		InputTextElement elementInputUsername = new InputTextElement(selectorInputUsername);
		elementInputUsername.clearInputText();
		elementInputUsername.setInputText(username);
		return this;
	}
	
	public FormAuthenticationPage setUserPassword(String userPassword) {
		InputTextElement elementInputPassword = new InputTextElement(selectorInputUserPassword);
		elementInputPassword.clearInputText();
		elementInputPassword.setInputText(userPassword);
		return this;
	}
	
	public String getLoginMessageText() {
		LabelElement loginMessageLabel = new LabelElement(selectorLoginMessage);
		return loginMessageLabel.getText();
	}
	
	public void clickLoginButton() {
		Button elementLoginButton = new Button(selectorLoginButton);
		elementLoginButton.click();
	}
	
	public void clickLogoutButton() {
		Button elementLogoutButton = new Button(selectorLogoutButton);
		elementLogoutButton.click();
	}
}
