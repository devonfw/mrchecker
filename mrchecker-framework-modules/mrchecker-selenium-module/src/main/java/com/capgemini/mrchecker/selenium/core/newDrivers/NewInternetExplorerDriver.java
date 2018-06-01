package com.capgemini.mrchecker.selenium.core.newDrivers;

import java.math.BigDecimal;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.exceptions.BFElementNotFoundException;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.Button;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.CheckBox;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.DropdownListElement;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.HorizontalSliderElement;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.IFrame;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.InputTextElement;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.LabelElement;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.ListElements;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.MenuElement;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.NavigationBarElement;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.RadioButtonElement;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.TabElement;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.TooltipElement;

public final class NewInternetExplorerDriver extends InternetExplorerDriver implements INewWebDriver {
	
	private DriverExtention driverExtention;
	
	public NewInternetExplorerDriver(Capabilities capabilities) {
		super(capabilities);
		driverExtention = new DriverExtention(this);
	}
	
	/**
	 * @deprecated As of release 1.0.0, replaced by {@link #findElementDynamics(By)()}
	 */
	@Deprecated
	@Override
	public List<WebElement> findElements(By by) {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		return DriverExtention.convertWebElementList(super.findElements(by));
	}
	
	/**
	 * @deprecated As of release 1.0.0, replaced by {@link #findElementDynamic(By)()}
	 */
	@Deprecated
	@Override
	public WebElement findElement(By by) throws BFElementNotFoundException {
		BasePage.getAnalytics()
						.sendMethodEvent(BasePage.analitycsCategoryName);
		WebElement elementFromDriver = null;
		try {
			elementFromDriver = super.findElement(by);
		} catch (NoSuchElementException e) {
			throw new BFElementNotFoundException(by);
		}
		return new NewRemoteWebElement(elementFromDriver);
	}
	
	/**
	 * Try to find element quietly - NoSuchElementException will not be thrown. Use it to check if element exist on page
	 * e.g. PopUp
	 * 
	 * @param elementToSearchIn
	 * @param searchedBySelector
	 * @return found WebElement or null if couldn't find
	 */
	@Override
	public WebElement findElementQuietly(WebElement elementToSearchIn, By searchedBySelector) {
		return driverExtention.findElementQuietly(elementToSearchIn, searchedBySelector);
	}
	
	/**
	 * Try to find element quietly - NoSuchElementException will not be thrown. Use it to check if element exist on page
	 * e.g. PopUp
	 * 
	 * @param searchedBySelector
	 * @return found WebElement or null if couldn't find
	 */
	@Override
	public WebElement findElementQuietly(By searchedBySelector) {
		return driverExtention.findElementQuietly(null, searchedBySelector);
	}
	
	/**
	 * Try to find by dynamic element - will wait given by user an amount of seconds for an element to load on page. If
	 * element will not be found will throw an exception (PiAtElementNotLoadedException)
	 * 
	 * @param by
	 *            selector
	 * @param timeout
	 *            - maximum time to wait for search WebElement
	 * @return found WebElement object
	 * @author
	 * @throws BFElementNotFoundException
	 */
	public WebElement findElementDynamic(By by, int timeOut) throws BFElementNotFoundException {
		return driverExtention.findElementDynamic(by, timeOut);
	}
	
	/**
	 * Try to find by dynamic element - will wait given by user an amount of seconds for an element to load on page. If
	 * element will not be found will throw an exception (PiAtElementNotLoadedException)
	 * 
	 * @param by
	 *            selector
	 * @return found WebElement object
	 * @author
	 * @throws BFElementNotFoundException
	 */
	public WebElement findElementDynamic(By by) throws BFElementNotFoundException {
		return driverExtention.findElementDynamic(by);
	}
	
	/**
	 * Try to find by dynamic List of elements - will wait given by user an amount of seconds for an element to load on
	 * page. If element will not be found will throw an exception (PiAtElementNotLoadedException)
	 * 
	 * @param by
	 *            selector
	 * @param timeout
	 *            - maximum time to wait for search WebElement
	 * @return found WebElement object
	 * @author
	 */
	public List<WebElement> findElementDynamics(By by, int timeOut) {
		return driverExtention.findElementsDynamic(by, timeOut);
	}
	
	/**
	 * Try to find by dynamic List of elements - will wait {@link BasePage.EXPLICITYWAITTIMER} seconds for an element to
	 * load on page. If element will not be found will throw an exception (PiAtElementNotLoadedException)
	 * 
	 * @param by
	 *            selector
	 * @return found WebElement object
	 * @author
	 */
	public List<WebElement> findElementDynamics(By by) {
		return driverExtention.findElementsDynamic(by);
	}
	
	public WebElement waitForElement(final By selector) {
		return driverExtention.waitForElement(selector);
	}
	
	/**
	 * Waits {@link BasePage.EXPLICITYWAITTIMER} seconds until an element will be clickable. If element will not be
	 * clickable then throw an exception (BFElementNotFoundException)
	 * 
	 * @param by
	 *            selector
	 * @return WebElement object ready to click
	 * @author
	 * @throws BFElementNotFoundException
	 */
	public WebElement waitUntilElementIsClickable(final By selector) {
		return driverExtention.waitUntilElementIsClickable(selector);
	}
	
	public WebElement waitForElementVisible(final By selector) {
		return driverExtention.waitForElementVisible(selector);
	}
	
	@Override
	public void waitForPageLoaded() {
		driverExtention.waitForPageLoaded();
		
	}
	
	@Override
	public Button elementButton(By selector) {
		return driverExtention.elementButton(selector);
	}
	
	@Override
	public RadioButtonElement elementRadioButton(By selector) {
		return driverExtention.elementRadioButton(selector);
	}
	
	@Override
	public RadioButtonElement elementRadioButton(By selector, By inputChildsSelector) {
		return driverExtention.elementRadioButton(selector, inputChildsSelector);
	}
	
	@Override
	public RadioButtonElement elementRadioButton(By selector,
					By inputChildsSelector,
					List<String> listSelectedAttributes) {
		return driverExtention.elementRadioButton(selector, inputChildsSelector, listSelectedAttributes);
	}
	
	@Override
	public InputTextElement elementInputText(By selector) {
		return driverExtention.elementInputText(selector);
	}
	
	@Override
	public DropdownListElement elementDropdownList(By selector) {
		return driverExtention.elementDropdownList(selector);
	}
	
	@Override
	public ListElements elementList(By selector) {
		return driverExtention.elementList(selector);
	}
	
	@Override
	public CheckBox elementCheckbox(By selector) {
		return driverExtention.elementCheckbox(selector);
	}
	
	@Override
	public LabelElement elementLabel(By selector) {
		return driverExtention.elementLabel(selector);
	}
	
	@Override
	public TabElement elementTab(By selector) {
		return driverExtention.elementTab(selector);
	}
	
	@Override
	public TabElement elementTab(By selector, By inputChildsSelector) {
		return driverExtention.elementTab(selector, inputChildsSelector);
	}
	
	@Override
	public TabElement elementTab(By selector, By inputChildsSelector, List<String> listSelectedAttributes) {
		return driverExtention.elementTab(selector, inputChildsSelector, listSelectedAttributes);
	}
	
	@Override
	public NavigationBarElement elementNavigationBar(By selector) {
		return driverExtention.elementNavigationBar(selector);
	}
	
	@Override
	public NavigationBarElement elementNavigationBar(By selector, By inputChildsSelector) {
		return driverExtention.elementNavigationBar(selector, inputChildsSelector);
	}
	
	@Override
	public TooltipElement elementTooltip(By cssSelector) {
		return driverExtention.elementTooltip(cssSelector);
	}
	
	@Override
	public MenuElement elementMenu(By selector) {
		return driverExtention.elementMenu(selector);
	}
	
	@Override
	public MenuElement elementMenu(By selector, By childsSelector) {
		return driverExtention.elementMenu(selector, childsSelector);
	}
	
	@Override
	public MenuElement elementMenu(By selector, By childsSelector, By subMenuSelector) {
		return driverExtention.elementMenu(selector, childsSelector, subMenuSelector);
	}
	
	@Override
	public MenuElement elementMenu(By selector, By childsSelector, By subMenuSelector, By childsSubMenuSelector) {
		return driverExtention.elementMenu(selector, childsSelector, subMenuSelector, childsSubMenuSelector);
	}
	
	@Override
	public HorizontalSliderElement elementHorizontalSlider(final By sliderContainerSelector) {
		return driverExtention.elementHorizontalSlider(sliderContainerSelector);
	}
	
	@Override
	public HorizontalSliderElement elementHorizontalSlider(final By sliderContainerSelector, final By sliderSelector, final By valueSelector) {
		return driverExtention.elementHorizontalSlider(sliderContainerSelector, sliderSelector, valueSelector);
	}
	
	@Override
	public HorizontalSliderElement elementHorizontalSlider(final By sliderContainerSelector,
					final By sliderSelector,
					final By valueSelector,
					final BigDecimal minRange,
					final BigDecimal maxRange,
					final BigDecimal step) {
		return driverExtention.elementHorizontalSlider(sliderContainerSelector, sliderSelector, valueSelector, minRange, maxRange, step);
	}
	
	@Override
	public IFrame elementIFrame(By selector) {
		return driverExtention.elementIFrame(selector);
	}
	
	@Override
	public void mouseRightClick(By selector) {
		driverExtention.mouseRightClick(selector);
	}
	
	@Override
	public void mouseLeftClick(By selector) {
		driverExtention.mouseLeftClick(selector);
	}
	
	@Override
	public void mouseLeftClick(WebElement element) {
		driverExtention.mouseLeftClick(element);
	}
}