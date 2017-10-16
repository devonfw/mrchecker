package com.example.selenium.pages.features.registration;

import com.example.selenium.core.BasePage;
import com.example.selenium.pages.enums.PageSubURLsEnum;
import com.example.selenium.pages.enums.PageTitlesEnum;

import org.openqa.selenium.By;

public class RegistryPage extends BasePage {
	private final static By selectorSubmitButton = By.cssSelector("input[id='name_3_firstname']");
	private boolean loaded;

	@Override
	public boolean isLoaded() {
		return isUrlAndPageTitleAsCurrentPage(PageSubURLsEnum.REGISTRATION);
	}
	
	@Override
	public void load() {
		BasePage.getDriver().get(PageSubURLsEnum.WWW_FONT_URL.subURL() + PageSubURLsEnum.REGISTRATION.subURL());
		loaded = true;
	}

	@Override
	public String pageTitle() {
		return PageTitlesEnum.REGISTRATION.toString();
	}


	public void clickSubmit() {
		getDriver().elementButton(selectorSubmitButton).click();
	}

	public boolean isButtonSubmitDisplayed() {
		return getDriver().elementButton(selectorSubmitButton).isDisplayed();
	}
}
