package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class HoversPage extends BasePage {
	
	private final static By	selectorImages	= By.cssSelector("div.figure > img");
	private final static By	selectorNames	= By.cssSelector("div.figcaption h5");
	
	@Override
	public boolean isLoaded() {
		BFLogger.logDebug("The Hovers page is loaded.");
		return getDriver().getCurrentUrl()
						.equals(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.HOVERS.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("load");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.HOVERS.getValue());
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
	
	public void hoverUnderAvatar(int index) {
		Actions action = new Actions(getDriver());
		WebElement avatarImage = getDriver().findElementDynamics(selectorImages)
						.get(index);
		action.moveToElement(avatarImage)
						.perform();
	}
	
	public String getTextUnderAvatar(int index) {
		WebElement elementText = getDriver().findElementDynamics(selectorNames)
						.get(index);
		return elementText.getText();
	}
	
}
