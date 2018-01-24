package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.Button;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class TheInternetPage extends BasePage {
	
	private static final By	selectorCheckboxesLink			= By.cssSelector("#content > ul > li:nth-child(5) > a");
	private static final By	selectorElementClick			= By.cssSelector("li:nth-child(1) > a");
	private static final By	selectorBrokenImageClickLink	= By.cssSelector("li:nth-child(3) > a");
	private static final By	selectorDropdownClickLink		= By.cssSelector("li:nth-child(9) > a");
	private static final By	selectorMultipleWindowsLink		= By.cssSelector("li > a[href*=windows]");
	private static final By	selectorBasicAuthLink			= By.cssSelector("li:nth-child(2) > a");
	private static final By	selectorKeyPressesLink			= By.cssSelector("li > a[href*=key_presses]");
	private static final By	selectorDynamicContent			= By.cssSelector("li > a[href*=dynamic_content]");
	
	@Override
	public boolean isLoaded() {
		BFLogger.logDebug("The internet page is loaded: " + getDriver().getCurrentUrl());
		return getDriver().getCurrentUrl()
						.equals(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue());
		
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("load page");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
	
	public CheckboxesPage clickCheckboxesLink() {
		Button elementLink = new Button(selectorCheckboxesLink);
		elementLink.click();
		return new CheckboxesPage();
	}
	
	public ABtestPage clickABtestingLink() {
		Button elementLink = new Button(selectorElementClick);
		elementLink.click();
		return new ABtestPage();
	}
	
	public BrokenImagePage clickBrokenImageLink() {
		Button elementLink = new Button(selectorBrokenImageClickLink);
		elementLink.click();
		return new BrokenImagePage();
	}
	
	public DropdownPage clickDropdownLink() {
		Button elementLink = new Button(selectorDropdownClickLink);
		elementLink.click();
		return new DropdownPage();
	}
	
	public KeyPressesPage clickKeyPressesLink() {
		Button elementLink = new Button(selectorKeyPressesLink);
		elementLink.click();
		return new KeyPressesPage();
	}
	
	public DynamicContentPage clickDynamicContentPage() {
		Button elementLink = new Button(selectorDynamicContent);
		elementLink.click();
		return new DynamicContentPage();
	}
	
	public MultipleWindowsPage clickmultipleWindowsPageLink() {
		Button elementLink = new Button(selectorMultipleWindowsLink);
		elementLink.click();
		return new MultipleWindowsPage();
	}
	
	public TheBasicAuthPage clickBasicAuthLink() {
		getDriver().waitForPageLoaded();
		WebElement link = getDriver().findElementDynamic(selectorBasicAuthLink);
		JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		executor.executeScript("var elem=arguments[0]; setTimeout(function() {elem.click();}, 100)", link);
		return new TheBasicAuthPage();
	}
}