package com.capgemini.mrchecker.selenium.pages.projectX;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.newDrivers.INewWebDriver;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectXEnum;
import com.capgemini.mrchecker.selenium.pages.environment.PageTitlesEnum;
import com.capgemini.mrchecker.selenium.pages.projectX.navigationbar.NavigationBar;

public class MainPage extends BasePage {
	
	public MainPage() {
		this(getDriver(), null);
	}
	
	public MainPage(INewWebDriver driver, BasePage parent) {
		super(driver, parent);
	}
	
	private BasePage parent;
	
	@Override
	public void load() {
		getDriver().get(GetEnvironmentParam.WWW_FONT_URL.getValue() + PageSubURLsProjectXEnum.MAIN_PAGE.getValue());
	}
	
	@Override
	public BasePage getParent() {
		return this.parent;
	}
	
	@Override
	public void setParent(BasePage parent) {
		// There is not parent
		this.parent = parent;
	}
	
	@Override
	public String pageTitle() {
		return PageTitlesEnum.MAIN_PAGE.toString();
	}
	
	@Override
	public boolean isLoaded() {
		return isUrlAndPageTitleAsCurrentPage(GetEnvironmentParam.WWW_FONT_URL.getValue() + PageSubURLsProjectXEnum.MAIN_PAGE.getValue());
	}
	
	public NavigationBar getNavigationBar() {
		NavigationBar navigationBar = new NavigationBar(getDriver(), this);
		return navigationBar;
	}
	
}
