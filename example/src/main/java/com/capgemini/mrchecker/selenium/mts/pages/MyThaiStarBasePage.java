package com.capgemini.mrchecker.selenium.mts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public abstract class MyThaiStarBasePage extends BasePage {
	
	@Override
	public boolean isLoaded() {
		WebElement displayableElement = getDriver().findElementQuietly(getDisplayableElementSelector());
		return displayableElement != null && displayableElement.isDisplayed();
	}
	
	@Override
	public void load() {
		BFLogger.logInfo(getClass().getSimpleName() + "  was not loaded.");
	}
	
	protected abstract By getDisplayableElementSelector();
}
