package com.capgemini.mrchecker.selenium.pages.projectY;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class HoversPage extends BasePage {
	
	private final static By	selectorImages	= By.cssSelector("div.figure > img");
	private final static By	selectorNames	= By.cssSelector("div.figcaption h5");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains(PageSubURLsProjectYEnum.HOVERS.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'Hovers' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.HOVERS.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
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
