package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.By;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.DropdownListElement;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class DropdownPage extends BasePage {
	
	private static final By selectorDropdownList = By.cssSelector("#dropdown");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains("dropdown");
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("load()");
	}
	
	@Override
	public String pageTitle() {
		return "The Internet";
	}
	
	public void setValueOnDropdownList(int valueOfDropdown) {
		DropdownListElement dropdown = getDriver().elementDropdownList(selectorDropdownList);
		dropdown.selectDropdownByIndex(valueOfDropdown);
	}
	
	public String getTextFromDropdownList() {
		DropdownListElement dropdown = getDriver().elementDropdownList(selectorDropdownList);
		return dropdown.getFirstSelectedOptionText();
	}
}
