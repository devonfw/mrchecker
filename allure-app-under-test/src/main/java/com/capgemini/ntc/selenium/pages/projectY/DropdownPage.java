package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.By;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.base.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.DropdownListElement;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.ntc.test.core.logger.BFLogger;

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
