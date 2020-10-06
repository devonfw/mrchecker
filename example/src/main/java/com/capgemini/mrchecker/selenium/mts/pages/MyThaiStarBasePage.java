package com.capgemini.mrchecker.selenium.mts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

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
	
	@Step("--Screenshot--")
	public static byte[] makeScreenShot() {
		return  takeScreenShot();
	}
	

	@Attachment(value = "Screenshot", type = "image/png")
	private static byte[] takeScreenShot() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
	}
}
