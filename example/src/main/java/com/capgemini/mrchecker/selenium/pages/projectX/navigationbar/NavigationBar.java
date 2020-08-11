package com.capgemini.mrchecker.selenium.pages.projectX.navigationbar;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.newDrivers.INewWebDriver;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectXEnum;
import com.capgemini.mrchecker.selenium.pages.environment.PageTitlesEnum;
import com.capgemini.mrchecker.selenium.pages.projectX.MainPage;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import org.openqa.selenium.By;

public class NavigationBar extends BasePage {

	private static final By selectorNavBarHome = By.cssSelector("li[id='menu-item-38']");

	public NavigationBar(MainPage parent) {
		super(parent);
	}

	@Override
	public boolean isLoaded() {
		return isUrlAndPageTitleAsCurrentPage(GetEnvironmentParam.WWW_FONT_URL.getValue() + PageSubURLsProjectXEnum.MAIN_PAGE.getValue());
	}

	@Override
	public void load() {
	}

	@Override
	public String pageTitle() {
		return PageTitlesEnum.MAIN_PAGE.toString();
	}

	public void clickNavBarHome() {
		getDriver().elementButton(selectorNavBarHome)
				.click();
		BFLogger.logInfo("Clicking on Navigation bar link - Home");
		getDriver().waitForPageLoaded();
	}

}
