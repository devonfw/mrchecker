package com.capgemini.ntc.selenium.core.newDrivers;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.exceptions.BFElementNotFoundException;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.*;

public interface INewWebDriver extends WebDriver {

	/**
	 * Try to find element quietly - NoSuchElementException will not be thrown. Use it to check if element exist on page
	 * e.g. PopUp
	 * 
	 * @param elementToSearchIn
	 * @param searchedBySelector
	 * @return found WebElement or null if couldn't find
	 */
	WebElement findElementQuietly(WebElement elementToSearchIn, By searchedBySelector);

	/**
	 * Try to find element quietly - NoSuchElementException will not be thrown. Use it to check if element exist on page
	 * e.g. PopUp
	 * 
	 * @param searchedBySelector
	 * @return found WebElement or null if couldn't find
	 */
	WebElement findElementQuietly(By searchedBySelector);

	/**
	 * 
	 * 
	 * /** Try to find by dynamic element - will wait given by user an amount of seconds for an element to load on page.
	 * If element will not be found will throw an exception (PiAtElementNotLoadedException)
	 * 
	 * @param by
	 *            selector
	 * @param timeout
	 *            - maximum time to wait for search WebElement
	 * @return found WebElement object
	 * @author
	 * @throws BFElementNotFoundException
	 */
	WebElement findDynamicElement(By by, int timeOut) throws BFElementNotFoundException;

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
	WebElement findDynamicElement(By by) throws BFElementNotFoundException;

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
	 * @throws BFElementNotFoundException
	 */
	List<WebElement> findDynamicElements(By by, int timeOut);

	/**
	 * Try to find by dynamic List of elements - will wait {@link BasePage.EXPLICITYWAITTIMER} seconds for an element to
	 * load on page. If element will not be found then throw an exception (PiAtElementNotLoadedException)
	 * 
	 * @param by
	 *            selector
	 * @return found WebElement object
	 * @author
	 * @throws BFElementNotFoundException
	 */
	List<WebElement> findDynamicElements(By by);

	/**
	 * Waits {@link BasePage.EXPLICITYWAITTIMER} seconds until an element will be enabled to action. If element after
	 * this time will not be enabled then throw an exception (PiAtElementNotFoundException)
	 * 
	 * @param by
	 *            selector
	 * @return WebElement object ready to click
	 * @author
	 * @throws BFElementNotFoundException
	 */
	WebElement waitForElement(final By selector);

	/**
	 * Waits {@link BasePage.EXPLICITYWAITTIMER} seconds until an element will be clickable. If element will not be
	 * clickable then throw an exception (PiAtElementNotFoundException)
	 * 
	 * @param by
	 *            selector
	 * @return WebElement object ready to click
	 * @author
	 * @throws BFElementNotFoundException
	 */
	WebElement waitUntilElementIsClickable(final By selector);

	/**
	 * Waits for element located by selector to become visible. Throws exception if element is not visible after
	 * specified time period.
	 * 
	 * @param by
	 *            selector
	 * @return found WebElement object
	 * @author
	 * @throws BFElementNotFoundException
	 */
	WebElement waitForElementVisible(final By selector);

	/**
	 * This function force browser (especially usable for IE ) to wait till page is loaded
	 * 
	 * @param driver
	 * @throws BFElementNotFoundException
	 */
	void waitForPageLoaded();

	/**
	 * Operations on Button
	 */
	Button elementButton(final By selector);

	/**
	 * Operations on Radion Buttons
	 */
	RadioButtonElement elementRadioButton(final By selector);

	RadioButtonElement elementRadioButton(final By selector, final By inputChildsSelector);

	RadioButtonElement elementRadioButton(final By selector, final By inputChildsSelector, final List<String> listSelectedAttributes);


	/**
	 * Operations on Input Text field
	 */
	InputTextElement elementInputText(final By selector);

	/**
	 * Operations on Dropdown List
	 */
	DropdownListElement elementDropdownList(final By selector);

	/**
	 * Operations on Lists
	 */
	ListElements elementList(final By selector);

	/**
	 * Operations on Checkbox
	 */
	CheckBox elementCheckbox(final By selector);

	/**
	 * Operations on Label
	 */
	LabelElement elementLabel(final By selector);

	/**
	 * Operations on Tabs
	 */
	TabElement elementTab(final By selector);

	TabElement elementTab(final By selector, final By inputChildsSelector);

	TabElement elementTab(final By selector, final By inputChildsSelector, final List<String> listSelectedAttributes);

	/**
	 * Operations on Navigation Bar
	 */
	NavigationBarElement elementNavigationBar(final By selector);

	NavigationBarElement elementNavigationBar(final By selector, final By inputChildsSelector);

	/**
	 * Operations on Tooltip
	 */
	TooltipElement elementTooltip(final By cssSelector);

	/**
	 *  Operations on Menu
	 */
	MenuElement elementMenu(final By selector);

	MenuElement elementMenu(final By selector,final By childsSelector);

	MenuElement elementMenu(final By selector,final By childsSelector,final By subMenuSelector);

	MenuElement elementMenu(final By selector,final By childsSelector,final By subMenuSelector,final By childsSubMenuSelector);

	/**
	 * Operations on iFrame
	 */
	IFrame elementIFrame(By selector);
}
