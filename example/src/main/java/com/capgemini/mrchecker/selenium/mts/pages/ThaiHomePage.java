package com.capgemini.mrchecker.selenium.mts.pages;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.enums.ResolutionEnum;
import com.capgemini.mrchecker.selenium.core.exceptions.BFElementNotFoundException;
import com.capgemini.mrchecker.selenium.core.utils.ResolutionUtils;
import com.capgemini.mrchecker.selenium.mts.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

import io.qameta.allure.Step;

public class ThaiHomePage extends MyThaiStarBasePage {
	
	private static final int	TIME_FOR_LOGOUT				= 5000;
	private static final String	myThaiStarUrl				= GetEnvironmentParam.MY_THAI_STAR_URL.getValue();
	private static final By		selectorLoginButtonSearch	= By.name("login");
	private static final By		selectorLogoutButtonSearch	= By.name("account");
	private static final By		selectorLabelLoginSearch	= By.xpath("//span[@data-name='userNameLogged']");
	private static final By		selectorHomeLink			= By.xpath("//a[@routerlink='/home']");
	private static final By		selectorMenuLink			= By.xpath("//a[@routerlink='/menu']");
	private static final By		selectorBookTableButton		= By.xpath("//a[@routerlink='/bookTable']");
	
	@Override
	public void load() {
		getDriver().manage()
				.deleteAllCookies();
		
		doLoad();
		ResolutionUtils.setResolution(getDriver(), ResolutionEnum.w1280);
		
	}
	
	@Step("Loading ThaiHomePage")
	private void doLoad() {
		getDriver().get(myThaiStarUrl);
	}
	
	@Override
	protected By getDisplayableElementSelector() {
		return selectorLoginButtonSearch;
	}
	
	@Override
	public String pageTitle() {
		return "My Thai Star";
	}
	
	@Step("Click log in button")
	public void clickLogInButton() {
		getDriver().findElementDynamic(selectorLoginButtonSearch)
				.click();
	}
	
	@Step("Click log out button")
	public void clickLogOutButton() {
		getDriver().findElementDynamic(selectorLogoutButtonSearch)
				.click();
		
		String scriptClick = "var we = document.getElementsByClassName(\"mat-menu-item\"); we[we.length-1].click();";
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript(scriptClick);
		
		waitUntilUserLoggedOut();
	}
	
	@Step("Wait for logging out")
	public void waitUntilUserLoggedOut() {
		if (!isUserLogged()) {
			return;
		}
		long timeBegin = System.currentTimeMillis();
		long timeCounter = System.currentTimeMillis() - timeBegin;
		while (timeCounter < TIME_FOR_LOGOUT) {
			boolean isDisplayed = getDriver().findElementDynamic(selectorLabelLoginSearch, 0)
					.isDisplayed();
			if (!(isDisplayed)) {
				return;
			}
			timeCounter = System.currentTimeMillis() - timeBegin;
		}
		fail("User is still logged.");
	}
	
	@Step("Click menu button")
	public void clickMenuButton() {
		getDriver().findElementDynamic(selectorMenuLink)
				.click();
	}
	
	@Step("Click home button")
	public void clickHomeButton() {
		getDriver().findElementDynamic(selectorHomeLink)
				.click();
	}
	
	@Step("Check if {username} is logged")
	public boolean isUserLogged(String username) {
		if (Objects.isNull(username))
			return false;
		try {
			List<WebElement> accessButton = getDriver().findElementDynamics(selectorLabelLoginSearch, 5);
			if (accessButton.size() > 0 && accessButton.get(0)
					.getText()
					.equals(username)) {
				makeScreenShot();
				return true;
			}
		} catch (BFElementNotFoundException e) {
		}
		
		return false;
	}
	
	public boolean isUserLogged() {
		try {
			List<WebElement> accessButton = getDriver().findElementDynamics(selectorLabelLoginSearch, 3);
			if (accessButton.size() > 0 && accessButton.get(0)
					.getText()
					.length() > 0) {
				return true;
			}
		} catch (BFElementNotFoundException e) {
		}
		
		return false;
	}
	
	@Step("Click book table button")
	public ThaiBookPage clickBookTable() {
		getDriver().findElementDynamic(selectorBookTableButton)
				.click();
		
		return PageFactory.getPageInstance(ThaiBookPage.class);
	}
	
}
