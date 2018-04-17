package com.capgemini.ntc.selenium.core.utils;

import java.io.File;
import java.util.regex.Pattern;

import org.openqa.selenium.By;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.enums.SubUrl;
import com.capgemini.ntc.test.core.exceptions.BFInputDataException;
import com.capgemini.ntc.test.core.logger.BFLogger;

/**
 * @author
 */
public class TestUtils {
	
	/**
	 * @author
	 * @param pageSubUrl
	 * @return Returns true if current URL contains subURL specified by parameter
	 */
	public static boolean isOnPage(SubUrl pageSubUrl) {
		return isCurrentUrlContains(pageSubUrl.subURL());
	}
	
	/**
	 * @param url
	 * @return Returns true if current URL contains URL specified by parameter
	 */
	public static boolean isCurrentUrlContains(String url) {
		if (BasePage.getDriver()
						.getCurrentUrl()
						.contains(url)) {
			BFLogger.logDebug("Current page as expected: " + BasePage.getDriver()
							.getCurrentUrl());
			return true;
		} else {
			BFLogger.logDebug("Not on " + url + " page. Current page: " + BasePage.getDriver()
							.getCurrentUrl());
			return false;
		}
	}
	
	/**
	 * @param url
	 * @return Returns true if current URL matches the specified pattern, false otherwise
	 */
	public static boolean isCurrentUrlMatchesPattern(String toMatch) {
		Pattern pattern = Pattern.compile(toMatch);
		return pattern.matcher(BasePage.getDriver()
						.getCurrentUrl())
						.matches();
	}
	
	public static boolean isCurrentUrlContains(String[] pageSubUrls) {
		for (String subUrl : pageSubUrls) {
			if (!isCurrentUrlContains(subUrl)) {
				BFLogger.logDebug("[isCurrentUrlContains()] subUrl: " + subUrl);
				return false;
			}
		}
		return true;
	}
	
	public static boolean isCurrentPageTitle(String title) {
		if (BasePage.getDriver()
						.getTitle()
						.contains(title)) {
			BFLogger.logDebug("Current page as expected: " + BasePage.getDriver()
							.getTitle());
			return true;
		} else {
			BFLogger.logDebug("Not on " + title + " page. Current page: " + BasePage.getDriver()
							.getTitle());
			return false;
		}
	}
	
	/**
	 * Wrapper for BasePage.getDriver().get(pageUrl) function that can be used in tests.
	 * 
	 * @param pageUrl
	 */
	public void goToPage(String pageUrl) {
		BasePage.getDriver()
						.get(pageUrl);
	}
	
	public String getCurrentPageURL() {
		return BasePage.getDriver()
						.getCurrentUrl();
	}
	
	/**
	 * @param text
	 *            to find
	 * @return true if text was found anywhere on the page, false otherwise
	 */
	@SuppressWarnings("deprecation")
	public boolean isTextOnPage(String text) {
		return BasePage.getDriver()
						.findElement(By.cssSelector("body"))
						.getText()
						.contains(text);
	}
	
	/**
	 * Generates absolute path to the file saved in test resources (/src/test/resources) Input: path - like
	 * 'environments/environments.csv' Output: absolute path to the file based on current OS
	 */
	public static String getAbsolutePathFor(String path) {
		String absolutePath = "";
		try {
			String resourceFile = TestUtils.class.getClassLoader()
							.getResource(path)
							.getFile();
			absolutePath = new File(resourceFile).getAbsolutePath();
		} catch (NullPointerException e) {
			throw new BFInputDataException("Given path: (" + path + ") does not exists in src/test/resources");
		}
		return absolutePath;
	}
	
}