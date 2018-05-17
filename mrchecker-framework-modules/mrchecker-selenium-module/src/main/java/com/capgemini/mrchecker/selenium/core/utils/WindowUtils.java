package com.capgemini.mrchecker.selenium.core.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.exceptions.BFComponentStateException;
import com.capgemini.mrchecker.selenium.core.exceptions.BFRobotInitilizationException;
import com.capgemini.mrchecker.selenium.core.exceptions.BFWaittingForBrowserWindowsException;
import com.capgemini.mrchecker.selenium.core.newDrivers.INewWebDriver;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

/**
 * This class contains utility functions related to browser windows (switching to certain window, checking if it exists
 * etc.).
 * 
 * @author
 */
public class WindowUtils {
	
	private WindowUtils() {
	}
	
	/**
	 * Method to switch driver on window with url
	 * 
	 * @param url
	 *            - url which you want to get driver
	 * @param switchToGivenURL
	 *            - true if you want switch on this window, false if the another one
	 */
	public static void switchWindow(String url, boolean switchToGivenURL) {
		for (String winHandle : BasePage.getDriver()
				.getWindowHandles()) {
			BasePage.getDriver()
					.switchTo()
					.window(winHandle);
			String currentURL = BasePage.getDriver()
					.getCurrentUrl();
			boolean isSwitchedToGivenURL = currentURL.contains(url);
			if (!(isSwitchedToGivenURL ^ switchToGivenURL)) {
				return;
			}
		}
	}
	
	/**
	 * Method to switch driver on window with given title
	 * 
	 * @param title
	 *            - title/part of title, at which you want to get driver
	 * @param switchToGivenURL
	 *            - true if you want switch on this window, false if the another one
	 */
	public static void switchToWindowByTitle(String title, boolean switchToGiven) {
		for (String winHandle : BasePage.getDriver()
				.getWindowHandles()) {
			BasePage.getDriver()
					.switchTo()
					.window(winHandle);
			String currentTitle = BasePage.getDriver()
					.getTitle();
			boolean isSwitchedToGivenURL = currentTitle.contains(title);
			if (!(isSwitchedToGivenURL ^ switchToGiven)) {
				BFLogger.logInfo("Switching to window title:" + currentTitle);
				;
				return;
			}
		}
	}
	
	/**
	 * @return numer of opened windows
	 * @author
	 */
	public static int getWindowsNumber() {
		return BasePage.getDriver()
				.getWindowHandles()
				.size();
	}
	
	/**
	 * Checks whether or not window with url is present
	 * 
	 * @param url
	 * @return true when window is present, false otherwise
	 */
	public static boolean isWindowPresent(String url) {
		String home = BasePage.getDriver()
				.getWindowHandle();
		boolean windowAvailable = false;
		for (String winHandle : BasePage.getDriver()
				.getWindowHandles()) {
			BasePage.getDriver()
					.switchTo()
					.window(winHandle);
			String currentURL = BasePage.getDriver()
					.getCurrentUrl();
			windowAvailable = currentURL.contains(url);
			if (windowAvailable)
				break;
		}
		BasePage.getDriver()
				.switchTo()
				.window(home);
		return windowAvailable;
	}
	
	/**
	 * Checks whether or not window with title is present
	 * 
	 * @param title
	 * @return true when window is present, false otherwise
	 */
	public static boolean isWindowTitlePresent(String title) {
		String home = BasePage.getDriver()
				.getWindowHandle();
		boolean windowAvailable = false;
		for (String winHandle : BasePage.getDriver()
				.getWindowHandles()) {
			BasePage.getDriver()
					.switchTo()
					.window(winHandle);
			String currentTitle = BasePage.getDriver()
					.getTitle();
			windowAvailable = currentTitle.contains(title);
			if (windowAvailable)
				break;
		}
		BasePage.getDriver()
				.switchTo()
				.window(home);
		return windowAvailable;
	}
	
	/**
	 * Closes all windows except one with specified url
	 * 
	 * @param url
	 */
	public static void closeAllWindowsExcept(String url) {
		String desiredWindowHandle = null;
		for (String winHandle : BasePage.getDriver()
				.getWindowHandles()) {
			BasePage.getDriver()
					.switchTo()
					.window(winHandle);
			String currentURL = BasePage.getDriver()
					.getCurrentUrl();
			if (!currentURL.equals(url)) {
				closeWindow(currentURL);
			} else {
				desiredWindowHandle = winHandle;
			}
		}
		if (desiredWindowHandle != null) {
			BasePage.getDriver()
					.switchTo()
					.window(desiredWindowHandle);
		} else {
			throw new BFComponentStateException(WindowUtils.class.getSimpleName(), "closeAllWindowsExcept " + url,
					"window with specified url not found");
		}
	}
	
	/**
	 * Closes window with given url
	 * 
	 * @param url
	 */
	public static void closeWindow(String url) {
		if (isWindowPresent(url)) {
			switchWindow(url, true);
			BasePage.getDriver()
					.close();
			switchWindow(url, false);
		}
	}
	
	public static void resetZoom() {
		sendCtrlKeyCombination(KeyEvent.VK_0);
	}
	
	public static void zoomIn() {
		sendCtrlKeyCombination(KeyEvent.VK_ADD);
	}
	
	public static void zoomOut() {
		sendCtrlKeyCombination(KeyEvent.VK_MINUS);
	}
	
	private static void sendCtrlKeyCombination(int keyCode) {
		try {
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(keyCode);
			
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyRelease(keyCode);
		} catch (AWTException e) {
			throw new BFRobotInitilizationException(e);
		}
	}
	
	/**
	 * Method to get screen view width - visible part of the page in the browser.
	 * 
	 * @return screen width value
	 */
	public static int getScreenWidth(INewWebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		
		long wS = (long) jse.executeScript("return document.documentElement.clientWidth;");
		return new BigDecimal(wS).intValue();
	}
	
	/**
	 * Method to get screen view height - visible part of the page in the browser.
	 * 
	 * @return screen height value
	 */
	public static int getScreenHeight(INewWebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		
		long hS = (long) jse.executeScript("return document.documentElement.clientHeight;");
		return new BigDecimal(hS).intValue();
	}
	
	/**
	 * Waits for opening another browser window by page/script
	 * 
	 * @param timeoutDescription
	 * @return set of opened window titles
	 * @throws InterruptedException
	 */
	public static Set<String> waitForOpeningSecondBrowserWindow(String timeoutDescription) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		Set<String> allBrowserWindows = BasePage.getDriver()
				.getWindowHandles();
		int tmp_timeout = BasePage.PROGRESSBARWAITTIMER;
		while (allBrowserWindows.size() < 2) {
			TimeUtills.waitMiliseconds(1000);
			if (tmp_timeout-- <= 0)
				throw new BFWaittingForBrowserWindowsException(timeoutDescription);
			allBrowserWindows = BasePage.getDriver()
					.getWindowHandles();
		}
		BFLogger.logTime(startTime, "waitForOpeningSecondBrowserWindow");
		return allBrowserWindows;
	}
	
	/**
	 * Checks if Alert Pop Up was displayed and dismiss it if shown.
	 * 
	 * @return true if alert was dismissed, false otherwise
	 * @author
	 */
	public static boolean closeAlertIfPresent() {
		try {
			Alert alert = BasePage.getDriver()
					.switchTo()
					.alert();
			alert.dismiss();
			BFLogger.logDebug("Alert dismissed.");
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}
	
}