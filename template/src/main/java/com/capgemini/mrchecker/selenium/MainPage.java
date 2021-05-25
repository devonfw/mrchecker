package com.capgemini.mrchecker.selenium;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.environment.EnvironmentParam;
import com.capgemini.mrchecker.selenium.environment.PageSubURLs;
import com.capgemini.mrchecker.selenium.environment.PageTitles;

public class MainPage extends BasePage {
	
	@Override
	public void load() {
		getDriver().get(EnvironmentParam.WWW_FONT_URL.getValue() + PageSubURLs.MAIN_PAGE.getValue());
	}
	
	@Override
	public boolean isLoaded() {
		return isUrlAndPageTitleAsCurrentPage(EnvironmentParam.WWW_FONT_URL.getValue() + PageSubURLs.MAIN_PAGE.getValue());
	}
	
	@Override
	public String pageTitle() {
		return PageTitles.MAIN_PAGE.toString();
	}
	
}
