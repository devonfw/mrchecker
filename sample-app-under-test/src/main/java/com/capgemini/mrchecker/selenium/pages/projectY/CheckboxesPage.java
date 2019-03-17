package com.capgemini.mrchecker.selenium.pages.projectY;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.CheckBox;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class CheckboxesPage extends BasePage {
	
	private final static By checkboxesFormSelector = By.cssSelector("#checkboxes");
	
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
	
	/**
	 * Verifies if checkbox form is visible on the page.
	 * 
	 * @return true if checkboxes are present and displayed on the page
	 */
	public boolean isElementCheckboxesFormVisible() {
		return getDriver().elementCheckbox(checkboxesFormSelector)
						.isDisplayed();
	}
	
	/**
	 * Verifies if given checkbox is selected or not.
	 * 
	 * @param index
	 *            The index of given checkbox
	 * @return true if given checkbox is selected
	 */
	public boolean isCheckboxSelected(int index) {
		return getDriver().elementCheckbox(checkboxesFormSelector)
						.isCheckBoxSetByIndex(index);
	}
	
	/**
	 * Selects given checkbox. Unselects, if it is already selected.
	 * 
	 * @param index
	 *            The index of given checkbox
	 */
	public void selectCheckbox(int index) {
		CheckBox checkbox = getDriver().elementCheckbox(checkboxesFormSelector);
		if (isCheckboxSelected(index)) {
			checkbox.unsetCheckBoxByIndex(index);
		} else {
			checkbox.setCheckBoxByIndex(index);
		}
	}
	
}
