package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.By;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.CheckBox;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class CheckboxesPage extends BasePage {
	
	private final static By selectorCheckboxesForm = By.cssSelector("#checkboxes");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains(PageSubURLsProjectYEnum.CHECKBOX.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'Checkboxes' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.CHECKBOX.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
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
