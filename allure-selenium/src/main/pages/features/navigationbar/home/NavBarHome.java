package com.example.selenium.pages.features.navigationbar.home;

import com.example.selenium.core.BasePage;
import com.example.selenium.pages.enums.PageSubURLsEnum;
import com.example.selenium.pages.enums.PageTitlesEnum;

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
