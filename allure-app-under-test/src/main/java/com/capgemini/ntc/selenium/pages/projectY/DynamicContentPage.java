package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.By;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.Button;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class DynamicContentPage extends BasePage {
	
	private static final By rowElements = By.cssSelector("div#content > div.row");
	
	@Override
	public boolean isLoaded() {
		BFLogger.logDebug("The KeyPresses page is loaded.");
		return getDriver().getCurrentUrl()
						.equals(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.DYNAMIC_CONTENT.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("load");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.DYNAMIC_CONTENT.getValue());
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
	
	public void clickChangeDynamicLink() {
		Button changeDynamicLink = new Button(By.cssSelector("div.example a"));
		changeDynamicLink.click();
	}
}
