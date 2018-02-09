package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.Button;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class TheInternetPage extends BasePage {
	
	private static final By	selectorCheckboxesLink			= By.cssSelector("li > a[href*=checkboxes]");
	private static final By	selectorElementClick			= By.cssSelector("li > a[href*=abtest]");
	private static final By	selectorBrokenImageClickLink	= By.cssSelector("li > a[href*=broken_images]");
	private static final By	selectorDropdownClickLink		= By.cssSelector("li > a[href*=dropdown]");
	private static final By	selectorMultipleWindowsLink		= By.cssSelector("li > a[href*=windows]");
	private static final By	selectorBasicAuthLink			= By.cssSelector("li > a[href*=basic_auth]");
	private static final By	selectorKeyPressesLink			= By.cssSelector("li > a[href*=key_presses]");
	private static final By	selectorJavaScriptAlertLink		= By.cssSelector("li > a[href*=javascript_alerts]");
	private static final By	selectorHoversLink				= By.cssSelector("li > a[href*=hovers]");
	private static final By	selectorChallengingDomClick		= By.cssSelector("li > a[href*=challenging_dom]");
	private static final By	selectorFormAuthenticationLink	= By.cssSelector("li > a[href*=login]");
	
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
		WebElement elementCheckbox = getDriver().findElementDynamic(selectorCheckboxesLink);
		elementCheckbox.click();
		return new CheckboxesPage();
	}
	
	public ABtestPage clickABtestingLink() {
		WebElement elementClickLink = getDriver().findElementDynamic(selectorElementClick);
		elementClickLink.click();
		return new ABtestPage();
	}
	
	public ChallengingDomPage clickChallengingDomLink() {
		WebElement elementClickLink = getDriver().findElementDynamic(selectorChallengingDomClick);
		elementClickLink.click();
		return new ChallengingDomPage();
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
	
	public KeyPressesPage clickKeyPressesLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorKeyPressesLink);
		elementLink.click();
		return new KeyPressesPage();
	}
	
	public JavaScriptAlertsPage clickJavaScriptAlertLink() {
		Button elementLink = new Button(selectorJavaScriptAlertLink);
		elementLink.click();
		return new JavaScriptAlertsPage();
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
	
	public HoversPage clickHoversLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorHoversLink);
		elementLink.click();
		return new HoversPage();
	}
	
	public FormAuthenticationPage clickFormAuthenticationLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorFormAuthenticationLink);
		elementLink.click();
		return new FormAuthenticationPage();
	}
	
}