package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class DisappearingElementsPage extends BasePage {
	
	private static final By	selectorGalleryMenuButton	= By.cssSelector("li > a[href*=gallery]");
	private static final By	selectorMenuButtons			= By.cssSelector("li");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains("disappearing_elements");
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("load()");
	}
	
	@Override
	public String pageTitle() {
		return "The Internet";
	}
	
	/**
	 * Returns a number of WebElements representing menu buttons.
	 * 
	 * @return A number of WebElements.
	 */
	public int getNumberOfMenuButtons() {
		return getDriver().findElementDynamics(selectorMenuButtons)
						.size();
	}
	
	/**
	 * Returns WebElement representing disappearing element of menu.
	 * 
	 * @return Disappearing WebElement if visible, null otherwise.
	 */
	public WebElement getGalleryMenuElement() {
		return getDriver().findElementQuietly(selectorGalleryMenuButton);
	}
	
	/**
	 * Refreshes web page as many times as it is required to appear/disappear menu button WebElement.
	 * 
	 * @param appearance
	 *            Determines if element should appear (true) or disappear (false).
	 */
	public void refreshUntilAppear(boolean appearance) {
		int numberOfAttempts = 5;
		int counter = 0;
		while (!(!(isGalleryMenuElementVisible() ^ appearance)) || counter > numberOfAttempts) {
			refreshPage();
			counter++;
		}
	}
	
	private boolean isGalleryMenuElementVisible() {
		boolean result = false;
		WebElement gallery = getGalleryMenuElement();
		if (gallery != null)
			result = gallery.isDisplayed();
		return result;
	}
	
}
