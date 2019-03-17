package com.capgemini.mrchecker.selenium;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.environment.PageSubURLs;
import com.capgemini.mrchecker.selenium.environment.PageTitlesEnum;

public class MainPage extends BasePage {
	
	@Override
	public void load() {
		getDriver().get(GetEnvironmentParam.WWW_FONT_URL.getValue() + PageSubURLs.MAIN_PAGE.getValue());
	}
	
	@Override
	public boolean isLoaded() {
		return isUrlAndPageTitleAsCurrentPage(GetEnvironmentParam.WWW_FONT_URL.getValue() + PageSubURLs.MAIN_PAGE.getValue());
	}
	
	@Override
	public String pageTitle() {
		return PageTitlesEnum.MAIN_PAGE.toString();
	}
	
}
