package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.By;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.Button;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class RedirectLinkPage extends BasePage {
	
	private static final By selectorRedirectHere = By.cssSelector("a#redirect");
	
	@Override
	public boolean isLoaded() {
		BFLogger.logDebug("The DynamicContent page is loaded.");
		return getDriver().getCurrentUrl()
				.equals(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.REDIRECT.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("load");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.REDIRECT.getValue());
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
	
	public StatusCodesHomePage clickRedirectHereLink() {
		new Button(selectorRedirectHere).click();
		return new StatusCodesHomePage();
	}
}
