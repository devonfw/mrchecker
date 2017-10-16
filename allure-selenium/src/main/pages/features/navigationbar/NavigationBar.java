package com.example.selenium.pages.features.navigationbar;

import com.example.selenium.core.BasePage;
import com.example.selenium.core.newDrivers.INewWebDriver;
import com.example.core.logger.BFLogger;
import com.example.selenium.pages.enums.PageSubURLsEnum;
import com.example.selenium.pages.enums.PageTitlesEnum;
import com.example.selenium.pages.features.MainPage;

import org.openqa.selenium.By;

public class NavigationBar extends BasePage {

	private static final By selectorNavBarHome = By.cssSelector("li[id='menu-item-38']");

	public NavigationBar(INewWebDriver driver, MainPage parent) {
		super(driver, parent);
	}

	@Override
	public boolean isLoaded() {
		return isUrlAndPageTitleAsCurrentPage(PageSubURLsEnum.MAIN_PAGE);
	}

	@Override
	public void load() {
	}

	@Override
	public String pageTitle() {
		return PageTitlesEnum.MAIN_PAGE.toString();
	}

	public void clickNavBarHome() {
		getDriver().elementButton(selectorNavBarHome).click();
		BFLogger.logInfo("Clicking on Navigation bar link - Home");
		getDriver().waitForPageLoaded();
	}

}
