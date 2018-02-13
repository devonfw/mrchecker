package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.By;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;

public class FileDownloadPage extends BasePage {
	
	private static final By selectorFirstFile = By.cssSelector("a[href*=some-file]");
	
	@Override
	public boolean isLoaded() {
		return getDriver().getCurrentUrl()
						.equals("http://the-internet.herokuapp.com/download");
	}
	
	@Override
	public void load() {
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.CHALLENGING_DOM.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
	
	public void downloadTheFile() {
		getDriver().elementButton(selectorFirstFile)
						.click();
	}
	
}