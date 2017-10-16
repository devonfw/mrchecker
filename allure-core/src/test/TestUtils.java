/**
 * 
 */
package com.example.core.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.core.enums.SubUrl;
import com.example.core.exceptions.BFInputDataException;
import com.example.core.logger.BFLogger;
import com.example.core.tests.core.BaseTest;

/**
 * @author
 *
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
		if (BaseTest.getDriver().getCurrentUrl().contains(url)) {
			BFLogger.logDebug("Current page as expected: " + BaseTest.getDriver().getCurrentUrl());
			return true;
		} else {
			BFLogger.logDebug("Not on " + url + " page. Current page: " + BaseTest.getDriver().getCurrentUrl());
			return false;
		}
	}

	/**
	 * @param url
	 * @return Returns true if current URL matches the specified pattern, false otherwise
	 */
	public static boolean isCurrentUrlMatchesPattern(String toMatch) {
		Pattern pattern = Pattern.compile(toMatch);
		return pattern.matcher(BaseTest.getDriver().getCurrentUrl()).matches();
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
		if (BaseTest.getDriver().getTitle().contains(title)) {
			BFLogger.logDebug("Current page as expected: " + BaseTest.getDriver().getTitle());
			return true;
		} else {
			BFLogger.logDebug("Not on " + title + " page. Current page: " + BaseTest.getDriver().getTitle());
			return false;
		}
	}

	/**
	 * Wrapper for BaseTest.getDriver().get(pageUrl) function that can be used in tests.
	 * 
	 * @param pageUrl
	 */
	public void goToPage(String pageUrl) {
		BaseTest.getDriver().get(pageUrl);
	}

	public String getCurrentPageURL() {
		return BaseTest.getDriver().getCurrentUrl();
	}

	/**
	 * 
	 * @param text
	 *            to find
	 * @return true if text was found anywhere on the page, false otherwise
	 */
	public boolean isTextOnPage(String text) {
		return BaseTest.getDriver().findElement(By.cssSelector("body")).getText().contains(text);
	}

	// compare two 2D arrays of Strings
	public void assertTwoDimensionalArrayEquals(String[][] expecteds, String[][] actuals) {
		for (int columnNo = 0; columnNo < expecteds.length; columnNo++) {
			assertArrayEquals(expecteds[columnNo], actuals[columnNo]);
		}
	}

	public void verifyElementText(String text, WebElement webElement) {
		assertEquals(text, webElement.getText());
	}

	/**
	 * Generates absolute path to the file saved in test resources (/src/test/resources) Input: path - like
	 * 'environments/environments.csv' Output: absolute path to the file based on current OS
	 */
	public static String getAbsolutePathFor(String path) {
		String absolutePath = "";
		try {
			String resourceFile = TestUtils.class.getClassLoader().getResource(path).getFile();
			absolutePath = new File(resourceFile).getAbsolutePath();
		} catch (NullPointerException e) {
			throw new BFInputDataException("Given path: (" + path + ") does not exists in src/test/resources");
		}
		return absolutePath;
	}

}
