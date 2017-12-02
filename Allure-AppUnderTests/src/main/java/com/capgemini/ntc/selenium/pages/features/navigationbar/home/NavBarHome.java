package com.capgemini.ntc.selenium.pages.features.navigationbar.home;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsEnum;
import com.capgemini.ntc.selenium.pages.environment.PageTitlesEnum;

public class NavBarHome extends BasePage {

	public NavBarHome(BasePage parent) {
		super(getDriver(), parent);
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

}
