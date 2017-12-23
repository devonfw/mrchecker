package com.capgemini.ntc.selenium.core.newDrivers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.FileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.UselessFileDetector;

import com.capgemini.ntc.selenium.core.exceptions.BFComponentStateException;
import com.capgemini.ntc.selenium.core.exceptions.BFElementNotFoundException;
import com.capgemini.ntc.selenium.core.utils.TimeUtills;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class NewRemoteWebElement extends RemoteWebElement {
	
	private static final int CLICK_NUM = 10;
	private static final int MICRO_SLEEP = 200;
	private static boolean clickTimerOn = false;
	private static long totalClickTime;
	private static long startClickTime;
	private static final Pattern foundByPattern = Pattern.compile("\\[\\[.* -> (.*): (.*)\\]");
	private static final FileDetector defaultFileDetector = new UselessFileDetector();
	
	/**
	 * The class is required for 3 reasons: 1. to keep a NewRemoteWebElement class immutable 2. to use a part of the
	 * code in two places 3. to name two data, to keep a code more readily
	 * 
	 * @author
	 */
	private class SelectorComponents {
		String locator = null;
		String term = null;
		
		SelectorComponents(String elementInfo) {
			Matcher thisWebElementInfo = foundByPattern.matcher(elementInfo);
			
			if (!thisWebElementInfo.matches()) {
				BFLogger.logError(String.format("Selector not found from '%s', pattern error.", elementInfo));
				return;
			}
			locator = thisWebElementInfo.group(1);
			term = thisWebElementInfo.group(2);
		}
		
		String getLocator() {
			return locator;
		}
		
		String getTerm() {
			return term;
		}
	}
	
	public NewRemoteWebElement(WebElement element) {
		RemoteWebElement remoteWebElement = (RemoteWebElement) element;
		id = remoteWebElement.getId();
		setParent((RemoteWebDriver) remoteWebElement.getWrappedDriver());
		fileDetector = defaultFileDetector;
		
		// if possible take a locator and a term
		Matcher remoteWebElementInfo = foundByPattern.matcher(remoteWebElement.toString());
		if (remoteWebElementInfo.matches()) {
			setFoundBy(element, remoteWebElementInfo.group(1), remoteWebElementInfo.group(2));
		} else {
			BFLogger.logError("Incorect FoundBy form WebElement " + remoteWebElement.toString());
		}
	}
	
	/**
	 * @deprecated As of release 1.0.0, replaced by {@link #findElementDynamic(By)()}
	 */
	@Deprecated
	@Override
	public WebElement findElement(By by) throws BFElementNotFoundException {
		WebElement element = null;
		try {
			element = super.findElement(by);
		} catch (NoSuchElementException e) {
			throw new BFElementNotFoundException(by);
		}
		return new NewRemoteWebElement(element);
	}
	
	/**
	 * @deprecated As of release 1.0.0, replaced by {@link #findElementDynamics(By)()}
	 */
	@Deprecated
	@Override
	public List<WebElement> findElements(By by) {
		List<WebElement> elements = new ArrayList<WebElement>();
		for (WebElement element : super.findElements(by)) {
			elements.add(new NewRemoteWebElement(element));
		}
		return elements;
	}
	
	@Override
	public void click() throws StaleElementReferenceException {
		if (clickTimerOn) {
			startClickTime = System.currentTimeMillis();
		}
		try {
			super.click();
		} catch (ElementNotVisibleException e) {
			SelectorComponents selectorComponents = new SelectorComponents(toString());
			throw new BFComponentStateException(selectorComponents.getTerm(), "click", "not visible");
		} catch (StaleElementReferenceException e) {
			By selector = getSelector();
			if (selector == null) {
				calculateClickTime();
				throw e;
			}
			if (!click(selector, CLICK_NUM)) {
				calculateClickTime();
				throw e;
			}
		}
		calculateClickTime();
	}
	
	private void calculateClickTime() {
		if (clickTimerOn) {
			totalClickTime += System.currentTimeMillis() - startClickTime;
		}
	}
	
	private boolean click(By selector, int callNum) {
		try {
			TimeUtills.waitMiliseconds(MICRO_SLEEP);
			getWrappedDriver().findElement(selector)
					.click();
			return true;
		} catch (StaleElementReferenceException e) {
			if (callNum > 0) {
				return click(selector, callNum - 1);
			}
		}
		return false;
	}
	
	private By getSelector() {
		SelectorComponents selectorComponents = new SelectorComponents(toString());
		
		String term = selectorComponents.getTerm();
		if (term == null) {
			BFLogger.logError("Selector is not defined");
			return null;
		}
		
		By selector = null;
		
		String locator = selectorComponents.getLocator();
		switch (locator) {
		case "className selector":
			selector = new By.ByClassName(term);
			break;
		case "css selector":
			selector = new By.ByCssSelector(term);
			break;
		case "id selector":
			selector = new By.ById(term);
			break;
		case "linkText selector":
			selector = new By.ByLinkText(term);
			break;
		case "name selector":
			selector = new By.ByName(term);
			break;
		case "partialLinkText selector":
			selector = new By.ByPartialLinkText(term);
			break;
		case "tagName selector":
			selector = new By.ByTagName(term);
			break;
		case "xpath selector":
			selector = new By.ByXPath(term);
			break;
		}
		BFLogger.logError(String.format("Locator '%s' not found", locator));
		return selector;
	}
	
	public static void setClickTimer() {
		clickTimerOn = true;
		totalClickTime = 0;
	}
	
	public static long dropClickTimer() {
		clickTimerOn = false;
		return totalClickTime;
	}
}
