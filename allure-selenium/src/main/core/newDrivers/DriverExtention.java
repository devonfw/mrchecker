package com.example.selenium.core.newDrivers;

import java.util.*;

import com.example.selenium.core.BasePage;
import com.example.selenium.core.exceptions.BFElementNotFoundException;
import com.example.selenium.core.newDrivers.elementType.*;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.*;

import com.example.core.logger.BFLogger;

public class DriverExtention {

	private INewWebDriver driver;

	public DriverExtention(INewWebDriver driver) {
		this.setDriver(driver);
	}

	public INewWebDriver getDriver() {
		return driver;
	}

	public WebElement findElementQuietly(WebElement elementToSearchIn, By searchedBySelector) {
		WebElement element = null;

		try {
			if (null == elementToSearchIn) {
				element = getDriver().findElement(searchedBySelector);
			} else {
				element = new NewRemoteWebElement(elementToSearchIn).findElement(searchedBySelector);
			}
		} catch (NoSuchElementException e) {
			BFLogger.logError("Element [" + searchedBySelector.toString() + "] was not found in given element");
		}
		return element;
	}

	public WebElement findDynamicElement(final By by, int timeOut) throws BFElementNotFoundException {
		long startTime = System.currentTimeMillis();
		WebDriverWait wait = webDriverWait(timeOut);
		WebElement element = null;
		try {
			element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
		} catch (TimeoutException | NoSuchElementException e) {
			boolean isTimeout = true;
			throw new BFElementNotFoundException(by, isTimeout, timeOut);
		}
		BFLogger.logTime(startTime, "findDynamicElement", by);
		return element;
	}

	public WebElement findDynamicElement(By by) throws BFElementNotFoundException {
		return findDynamicElement(by, BasePage.EXPLICITYWAITTIMER);
	}

	public List<WebElement> findDynamicElements(By by, int timeOut) throws BFElementNotFoundException {
		long startTime = System.currentTimeMillis();
		WebDriverWait wait = webDriverWait(timeOut);
		List<WebElement> elements = new ArrayList<WebElement>();
		try {
			elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
		} catch (BFElementNotFoundException | TimeoutException e) {
			throw new BFElementNotFoundException(by, true, timeOut);
		}
		if (elements.isEmpty()) {
			BFLogger.logError("Not found element : " + by.toString() + ".");
		}
		BFLogger.logTime(startTime, "findDynamicElements", by);
		return elements;
	}

	public List<WebElement> findDynamicElements(By by) throws BFElementNotFoundException {
		return findDynamicElements(by, BasePage.EXPLICITYWAITTIMER);
	}

	public WebElement waitForElement(final By by) throws BFElementNotFoundException {
		long startTime = System.currentTimeMillis();
		WebElement element = null;
		try {
			element = webDriverWait().until(new ExpectedCondition<WebElement>() {
				@Override
				public WebElement apply(WebDriver driver) {
					return driver.findElement(by);
				}
			});
		} catch (TimeoutException | NoSuchElementException e) {
			boolean isTimeout = true;
			throw new BFElementNotFoundException(by, isTimeout, BasePage.EXPLICITYWAITTIMER);
		}
		BFLogger.logTime(startTime, "waitForElement", by);
		return element;
	}

	public WebElement waitUntilElementIsClickable(final By by) {
		long startTime = System.currentTimeMillis();
		WebElement element = null;
		try {
			element = webDriverWait().until(ExpectedConditions.elementToBeClickable(by));
		} catch (TimeoutException | NoSuchElementException e) {
			boolean isTimeout = true;
			throw new BFElementNotFoundException(by, isTimeout, BasePage.EXPLICITYWAITTIMER);
		}
		BFLogger.logTime(startTime, "waitUntilElementIsClickable", by);
		return element;
	}

	public WebElement waitForElementVisible(final By by) throws BFElementNotFoundException {
		long startTime = System.currentTimeMillis();

		WebElement element = null;
		try {
			element = webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (TimeoutException | NoSuchElementException e) {
			boolean isTimeout = true;
			throw new BFElementNotFoundException(by, isTimeout, BasePage.EXPLICITYWAITTIMER);
		}
		BFLogger.logTime(startTime, "waitForElementVisible", by);
		return element;
	}

	public void waitForPageLoaded() throws BFElementNotFoundException {
		long startTime = System.currentTimeMillis();
		final String jsVariable = "return document.readyState";
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript(jsVariable).equals("complete");
			}
		};
		int progressBarWaitTimer = BasePage.PROGRESSBARWAITTIMER;
		WebDriverWait wait = webDriverWait(progressBarWaitTimer);
		try {
			wait.until(expectation);
		} catch (TimeoutException | NoSuchElementException e) {
			boolean isTimeout = true;
			throw new BFElementNotFoundException(By.cssSelector(jsVariable), isTimeout, progressBarWaitTimer);
		}
		BFLogger.logTime(startTime, "waitForPageLoaded");
	}

	private void setDriver(INewWebDriver driver) {
		this.driver = driver;
	}

	private WebDriverWait webDriverWait() {
		return webDriverWait(BasePage.EXPLICITYWAITTIMER);
	}

	private WebDriverWait webDriverWait(int timeOut) {
		return new WebDriverWait(getDriver(), timeOut);
	}

	static List<WebElement> convertWebElementList(List<WebElement> elementList) {
		List<WebElement> elementsList = new ArrayList<WebElement>();
		for (WebElement element : elementList) {
			elementsList.add(new NewRemoteWebElement(element));
		}
		return elementsList;
	}

	public Button elementButton(By selector) {
		return new Button(selector);
	}

	public RadioButtonElement elementRadioButton(By selector) {
		return new RadioButtonElement(selector);
	}

	public RadioButtonElement elementRadioButton(By selector, By childs) {
		return new RadioButtonElement(selector,childs);
	}

	public RadioButtonElement elementRadioButton(By selector,By childs, List<String> selectedAttributes){return new RadioButtonElement(selector, childs,selectedAttributes);}

	public InputTextElement elementInputText(By selector) {
		return new InputTextElement(selector);
	}

	public DropdownListElement elementDropdownList(By selector) {
		return new DropdownListElement(selector);
	}

	public ListElements elementList(By selector) {
		return new ListElements(selector);
	}

	public CheckBox elementCheckbox(By selector) {
		return new CheckBox(selector);
	}

	public CheckBox elementCheckbox(By selector, By childs) {
		return new CheckBox(selector, childs);
	}

	public LabelElement elementLabel(By selector) {
		return new LabelElement(selector);
	}

	public TabElement elementTab(By selector){return new TabElement(selector);}

	public TabElement elementTab(By selector,By childs){return new TabElement(selector, childs);}

	public TabElement elementTab(By selector,By childs, List<String> selectedAttributes){return new TabElement(selector, childs,selectedAttributes);}

	public NavigationBarElement elementNavigationBar(By selector){return new NavigationBarElement(selector);}

	public NavigationBarElement elementNavigationBar(By selector,By inputChildsSelector){return new NavigationBarElement(selector,inputChildsSelector);}

	public TooltipElement elementTooltip(By selector){return new TooltipElement(selector);}

	public MenuElement elementMenu(By selector){return new MenuElement(selector);}

	public MenuElement elementMenu(By selector,By childsSelector){return new MenuElement(selector,childsSelector);}

	public MenuElement elementMenu(By selector,By childsSelector,By subMenuSelector){return new MenuElement(selector,childsSelector,subMenuSelector);}

	public MenuElement elementMenu(By selector,By childsSelector,By subMenuSelector,By childsSubMenuSelector){return new MenuElement(selector,childsSelector,subMenuSelector,childsSubMenuSelector);}

	public IFrame elementIFrame(By selector) {
		return new IFrame(selector);
	}
}
