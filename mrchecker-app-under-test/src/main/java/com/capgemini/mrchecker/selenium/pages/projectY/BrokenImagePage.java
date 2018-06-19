package com.capgemini.mrchecker.selenium.pages.projectY;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.Button;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class BrokenImagePage extends TheInternetSubpage {
	
	private static final By[]	selectorsImages		= { By.cssSelector("div > img:nth-child(2)"),
					By.cssSelector("div > img:nth-child(3)"),
					By.cssSelector("div > img:nth-child(4)") };
	public final By				pageLinkSelector	= By.cssSelector("li > a[href*='broken_images']");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains(PageSubURLsProjectYEnum.BROKEN_IMAGES.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'Broken Images' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.BROKEN_IMAGES.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
	}
	
	@Override
	public void clickPageLink() {
		new Button(pageLinkSelector).click();
	}
	
	/**
	 * Returns an image height in pixels.
	 * 
	 * @param imageIndex
	 *            An index of given image.
	 * @return Height of an image in pixels.
	 */
	public int getImageHeight(int imageIndex) {
		return getImageDimension(imageIndex).getHeight();
	}
	
	/**
	 * Returns an image width in pixels.
	 * 
	 * @param imageIndex
	 *            An index of given image.
	 * @return Width of an image in pixels.
	 */
	public int getImageWidth(int imageIndex) {
		return getImageDimension(imageIndex).getWidth();
	}
	
	private Dimension getImageDimension(int imageIndex) {
		return getDriver().findElementDynamic(selectorsImages[imageIndex])
						.getSize();
	}
	
}
