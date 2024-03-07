package com.capgemini.pages.demoqaforms;

import com.capgemini.framework.actions.ActionGui;
import com.capgemini.framework.environment.csvEnv.GetEnvironmentParam;
import com.capgemini.framework.logger.AllureStepLogger;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;

import static com.capgemini.framework.playwright.PlaywrightFactory.getPage;

public class DemoQALoginPage{
	protected final      ActionGui actionGui            = new ActionGui();
	private static final int       PAGE_LOADING_TIMEOUT = 20000;
	private final        String  url                  = GetEnvironmentParam.DEMO_QA_LOGIN_URL.getValue();
	private              Locator locatorUsernameInput;
	private              Locator locatorPasswordInput;
	private              Locator locatorLoginButton;
	//or just with selector
	private static final String SELECTOR_LOGIN_Button                          = "button[id='login']";
	private              Locator locatorLabelElement;

	
	
	
	//For every new playwright browserContext / new test you have to define refresh locators
	public void initLocatorsForNewBrowserContext() {
		var page = getPage();
		locatorUsernameInput =page.getByPlaceholder("UserName");
		locatorPasswordInput = page.getByPlaceholder("Password");
		locatorLoginButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
		//or just with selector
		//selectorLoginButton = page.locator(SELECTOR_LOGIN_Button);
		locatorLabelElement = page.getByText("Invalid username or password!");
	}
	
	public void startPage() {
		initLocatorsForNewBrowserContext();
		getPage().navigate(url, new Page.NavigateOptions().setTimeout(PAGE_LOADING_TIMEOUT));
		getPage().onLoad(p -> AllureStepLogger.step("Page loaded!"));
		getPage().getByLabel("Consent", new Page.GetByLabelOptions().setExact(true))
				.click();

	}
	
	@Step("Fill username: {username}")
	public void fillUsername(String username) {
		locatorUsernameInput.fill(username);
	}
	
	@Step("Fill password")
	public void fillPassword(String password) {
		locatorPasswordInput.fill(password);
	}
	
	@Step("Click login button")
	public void clickLoginButton() {
		locatorLoginButton.click();
	}
	
	public String getOutputText() {
		return locatorLabelElement.innerText();
	}
	
	public boolean isDisplayedUsernameInput() {
		return locatorUsernameInput.isVisible();
	}
	
	public boolean isDisplayedPasswordInput() {
		return locatorPasswordInput.isVisible();
	}
	
	public boolean isDisplayedLoginButton() {
		return locatorLoginButton.isVisible();
	}
}