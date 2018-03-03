package com.capgemini.ntc.selenium.core.newDrivers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.exceptions.BFElementNotFoundException;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.Button;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.CheckBox;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.DropdownListElement;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.HorizontalSliderElement;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.IFrame;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.InputTextElement;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.LabelElement;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.ListElements;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.MenuElement;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.NavigationBarElement;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.RadioButtonElement;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.TabElement;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.TooltipElement;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class DriverExtention {
	
	private INewWebDriver driver;
	
	public DriverExtention(INewWebDriver driver) {
		this.setDriver(driver);
	}
	
	public INewWebDriver getDriver() {
		return driver;
	}
	
	public WebElement findElementQuietly(By by) {
		return this.findElementQuietly(null, by);
	}
	
	public WebElement findElementQuietly(WebElement elementToSearchIn, By by) {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		
		WebElement element = null;
		try {
			if (null == elementToSearchIn) {
				element = getDriver().findElement(by);
			} else {
				element = new NewRemoteWebElement(elementToSearchIn).findElement(by);
			}
		} catch (NoSuchElementException e) {
			BFLogger.logError("Element [" + by.toString() + "] was not found in given element");
		}
		return element;
	}
	
	public WebElement findElementDynamic(By by) throws BFElementNotFoundException {
		long startTime = System.currentTimeMillis();
		return findElementDynamicBasic(by, startTime);
	}
	
	public WebElement findElementDynamic(final By by, int timeOut) throws BFElementNotFoundException {
		long startTime = System.currentTimeMillis();
		return findElementDynamicBasic(by, startTime, timeOut);
	}
	
	public WebElement findElementDynamic(WebElement elementToSearchIn, By by) throws BFElementNotFoundException {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		long startTime = System.currentTimeMillis();
		WebElement element;
		if (null == elementToSearchIn) {
			element = this.findElementDynamic(by);
		} else {
			element = findElementDynamicBasic(by, startTime);
		}
		return element;
	}
	
	private WebElement findElementDynamicBasic(By by, long startTime) throws BFElementNotFoundException {
		int timeOut = BasePage.EXPLICITYWAITTIMER;
		return this.findElementDynamicBasic(by, startTime, timeOut);
	}
	
	private WebElement findElementDynamicBasic(By by, long startTime, int timeOut) throws BFElementNotFoundException {
		WebElement element = null;
		WebDriverWait wait = webDriverWait(timeOut);
		try {
			element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
		} catch (TimeoutException | NoSuchElementException e) {
			boolean isTimeout = true;
			throw new BFElementNotFoundException(by, isTimeout, timeOut);
		}
		BFLogger.logTime(startTime, "findElementDynamic()", by.toString());
		return element;
	}
	
	public List<WebElement> findElementsDynamic(By by, int timeOut) throws BFElementNotFoundException {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
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
		BFLogger.logTime(startTime, "findElementDynamics()", by.toString());
		return elements;
	}
	
	public List<WebElement> findElementsDynamic(By by) throws BFElementNotFoundException {
		return findElementsDynamic(by, BasePage.EXPLICITYWAITTIMER);
	}
	
	public WebElement waitForElement(final By by) throws BFElementNotFoundException {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
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
		BFLogger.logTime(startTime, "waitForElement()", by.toString());
		return element;
	}
	
	public WebElement waitUntilElementIsClickable(final By by) {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		long startTime = System.currentTimeMillis();
		WebElement element = null;
		try {
			element = webDriverWait().until(ExpectedConditions.elementToBeClickable(by));
		} catch (TimeoutException | NoSuchElementException e) {
			boolean isTimeout = true;
			throw new BFElementNotFoundException(by, isTimeout, BasePage.EXPLICITYWAITTIMER);
		}
		BFLogger.logTime(startTime, "waitUntilElementIsClickable()", by.toString());
		return element;
	}
	
	public WebElement waitForElementVisible(final By by) throws BFElementNotFoundException {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		long startTime = System.currentTimeMillis();
		
		WebElement element = null;
		try {
			element = webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (TimeoutException | NoSuchElementException e) {
			boolean isTimeout = true;
			throw new BFElementNotFoundException(by, isTimeout, BasePage.EXPLICITYWAITTIMER);
		}
		BFLogger.logTime(startTime, "waitForElementVisible()", by.toString());
		return element;
	}
	
	public void waitForPageLoaded() throws BFElementNotFoundException {
		long startTime = System.currentTimeMillis();
		final String jsVariable = "return document.readyState";
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript(jsVariable)
								.equals("complete");
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
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		return new Button(selector);
	}
	
	public RadioButtonElement elementRadioButton(By selector) {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		return new RadioButtonElement(selector);
	}
	
	public RadioButtonElement elementRadioButton(By selector, By childs) {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		return new RadioButtonElement(selector, childs);
	}
	
	public RadioButtonElement elementRadioButton(By selector, By childs, List<String> selectedAttributes) {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		return new RadioButtonElement(selector, childs, selectedAttributes);
	}
	
	public InputTextElement elementInputText(By selector) {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		return new InputTextElement(selector);
	}
	
	public DropdownListElement elementDropdownList(By selector) {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		return new DropdownListElement(selector);
	}
	
	public ListElements elementList(By selector) {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		return new ListElements(selector);
	}
	
	public CheckBox elementCheckbox(By selector) {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		return new CheckBox(selector);
	}
	
	public CheckBox elementCheckbox(By selector, By childs) {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		return new CheckBox(selector, childs);
	}
	
	public LabelElement elementLabel(By selector) {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		return new LabelElement(selector);
	}
	
	public TabElement elementTab(By selector) {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		return new TabElement(selector);
	}
	
	public TabElement elementTab(By selector, By childs) {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		return new TabElement(selector, childs);
	}
	
	public TabElement elementTab(By selector, By childs, List<String> selectedAttributes) {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		return new TabElement(selector, childs, selectedAttributes);
	}
	
	public NavigationBarElement elementNavigationBar(By selector) {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		return new NavigationBarElement(selector);
	}
	
	public NavigationBarElement elementNavigationBar(By selector, By inputChildsSelector) {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		return new NavigationBarElement(selector, inputChildsSelector);
	}
	
	public TooltipElement elementTooltip(By selector) {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		return new TooltipElement(selector);
	}
	
	public MenuElement elementMenu(By selector) {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		return new MenuElement(selector);
	}
	
	public MenuElement elementMenu(By selector, By childsSelector) {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		return new MenuElement(selector, childsSelector);
	}
	
	public MenuElement elementMenu(By selector, By childsSelector, By subMenuSelector) {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		return new MenuElement(selector, childsSelector, subMenuSelector);
	}
	
	public MenuElement elementMenu(By selector, By childsSelector, By subMenuSelector, By childsSubMenuSelector) {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		return new MenuElement(selector, childsSelector, subMenuSelector, childsSubMenuSelector);
	}
	
	public HorizontalSliderElement elementHorizontalSlider(final By sliderContainerSelector) {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		return new HorizontalSliderElement(sliderContainerSelector);
	}
	
	public HorizontalSliderElement elementHorizontalSlider(final By sliderContainerSelector, final By sliderSelector, final By valueSelector) {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		return new HorizontalSliderElement(sliderContainerSelector, sliderSelector, valueSelector);
	}
	
	public HorizontalSliderElement elementHorizontalSlider(final By sliderContainerSelector,
					final By sliderSelector,
					final By valueSelector,
					final BigDecimal minRange,
					final BigDecimal maxRange,
					final BigDecimal step) {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		return new HorizontalSliderElement(sliderContainerSelector, sliderSelector, valueSelector, minRange, maxRange, step);
	}
	
	public IFrame elementIFrame(By selector) {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		return new IFrame(selector);
	}
	
	public void mouseRightClick(By selector) {
		WebElement element = findElementDynamic(selector);
		Actions action = new Actions(getDriver()).contextClick(element);
		action.build()
						.perform();
	}
	
	public void mouseLeftClick(By selector) {
		WebElement element = getDriver().findElementQuietly(selector);
		if (element != null) {
			mouseLeftClick(element);
		} else {
			BFLogger.logDebug("Unable to perform left mouse click due to null WebElement");
		}
	}
	
	public void mouseLeftClick(WebElement element) {
		new Actions(getDriver()).click(element)
						.build()
						.perform();
	}
	
}
