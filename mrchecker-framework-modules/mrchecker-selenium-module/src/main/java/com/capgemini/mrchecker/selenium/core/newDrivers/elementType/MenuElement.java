package com.capgemini.mrchecker.selenium.core.newDrivers.elementType;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.exceptions.BFElementNotFoundException;
import com.capgemini.mrchecker.test.core.exceptions.BFInputDataException;

import java.util.Arrays;
import java.util.List;

/**
 * Created by LKURZAJ on 08.03.2017.
 */
public class MenuElement extends BasicElement {
	
	private By childsSelector;
	private By subMenuSelector;
	private By childsSubMenuSelector;
	
	public MenuElement(By cssSelector) {
		this(cssSelector, By.cssSelector("li"));
	}
	
	public MenuElement(By cssSelector, By childsSelector) {
		this(cssSelector, childsSelector, cssSelector, childsSelector);
	}
	
	public MenuElement(By cssSelector, By childsSelector, By subMenuSelector) {
		this(cssSelector, childsSelector, subMenuSelector, By.cssSelector("li"));
	}
	
	public MenuElement(By cssSelector, By childsSelector, By subMenuSelector, By childsSubMenuSelector) {
		super(ElementType.MENU, cssSelector);
		this.childsSelector = childsSelector;
		this.subMenuSelector = subMenuSelector;
		this.childsSubMenuSelector = childsSubMenuSelector;
	}
	
	public void selectItemByIndex(int index) {
		this.getItemByIndex(index)
				.click();
	}
	
	public void selectItemByText(String text) {
		this.getItemByText(text)
				.click();
	}
	
	public String getItemLinkByIndex(int index) {
		return getLinkItemFromWebElement(getItemByIndex(index)).getAttribute("href");
	}
	
	public String getItemLinkByText(String text) {
		return getLinkItemFromWebElement(getItemByText(text)).getAttribute("href");
	}
	
	public List<String> getItemsTextList() {
		return Arrays.asList(this.getText()
				.split("\n"));
	}
	
	public void selectSubMenuItemByText(String itemText, String subItemText) {
		this.getSubItemByText(itemText, subItemText)
				.click();
	}
	
	public void selectSubMenuItemByIndex(int itemIndex, int subItemIndex) {
		this.getSubItemByIndex(itemIndex, subItemIndex)
				.click();
	}
	
	public String getSubMenuItemLinkByText(String itemText, String subItemText) {
		return getLinkItemFromWebElement(this.getSubItemByText(itemText, subItemText)).getAttribute("href");
	}
	
	public String getSubMenuItemLinkByIndex(int itemIndex, int subItemIndex) {
		return getLinkItemFromWebElement(this.getSubItemByIndex(itemIndex, subItemIndex)).getAttribute("href");
	}
	
	public int getItemsCount() {
		return this.getItems()
				.size();
	}
	
	private void clickMenuItemAndWaitForSubMenuVisible(WebElement menuItem) {
		menuItem.click();
		BasePage.getAction()
				.moveToElement(menuItem)
				.perform();
		BasePage.getDriver()
				.waitForElementVisible(this.subMenuSelector);
	}
	
	private WebElement getLinkItemFromWebElement(WebElement webElement) {
		return webElement.findElements(By.cssSelector("a"))
				.get(0);
	}
	
	private WebElement getSubItemByIndex(int itemIndex, int subItemIndex) {
		WebElement elem = this.getItemByIndex(itemIndex);
		this.clickMenuItemAndWaitForSubMenuVisible(elem);
		return this.getElementByIndex(this.getItemSubItems(elem), subItemIndex);
	}
	
	private WebElement getSubItemByText(String itemText, String subItemText) {
		WebElement webElement = getItemByText(itemText);
		this.clickMenuItemAndWaitForSubMenuVisible(webElement);
		return this.getElementByText(this.getItemSubItems(webElement), subItemText);
	}
	
	private List<WebElement> getItemSubItems(WebElement webElement) {
		return webElement.findElements(this.childsSubMenuSelector);
	}
	
	private List<WebElement> getItemSubItemsByText(WebElement webElement) {
		this.clickMenuItemAndWaitForSubMenuVisible(webElement);
		return this.getItemSubItems(webElement);
	}
	
	private WebElement getItemByText(String text) {
		return getElementByText(this.getItems(), text);
	}
	
	private WebElement getItemByIndex(int index) {
		return this.getElementByIndex(this.getItems(), index);
	}
	
	private List<WebElement> getItems() {
		return this.getElement()
				.findElements(this.childsSelector);
	}
	
	private WebElement getElementByIndex(List<WebElement> listElements, int index) {
		if (index < 0 || index >= listElements.size()) {
			throw new BFInputDataException("Index out of range: 0 - " + String.valueOf(listElements.size() - 1));
		}
		return listElements.get(index);
	}
	
	private WebElement getElementByText(List<WebElement> listElements, String text) {
		for (int i = 0; i < listElements.size(); i++) {
			if (listElements.get(i)
					.getText()
					.equals(text)) {
				return listElements.get(i);
			}
		}
		throw new BFElementNotFoundException("Item with text: " + text + " not found in " + listElements.toString());
	}
}
