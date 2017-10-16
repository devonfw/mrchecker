package com.example.core.tests.assertions;

import static com.example.selenium.core.BasePage.getDriver;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.selenium.core.BasePage;
import com.example.selenium.pages.enums.PageSubURLsEnum;
import com.example.core.logger.BFLogger;
import com.example.core.tests.TestUtils;
import com.example.core.tests.core.BaseTest;

/**
 * Class with additional assertions that are missing in class 'Assert'
 */

public class AssertExtension {

	static void failNotEquals(String message, Object expected, Object actual) {
		fail(format(message, expected, actual));
	}

	static String format(String message, Object expected, Object actual) {
		String formatted = "";
		if (message != null && !message.equals("")) {
			formatted = message + " ";
		}
		String expectedString = String.valueOf(expected);
		String actualString = String.valueOf(actual);
		if (expectedString.equals(actualString)) {
			return formatted + "expected: " + formatClassAndValue(expected, expectedString) + " but was: "
					+ formatClassAndValue(actual, actualString);
		} else {
			return formatted + "expected:<" + expectedString + "> but was:<" + actualString + ">";
		}
	}

	private static String formatClassAndValue(Object value, String valueString) {
		String className = value == null ? "null" : value.getClass().getName();
		return className + "<" + valueString + ">";
	}

	/**
	 * Asserts two lists of arrays are equals
	 *
	 * @param message
	 *            to be displayed in case of failure
	 * @param expected
	 *            values
	 * @param actuals
	 *            values
	 */
	static public <T> void assertListsEquals(String message, List<T[]> expected, List<T[]> actuals) {
		assertEquals("Size of expected list is differend than actuals.", expected.size(), actuals.size());
		for (int i = 0; i < expected.size(); i++) {
			T[] expectedValues = expected.get(i);
			T[] actualValues = actuals.get(i);
			assertArrayEquals(message, expectedValues, actualValues);
		}
	}

	/**
	 * Asserts lists consists the same elements. Item order is ignored.
	 *
	 * @param message
	 *            to be displayed in case of failure
	 * @param expected
	 *            values
	 * @param actuals
	 *            values
	 */
	static public <T> void assertListsConsistsSameElements(String message, List<T> expected, List<T> actuals) {
		assertTrue(message,
				expected.size() == actuals.size() && expected.containsAll(actuals) && actuals.containsAll(expected));
	}

	/**
	 * Asserts each of list elements matches given regular expression
	 *
	 * @param expectedPattern
	 * @param actuals
	 */
	public static void assertEachMatches(String expectedPattern, List<String> actuals) {
		for (String actual : actuals) {
			if (!actual.matches(expectedPattern)) {
				fail(String.format("Each value schould match regex '%s' but '%s' doesn't", expectedPattern, actual));
			}
		}
	}

	/**
	 * Asserts none list element matches given regular expression
	 *
	 * @param expectedPattern
	 * @param actuals
	 */
	public static void assertNoneMatches(String unexpectedPattern, List<String> actuals) {
		for (String actual : actuals) {
			if (actual.matches(unexpectedPattern)) {
				fail(String.format("None value schould match regex '%s' but '%s' does", unexpectedPattern, actual));
			}
		}
	}

	/**
	 * Asserts tab was opened
	 *
	 * @param PageSubURLsEnum
	 */
	public static void assertPageOpened(PageSubURLsEnum tabToCheck) {
		assertTrue(tabToCheck.toString() + " was not opened.", TestUtils.isOnPage(tabToCheck));
	}

	/**
	 * Clicks on all simple (no javascript) links on page and checks if user gets redirected
	 *
	 * @return true if all links are working, false when user is not redirected after click
	 */
	public static void assertAllLinksWork() {
		List<String> links = getAllValidLinksTexts();
		assertLinksWork(links);
	}

	/**
	 * Clicks on given links on page and checks if user gets redirected
	 *
	 * @return true if all links from set are working, false when user is not redirected after click
	 */
	public static void assertLinksWork(List<String> links) {
		String url = getDriver().getCurrentUrl();
		List<String> linksTexts = links;
		for (String linkText : linksTexts) {
			WebElement link = getDriver().findElement(By.linkText(linkText));
			link.click();
			getDriver().waitForPageLoaded();
			String newUrl = getDriver().getCurrentUrl();
			BFLogger.logDebug("Checking if user got redirected after clicking on link [" + linkText + "].");
			assertNotEquals("Link [" + linkText + "] is not working. Url is the same as before click.", url, newUrl);
			BasePage.navigateBack();
		}
	}

	private static List<String> getAllValidLinksTexts() {
		List<WebElement> links = getDriver().findElements(By.tagName("a"));
		List<String> visibleLinksTexts = new ArrayList<String>();
		for (WebElement link : links) {
			String text = link.getText();
			if (!text.isEmpty()) {
				String href = link.getAttribute("href");
				if (href.contains("javascript")) {
					continue;
				}
				int lastIndex = href.length() - 1;
				char lastChar = href.charAt(lastIndex);
				if (lastChar != '#') {
					visibleLinksTexts.add(text);
				}
			}
		}
		return visibleLinksTexts;
	}
}