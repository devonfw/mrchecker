package com.capgemini.mrchecker.selenium.mts.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.exceptions.BFElementNotFoundException;
import com.capgemini.mrchecker.selenium.mts.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

public class ThaiHomePage extends MyThaiStarBasePage {
	
	private static final String	mythaistarUrl			= GetEnvironmentParam.MAY_THAI_STAR_URL.getValue();
	private static final By		loginButtonSearch		= By.name("login");
	private static final By		logoutButtonSearch		= By.name("account");
	private static final By		labelLoginSearch		= By.xpath("//span[@data-name='userNameLogged']");
	private static final By		homeTabSearch			= By.xpath("//a[@routerlink='/home']");
	private static final By		menuTabSearch			= By.xpath("//a[@routerlink='/menu']");
	private static final By		bookTableButtonSearch	= By.xpath("//a[@routerlink='/bookTable']");
	
	@Override
	public void load() {
		getDriver().get(mythaistarUrl);
	}
	
	@Override
	protected By getDisplayableElementSelector() {
		return homeTabSearch;
	}
	
	@Override
	public String pageTitle() {
		return "My Thai Star";
	}
	
	public void clickLogInButton() {
		getDriver().findElementDynamic(loginButtonSearch)
				.click();
	}
	
	public void clickLogOutButton() {
		getDriver().findElementDynamic(logoutButtonSearch)
				.click();
		
		String scriptClick = "var we = document.getElementsByClassName(\"mat-menu-item\"); we[we.length-1].click();";
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript(scriptClick);
	}
	
	public void clickMenuButton() {
		getDriver().findElementDynamic(menuTabSearch)
				.click();
	}
	
	public void clickHomeButton() {
		getDriver().findElementDynamic(homeTabSearch)
				.click();
	}
	
	public boolean isUserLogged(String username) {
		try {
			List<WebElement> accessButton = getDriver().findElementDynamics(labelLoginSearch);
			if (accessButton.size() > 0 && accessButton.get(0)
					.getText()
					.equals(username)) {
				return true;
			}
		} catch (BFElementNotFoundException e) {
		}
		
		return false;
	}
	
	public boolean isUserLogged() {
		try {
			List<WebElement> accessButton = getDriver().findElementDynamics(labelLoginSearch, 3);
			if (accessButton.size() > 0 && accessButton.get(0)
					.getText()
					.length() > 0) {
				return true;
			}
		} catch (BFElementNotFoundException e) {
		}
		
		return false;
	}
	
	public ThaiBookPage clickBookTable() {
		getDriver().findElementDynamic(bookTableButtonSearch)
				.click();
		
		return PageFactory.getPageInstance(ThaiBookPage.class);
	}
	
	public void openTab(String tabName) {
	}
	
}
