package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class TheInternetPage extends BasePage {
	
	private static final By	selectorCheckboxesLink			= By.cssSelector("#content > ul > li:nth-child(5) > a");
	private static final By	selectorElementClick			= By.cssSelector("li:nth-child(1) > a");
	private static final By	selectorBrokenImageClickLink	= By.cssSelector("li:nth-child(3) > a");
	private static final By	selectorDropdownClickLink		= By.cssSelector("li:nth-child(9) > a");
	private static final By	selectorMultipleWindowsLink		= By.cssSelector("#content > ul > li:nth-child(29) > a");
	private static final By	selectorBasicAuthLink			= By.cssSelector("li:nth-child(2) > a");
	
	@Override
	public boolean isLoaded() {
		BFLogger.logDebug("The internet page is loaded.");
		return getDriver().getCurrentUrl()
						.equals("http://the-internet.herokuapp.com/");
		
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("load page");
		getDriver().get("http://the-internet.herokuapp.com");
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
	
	public CheckboxesPage clickCheckboxesLink() {
		WebElement elementCheckbox = getDriver().findElementDynamic(selectorCheckboxesLink);
		elementCheckbox.click();
		return new CheckboxesPage();
	}
	
	public ABtestPage clickABtestingLink() {
		WebElement elementClickLink = getDriver().findElementDynamic(selectorElementClick);
		elementClickLink.click();
		return new ABtestPage();
	}
	
	public BrokenImagePage clickBrokenImageLink() {
		WebElement elementClickLink = getDriver().findElementDynamic(selectorBrokenImageClickLink);
		elementClickLink.click();
		return new BrokenImagePage();
	}
	
	public DropdownPage clickDropdownLink() {
		WebElement elementClickLink = getDriver().findElementDynamic(selectorDropdownClickLink);
		elementClickLink.click();
		return new DropdownPage();
	}
	
	public MultipleWindowsPage clickmultipleWindowsPageLink() {
		WebElement elementClickLink = getDriver().findElementDynamic(selectorMultipleWindowsLink);
		elementClickLink.click();
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