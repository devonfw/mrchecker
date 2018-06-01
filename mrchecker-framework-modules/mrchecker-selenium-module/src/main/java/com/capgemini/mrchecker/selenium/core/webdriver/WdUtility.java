package com.capgemini.mrchecker.selenium.core.webdriver;

import static com.capgemini.mrchecker.selenium.core.BasePage.getAction;
import static com.capgemini.mrchecker.selenium.core.BasePage.getDriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.newDrivers.INewWebDriver;
import com.capgemini.mrchecker.selenium.core.utils.WebElementUtils;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

/**
 * Web Driver utility class
 * 
 * @author
 */
public class WdUtility {
	
	private WdUtility() {
	}
	
	/**
	 * Executes Java Script code to returns content of CSS property by given selector (class, id)
	 * 
	 * @param driver
	 * @param querySelector
	 *            - e.g. .balances--download-balances
	 * @param pseudoElement
	 *            - e.g. :before
	 * @param cssProperty
	 *            - e.g. content
	 * @return
	 */
	public static String getStylePropertyByJS(final INewWebDriver driver,
					String querySelector,
					String pseudoElement,
					String cssProperty) {
		String script = "return window.getComputedStyle(document.querySelector('" + querySelector + "'),'"
						+ pseudoElement + "').getPropertyValue('" + cssProperty + "')";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (String) js.executeScript(script);
	}
	
	public static List<Date> createDateList(List<WebElement> dateElements, String pattern) throws ParseException {
		List<Date> datesList = new ArrayList<Date>();
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		for (WebElement dateElement : dateElements) {
			Date date = dateFormat.parse(dateElement.getText());
			datesList.add(date);
		}
		return datesList;
	}
	
	public static List<String> createStringList(List<WebElement> elements) {
		List<String> stringsList = new ArrayList<String>();
		for (WebElement el : elements) {
			stringsList.add(el.getText());
		}
		return stringsList;
	}
	
	/**
	 * Compares two elements in z-axis, by comparison their CSS <i>z-index</i> values. For element which doesn't contain
	 * z-index it assign 0 value.
	 * 
	 * @param firstElement
	 * @param secondElement
	 * @return the value 0 if the both elements have same z-index or non z-index values; a value less than 0 if first
	 *         element is behind second element; and a value greater than 0 if first element is in front of second
	 *         element.
	 */
	public static int compareElementsInZAxis(WebElement firstElement, WebElement secondElement) {
		String firstElementCssZindex = firstElement.getCssValue("z-index");
		int firstElementValle = 0;
		if (!firstElementCssZindex.isEmpty()) {
			firstElementValle = Integer.parseInt(firstElementCssZindex);
		}
		String secondElementCssZindex = secondElement.getCssValue("z-index");
		int secondElementValle = 0;
		if (!secondElementCssZindex.isEmpty()) {
			secondElementValle = Integer.parseInt(secondElementCssZindex);
		}
		// greater value of index means, it is 'closer' to user
		if (firstElementValle == secondElementValle) {
			return 0;
		} else if (firstElementValle > secondElementValle) {
			return 1;
		}
		return -1;
	}
	
	/**
	 * Check if first element is over second element
	 * 
	 * @param firstElement
	 * @param secondElement
	 * @return true if first element is on top of other; false otherwise
	 */
	public static boolean isOverElement(WebElement firstElement, WebElement secondElement) {
		return WdUtility.compareElementsInZAxis(firstElement, secondElement) > 0 ? true : false;
	}
	
	/**
	 * Checks if text is displayed inside the element
	 * 
	 * @author
	 * @param selector
	 *            selector for element
	 * @param text
	 *            text to look for inside the element
	 * @return true if text is displayed inside the element, false otherwise
	 */
	public static boolean isTextDisplayedInsideElement(By selector, String text) {
		@SuppressWarnings("deprecation")
		WebElement element = getDriver().findElement(selector);
		return element.getText()
						.contains(text);
	}
	
	/**
	 * Checks if tooltip is displayed when hovering over element
	 * 
	 * @author
	 * @param element
	 *            element to be hovered
	 * @param tooltip
	 *            tooltip to be displayed upon hover
	 * @return true if tooltip is displayed, false otherwise
	 */
	public static boolean isTooltipDisplayed(WebElement element, WebElement tooltip) {
		getAction().moveToElement(element)
						.perform();
		return !tooltip.getCssValue("display")
						.equals("none");
	}
	
	/**
	 * Checks if tooltip is displayed when hovering over element
	 * 
	 * @author
	 * @param element
	 *            element to be hovered
	 * @param tooltip
	 *            selector to be displayed upon hover
	 * @return true if tooltip is displayed, false otherwise
	 */
	public static boolean isTooltipDisplayed(WebElement element, By waitForSelector) {
		WebElement tooltip = WebElementUtils.hoverOnElement(element, waitForSelector);
		return !tooltip.isDisplayed();
	}
	
	/**
	 * Checks if element's text matches the given format
	 * 
	 * @author
	 * @param selector
	 *            selector for element
	 * @param format
	 *            format which the text should match
	 * @return true if text matches the given format, false otherwise
	 */
	public static boolean isTextInCorrectFormat(By selector, String format) {
		@SuppressWarnings("deprecation")
		WebElement element = getDriver().findElement(selector);
		String elementText = element.getText();
		Pattern pattern = Pattern.compile(format);
		Matcher patternMatcher = pattern.matcher(elementText);
		return patternMatcher.find();
	}
	
	/**
	 * @author
	 * @param selectorKey
	 *            selector for all key elements
	 * @param selectorValue
	 *            selector for all value elements
	 * @return map combined from the list of keys and values (their text values)
	 */
	public static Map<String, String> getMapFromLists(By selectorKey, By selectorValue) {
		@SuppressWarnings("deprecation")
		List<WebElement> keys = getDriver().findElements(selectorKey);
		@SuppressWarnings("deprecation")
		List<WebElement> values = getDriver().findElements(selectorValue);
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (keys.size() != values.size() || keys.size() == 0) {
			BFLogger.logError("Incorrect lists - an empty map will be created");
			return map;
		}
		
		Iterator<WebElement> keyIterator = keys.iterator();
		Iterator<WebElement> valueIterator = values.iterator();
		while (keyIterator.hasNext() && valueIterator.hasNext()) {
			map.put(keyIterator.next()
							.getText(),
							valueIterator.next()
											.getText());
		}
		return map;
	}
	
	/**
	 * @author
	 * @param element
	 *            's selector as a string
	 * @return true if element's text is cropped (it's so long that instead full text, just a part of it is displayed,
	 *         followed by '...'), false otherwise. example of cropped text: RICH PHARMACEUTICALS INC COM NEW ISIN
	 *         #US76303T...
	 */
	public static boolean isElementTextCropped(String selectorForElement) {
		JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		return (boolean) executor.executeScript(
						"var element = document.querySelector(arguments[0]); return (element.offsetWidth < element.scrollWidth);",
						selectorForElement);
	}
	
	/**
	 * @author
	 * @param selectorElement
	 *            selector for element
	 * @param expectedText
	 *            text to look for inside the element
	 * @return true if element contains the text, false otherwise
	 */
	public static boolean isElementContainsText(By selectorElement, String expectedText) {
		@SuppressWarnings("deprecation")
		WebElement element = getDriver().findElement(selectorElement);
		String elementText = element.getText();
		return elementText.contains(expectedText);
	}
}
