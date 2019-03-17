package com.capgemini.mrchecker.selenium.pages.projectX.registration;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectXEnum;
import com.capgemini.mrchecker.selenium.pages.environment.PageTitlesEnum;

public class RegistryPage extends BasePage {
	private final static By selectorSubmitButton = By.cssSelector("input[id='name_3_firstname']");
	private boolean loaded;
	
	@Override
	public boolean isLoaded() {
		return isUrlAndPageTitleAsCurrentPage(GetEnvironmentParam.WWW_FONT_URL.getValue() + PageSubURLsProjectXEnum.REGISTRATION.getValue());
	}
	
	@Override
	public void load() {
		BasePage.getDriver()
				.get(GetEnvironmentParam.WWW_FONT_URL.getValue() + PageSubURLsProjectXEnum.REGISTRATION.getValue());
		getDriver().waitForPageLoaded();
		loaded = true;
	}
	
	@Override
	public String pageTitle() {
		return PageTitlesEnum.REGISTRATION.toString();
	}
	
	public void clickSubmit() {
		getDriver().elementButton(selectorSubmitButton)
				.click();
	}
	
	public boolean isButtonSubmitDisplayed() {
		return getDriver().elementButton(selectorSubmitButton)
				.isDisplayed();
	}
}
