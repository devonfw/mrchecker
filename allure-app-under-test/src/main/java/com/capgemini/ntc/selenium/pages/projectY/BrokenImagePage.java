package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.By;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class BrokenImagePage extends BasePage {
	
	private static final By[] selectorsImages = { By.cssSelector("div > img:nth-child(2)"),
					By.cssSelector("div > img:nth-child(3)"),
					By.cssSelector("div > img:nth-child(4)") };
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains("broken_images");
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("load()");
	}
	
	@Override
	public String pageTitle() {
		return "The Internet";
	}
	
	public int getHeightOfImage(int imageIndex) {
		return getDriver().findElementDynamic(selectorsImages[imageIndex])
						.getSize()
						.getHeight();
	}
	
	public int getWidthOfImage(int imageIndex) {
		return getDriver().findElementDynamic(selectorsImages[imageIndex])
						.getSize()
						.getWidth();
	}
	
}
