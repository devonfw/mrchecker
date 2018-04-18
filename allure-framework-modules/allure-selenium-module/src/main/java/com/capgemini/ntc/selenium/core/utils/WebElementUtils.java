package com.capgemini.ntc.selenium.core.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.exceptions.BFComponentStateException;
import com.capgemini.ntc.selenium.core.exceptions.BFRobotInitilizationException;
import com.capgemini.ntc.test.core.exceptions.BFWaitingTimeoutException;
import com.capgemini.ntc.test.core.logger.BFLogger;

/**
 * Class represent WebElement utils. All needed method which may be use in WebElements
 * 
 * @author
 * @see
 */
public class WebElementUtils {
	
	private WebElementUtils() {
	}
	
	/**
	 * Simulate click on Print link. That was needed becouse after standard click Selenium lost focus. This function
	 * mark element and press enter to simulate click.
	 * 
	 * @param element
	 * @throws BFRobotInitilizationException
	 */
	public static void nonBlockingClickOnWebElement(WebElement element) {
		ScrollUtils.scrollElementIntoView(element);
		element.sendKeys();
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			BFLogger.logDebug("Press mouse on element");
		} catch (AWTException e) {
			throw new BFRobotInitilizationException(e);
		}
		
	}
	
	/**
	 * Checks if WebElement is highlighted in specified color
	 * 
	 * @param webElement
	 * @param color
	 *            as rgba value
	 * @return true if selected account is highlighted in specified color
	 */
	public static boolean isWebElementHighlightedInColor(WebElement webElement, String color) {
		String colorAsString = webElement.getCssValue("background-color");
		return color.equalsIgnoreCase(colorAsString);
	}
	
	/**
	 * Method is moving mouse to the specified element by <b>elementSelector</b> and wait for 10 seconds for hover
	 * element specified by <b>waitForSelector</b>
	 * 
	 * @param elementSelector
	 *            - element to hover on
	 * @param waitForSelector
	 *            - element to wait for after hovering mouse on element specified by elementSelector
	 */
	public static WebElement hoverOnElement(By elementSelector, By waitForSelector) {
		@SuppressWarnings("deprecation")
		WebElement element = BasePage.getDriver()
						.findElement(elementSelector);
		
		return hoverOnElement(element, waitForSelector);
	}
	
	/**
	 * Method is moving mouse to the specified element by <b>elementToHoverOn</b> and wait for 10 seconds for hover
	 * element specified by <b>waitForSelector</b>
	 * 
	 * @param elementToHoverOn
	 *            - element to hover on
	 * @param waitForSelector
	 *            - element to wait for after hovering mouse on element specified by elementSelector
	 */
	public static WebElement hoverOnElement(WebElement elementToHoverOn, By waitForSelector) {
		long startTime = System.currentTimeMillis();
		BasePage.getAction()
						.moveToElement(elementToHoverOn)
						.build()
						.perform();
		
		WebDriverWait wait = new WebDriverWait(BasePage.getDriver(), BasePage.EXPLICITYWAITTIMER);
		WebElement element = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.visibilityOfElementLocated(waitForSelector));
		BFLogger.logTime(startTime, "hoverOnElement", waitForSelector.toString());
		return element;
	}
	
	/**
	 * Method is moving mouse to the specified element by <b>elementToHoverOn</b> and wait for 10 seconds for hover
	 * element specified by <b>waitForElement</b>
	 * 
	 * @param elementToHoverOn
	 *            - element to hover on
	 * @param waitForElement
	 *            - element to wait for after hovering mouse on element specified by elementToHoverOn
	 */
	public static WebElement hoverOnElement(WebElement elementToHoverOn, WebElement waitForElement) {
		long startTime = System.currentTimeMillis();
		BasePage.getAction()
						.moveToElement(elementToHoverOn)
						.build()
						.perform();
		
		WebDriverWait wait = new WebDriverWait(BasePage.getDriver(), BasePage.EXPLICITYWAITTIMER);
		WebElement element = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.visibilityOf(waitForElement));
		BFLogger.logTime(startTime, "hoverOnElement", waitForElement.toString());
		return element;
	}
	
	/**
	 * Wait until element localized by passed as a parameter selector is visible
	 * 
	 * @param elementToWaitFor
	 *            - selector for an element to wait for
	 * @throws TimeoutException
	 *             after {@link BasePage.EXPLICITYWAITTIMER}
	 */
	public static void waitForElementVisible(By elementToWaitFor) {
		waitForElementVisible(elementToWaitFor, BasePage.EXPLICITYWAITTIMER);
	}
	
	/**
	 * Wait until element localized by passed as a parameter selector is visible
	 * 
	 * @param elementToWaitFor
	 *            - selector for an element to wait for
	 * @param tiemout
	 *            in seconds
	 * @throws TimeoutException
	 *             after time provided by timeout parameter
	 */
	public static void waitForElementVisible(By elementToWaitFor, int tiemout) {
		long startTime = System.currentTimeMillis();
		WebDriverWait wait = new WebDriverWait(BasePage.getDriver(), tiemout);
		wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.visibilityOfElementLocated(elementToWaitFor));
		BFLogger.logTime(startTime, "waitForElementVisible", elementToWaitFor.toString());
	}
	
	/**
	 * Method waits max of 10 seconds for the <b>elementToWaitFor</b> to become visible
	 * 
	 * @param elementToWaitFor
	 * @return elementToWaitFor if it becomes visible, throws TimeoutException otherwise
	 */
	public static WebElement waitForElementVisible(WebElement elementToWaitFor) {
		long startTime = System.currentTimeMillis();
		WebDriverWait wait = new WebDriverWait(BasePage.getDriver(), BasePage.EXPLICITYWAITTIMER);
		WebElement element = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.visibilityOf(elementToWaitFor));
		BFLogger.logTime(startTime, "waitForElementVisible", elementToWaitFor.toString());
		return element;
	}
	
	/**
	 * Method waits max of 10 seconds for the <b>elementToWaitFor</b> to become invisible
	 * 
	 * @param elementToWaitFor
	 *            - selector for element to wait for
	 * @return true if it becomes invisible, throws TimeoutException otherwise
	 * @throws TimeoutException
	 */
	public static boolean waitForElementNotVisible(By elementToWaitFor) {
		long startTime = System.currentTimeMillis();
		WebDriverWait wait = new WebDriverWait(BasePage.getDriver(), BasePage.EXPLICITYWAITTIMER);
		Boolean elementInvisibility = wait.until((Function<? super WebDriver, Boolean>) ExpectedConditions.invisibilityOfElementLocated(elementToWaitFor));
		BFLogger.logTime(startTime, "waitForElementNotVisible", elementToWaitFor.toString());
		return elementInvisibility;
	}
	
	/**
	 * Method waits max of 10 seconds for the <b>elementToWaitFor</b> to become invisible
	 * 
	 * @param elementToWaitFor
	 * @return true if it becomes invisible, throws TimeoutException otherwise
	 */
	public static boolean waitForElementNotVisible(WebElement elementToWaitFor) {
		long startTime = System.currentTimeMillis();
		WebDriverWait wait = new WebDriverWait(BasePage.getDriver(), BasePage.EXPLICITYWAITTIMER);
		Boolean elementInvisibility = wait
						.until((Function<? super WebDriver, Boolean>) ExpectedConditions.not(ExpectedConditions.visibilityOf(elementToWaitFor)));
		BFLogger.logTime(startTime, "waitForElementNotVisible", elementToWaitFor.toString());
		return elementInvisibility;
	}
	
	/**
	 * Waits till element is clickable and perform click
	 * 
	 * @param selector
	 */
	public static void dynamicClick(By selector) {
		BasePage.getDriver()
						.waitUntilElementIsClickable(selector)
						.click();
	}
	
	/**
	 * Method is moving mouse on given distance in px from the actual position
	 * 
	 * @param xOffset
	 *            horizontal offset. Negative value means moving mouse to the left
	 * @param yOffset
	 *            vertical offset. Negative value means moving mouse up
	 */
	public static void moveMousePointer(int xOffset, int yOffset) {
		BasePage.getAction()
						.moveByOffset(xOffset, yOffset)
						.build()
						.perform();
	}
	
	/**
	 * @param field
	 * @return 'value' attribute of given WebElement or null if 'value' attribute not found
	 */
	public static String getValue(WebElement field) {
		return field.getAttribute("value");
	}
	
	/**
	 * @param WebElement
	 *            field
	 * @return 'value' attribute of given WebElement or null if 'value' attribute not found
	 */
	public static boolean isAttributeWithContent(String attribute, WebElement field) {
		return !StringUtils.isEmpty(field.getAttribute(attribute));
	}
	
	/**
	 * Clears input field and sends new string
	 * 
	 * @param field
	 *            to write into
	 * @param keys
	 *            to send
	 */
	public static void sendNewKeys(WebElement field, String keys) {
		try {
			field.clear();
			field.sendKeys(keys);
		} catch (InvalidElementStateException e) {
			throw new BFComponentStateException(field.toString(), "type '" + keys + "' into", "not editable.");
		}
	}
	
	/**
	 * Method gets desired WebElement within certain parent object. Generates detailed error message when element is not
	 * found.Generic class argument is only for warning suppression.
	 * 
	 * @param parent
	 *            of desired WebElement
	 * @param selector
	 *            of WebElement
	 * @param retriever
	 *            Class calling this method, used in worst case scenario to generate error message
	 * @param expectedElementDescription
	 *            phrase that describes WebElement, used in worst case scenario to generate error message
	 * @throws BFComponentStateException
	 * @return WebElement matching selector
	 */
	public static <T> WebElement getExpectedElement(WebElement parent,
					By selector,
					Class<T> retriever,
					String expectedElementDescription) {
		WebElement e;
		try {
			e = parent.findElement(selector);
		} catch (NoSuchElementException ex) {
			throw new BFComponentStateException(retriever.getSimpleName(), "get " + expectedElementDescription,
							"not found");
		}
		return e;
	}
	
	/**
	 * Checks if given element was changed (by page refresh or dynamically by script). Flow: retrieve web element
	 * {@code webElement} than perform some action which might trigger refresh (on it) and than invoke this method to
	 * check if element has changed
	 * 
	 * @return boolean value
	 */
	public static boolean isElementOutdated(WebElement webElement) {
		try {
			webElement.getText();
		} catch (StaleElementReferenceException e) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if Element is link.
	 * 
	 * @param WebElement
	 * @return false if href and url attributes are null
	 */
	public static boolean isLink(WebElement element) {
		return (element.getAttribute("href") != null || element.getAttribute("url") != null);
	}
	
	/**
	 * Wait for end animation. For example when element 'selector' is fade in out
	 * 
	 * @param selector
	 */
	public static void waitForAnimationEnd(By selector) {
		long startTime = System.currentTimeMillis();
		@SuppressWarnings("deprecation")
		WebElement sliderElement = BasePage.getDriver()
						.findElement(selector);
		String leftOffset = "";
		while (!leftOffset.equals(sliderElement.getAttribute("style"))) {
			if (System.currentTimeMillis() - startTime > BasePage.EXPLICITYWAITTIMER * 1000) {
				throw new BFWaitingTimeoutException("Animation is to long", BasePage.EXPLICITYWAITTIMER * 1000);
			}
			leftOffset = sliderElement.getAttribute("style");
			TimeUtills.waitMiliseconds(100);
		}
		BFLogger.logTime(startTime, "waitForAnimationEnd", selector.toString());
	}
	
	/**
	 * Clicks on element if it is displayed, otherwise throws an exception
	 * 
	 * @param selector
	 *            of the element to click
	 * @param elementName
	 *            to print in exception
	 */
	@SuppressWarnings("deprecation")
	public static void clickIfVisible(By selector, String elementName) {
		if (BasePage.isElementDisplayedNoException(selector)) {
			BasePage.getDriver()
							.findElement(selector)
							.click();
		} else {
			throw new BFComponentStateException(elementName, "click", "element not found");
		}
	}
	
	/**
	 * @return texts from elements
	 */
	public static List<String> getTexts(List<WebElement> elements) {
		List<String> result = new ArrayList<>();
		for (WebElement element : elements) {
			result.add(element.getText());
		}
		return result;
	}
}
