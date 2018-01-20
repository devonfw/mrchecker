package com.capgemini.ntc.selenium.pages.projectX.navigationbar.home;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsToolsQAEnum;
import com.capgemini.ntc.selenium.pages.environment.PageTitlesEnum;

public class NavBarHome extends BasePage {
	
	public NavBarHome(BasePage parent) {
		super(getDriver(), parent);
	}
	
	@Override
	public boolean isLoaded() {
		return isUrlAndPageTitleAsCurrentPage(GetEnvironmentParam.WWW_FONT_URL.getValue() + PageSubURLsToolsQAEnum.MAIN_PAGE.getValue());
	}
	
	@Override
	public void load() {
	}
	
	@Override
	public String pageTitle() {
		return PageTitlesEnum.MAIN_PAGE.toString();
	}
	
}
