package com.capgemini.mrchecker.selenium.pages.projectY;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.base.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.DropdownListElement;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class DropdownPage extends BasePage {
	
	private static final By selectorDropdownList = By.cssSelector("#dropdown");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains(PageSubURLsProjectYEnum.DROPDOWN.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'Dropdown List' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.DROPDOWN.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
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
