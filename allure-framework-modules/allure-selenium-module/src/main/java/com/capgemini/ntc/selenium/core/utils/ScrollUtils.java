package com.capgemini.ntc.selenium.core.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.test.core.logger.BFLogger;

/**
 * This class contains methods related to scrolling the page. Some methods were moved here from BasePage since they
 * operated on the whole page not on a particular component.
 * 
 * @author
 */
public class ScrollUtils {
	
	private ScrollUtils() {
	}
	
	/**
	 * Determines if the scroll bar is positioned at its lowest possible position by comparing its position before and
	 * after scrolling 250 points to the bottom
	 * 
	 * @return true if we can't scroll to bottom of the page anymore, false otherwise
	 * @author
	 */
	public static boolean isScrolledToTheBottomOfThePage() {
		
		JavascriptExecutor executor = (JavascriptExecutor) BasePage.getDriver();
		Long scrollPositionBefore = (Long) executor.executeScript("return window.scrollY;");
		executor.executeScript("window.scrollBy(0,250);");
		Long scrollPositionAfter = (Long) executor.executeScript("return window.scrollY;");
		
		BFLogger.logDebug(scrollPositionBefore + " " + scrollPositionAfter);
		
		return scrollPositionBefore.equals(scrollPositionAfter);
	}
	
	public static void scrollToBottom() {
		BasePage.getAction()
						.sendKeys(Keys.END)
						.perform();
	}
	
	public static void scrollToTop() {
		JavascriptExecutor js = (JavascriptExecutor) BasePage.getDriver();
		js.executeScript("window.scrollTo(0,0);");
	}
	
	/**
	 * This method moves view to the given element and tries to center it horizontally and vertically if possible Its
	 * useful especially with Chrome, when driver cant click on element which is not visible on screen, becouse of for
	 * example: docked-ticket-trade-inner at the bottom of the screen
	 * 
	 * @param element
	 *            WebElement which we want to be displayd on center of the screen
	 */
	public static void scrollElementIntoView(WebElement element) {
		Point point = element.getLocation();
		int windowHeight = BasePage.getDriver()
						.manage()
						.window()
						.getSize()
						.getHeight();
		int center = windowHeight / 2;
		int toMove = point.getY();
		@SuppressWarnings("deprecation")
		int toMoveCenter = (toMove >= center
						|| toMove <= BasePage.getDriver()
										.findElement(By.tagName("body"))
										.getSize()
										.getHeight())
														? toMove - center
														: toMove;
		
		JavascriptExecutor jse = (JavascriptExecutor) BasePage.getDriver();
		
		jse.executeScript("window.scrollTo(0, arguments[0]);", toMoveCenter);
		BasePage.getDriver()
						.waitForPageLoaded();
	}
	
	/**
	 * @return true if element is presented in the middle of the page; false otherwise
	 * @author
	 */
	public static boolean isElementLocatedInTheMiddle(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) BasePage.getDriver();
		long verticalOffset = (long) jse.executeScript("return window.scrollY;");
		long elementTopPosition = element.getLocation()
						.getY();
		long elementBottomPosition = elementTopPosition + element.getSize().height;
		long windowHeight = BasePage.getDriver()
						.manage()
						.window()
						.getSize()
						.getHeight();
		long windowHeightCenter = (windowHeight / 2) + verticalOffset;
		return elementTopPosition < windowHeightCenter && elementBottomPosition > windowHeightCenter;
	}
	
	/**
	 * Scrolls page by offsets given by parameters Negative xOffset scrolls left, negative yOffset scrolls up
	 * 
	 * @param xOffset
	 *            horizontal offset
	 * @param yOffset
	 *            vertical offset
	 */
	public static void scrollPage(int xOffset, int yOffset) {
		JavascriptExecutor jse = (JavascriptExecutor) BasePage.getDriver();
		jse.executeScript("window.scrollBy(" + xOffset + "," + yOffset + ")", "");
	}
	
}
