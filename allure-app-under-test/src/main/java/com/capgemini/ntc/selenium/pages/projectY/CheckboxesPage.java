package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.By;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.CheckBox;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class CheckboxesPage extends BasePage {
	
	private final static By selectorCheckboxesForm = By.cssSelector("#checkboxes");
	
	@Override
	public boolean isLoaded() {
		BFLogger.logDebug("The checkboxes page is loaded.");
		return getDriver().getCurrentUrl()
						.equals("http://the-internet.herokuapp.com/checkboxes");
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("load");
		getDriver().get("http://the-internet.herokuapp.com/checkboxes");
		getDriver().waitForPageLoaded();
		
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
	
	public boolean isElementCheckboxesVisible() {
		getDriver().elementCheckbox(selectorCheckboxesForm)
						.isDisplayed();
		return true;
	}
	
	public boolean isCheckboxSelectedBefore(int index) {
		CheckBox elementCheckbox = getDriver().elementCheckbox(selectorCheckboxesForm);
		return elementCheckbox.isCheckBoxSetByIndex(index);
	}
	
	public void thickCheckbox(int index) {
		getDriver().elementCheckbox(selectorCheckboxesForm)
						.setCheckBoxByIndex(index);
	}
	
	public boolean isCheckboxSelectedAfter(int index) {
		CheckBox elementCheckbox = getDriver().elementCheckbox(selectorCheckboxesForm);
		return elementCheckbox.isCheckBoxSetByIndex(index);
	}
	
	public void unthickCheckbox(int index) {
		getDriver().elementCheckbox(selectorCheckboxesForm)
						.unsetCheckBoxByIndex(index);
	}
	
}
