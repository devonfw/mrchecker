package com.capgemini.mrchecker.selenium.core.newDrivers.elementType;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.exceptions.BFElementNotFoundException;

public class RadioButtonElement extends BasicElement implements IBasicElement {
	
	private By inputChildsSelector;
	private List<WebElement> listElements;
	private List<String> listSelectedAttributes;
	
	public RadioButtonElement(By cssSelector)
	/**
	 * @param cssSelector
	 *            - selector of Radio Button element's set
	 **/
	{
		this(cssSelector, By.cssSelector("input"), Arrays.asList("selected"));
	}
	
	public RadioButtonElement(By cssSelector, By inputChildsSelector) {
		/**
		 * @param cssSelector
		 *            - selector of Radio Button element's set
		 * @param inputChildsSelector
		 *            - selector of relative path from Radio Button element's set to basic input element
		 **/
		this(ElementType.INPUT_TEXT, cssSelector, inputChildsSelector, Arrays.asList("selected"));
	}
	
	public RadioButtonElement(By cssSelector, By inputChildsSelector, List<String> listSelectedAttributes) {
		/**
		 * @param cssSelector
		 *            - selector of Radio Button element's set
		 * @param inputChildsSelector
		 *            - selector of relative path from Radio Button element's set to basic input element
		 * @param listSelectedAttributes
		 *            - list of class name describing selected item
		 **/
		this(ElementType.INPUT_TEXT, cssSelector, inputChildsSelector, listSelectedAttributes);
	}
	
	protected RadioButtonElement(ElementType elemType, By cssSelector, By inputChildsSelector,
			List<String> listSelectedAttributes) {
		/**
		 * @param cssSelector
		 *            - selector of Radio Button element's set
		 * @param inputChildsSelector
		 *            - selector of relative path from Radio Button element's set to basic input element
		 * @param listSelectedAttributes
		 *            - list of class name describing selected item
		 **/
		super(elemType, cssSelector);
		setInputChildsSelector(inputChildsSelector);
		setSelectedAttributes(listSelectedAttributes);
	}
	
	public int getSelectedItemIndex() {
		this.setItems();
		return this.listElements.indexOf(getSelectedItem());
	}
	
	public String getSelectedItemText() {
		return getSelectedItem().getText();
	}
	
	public String getSelectedItemValue() {
		return getSelectedItem().getAttribute("value");
	}
	
	public List<String> getTextList() {
		return Arrays.asList(this.getTextArray());
	}
	
	public int getItemsCount() {
		this.setItems();
		return this.listElements.size();
	}
	
	public boolean isItemSelectedByText(String elementText) {
		return this.getSelectedItemText()
				.equals(elementText);
	}
	
	public boolean isItemSelectedByIndex(int elementIndex) {
		return this.getSelectedItemIndex() == elementIndex;
	}
	
	public boolean isItemSelectedByValue(String elementValue) {
		return this.getSelectedItemValue()
				.equals(elementValue);
	}
	
	public void selectItemByText(String elementText) {
		WebElement elementToClick = this.getItemByText(elementText);
		elementToClick.click();
		this.checkIsItemClicked(elementToClick);
	}
	
	public void selectItemByIndex(int elementIndex) {
		WebElement elementToClick = this.getItemByIndex(elementIndex);
		elementToClick.click();
		this.checkIsItemClicked(elementToClick);
	}
	
	public void selectItemByValue(String elementValue) {
		WebElement elementToClick = this.getItemByValue(elementValue);
		elementToClick.click();
		this.checkIsItemClicked(elementToClick);
	}
	
	private void checkIsItemClicked(WebElement element) {
		if (this.isItemSelected(element)) {
			System.out.println(getElementTypeName() + ": " + this.toString() + " isn't clicked.");
		}
	}
	
	private String[] getTextArray() {
		return this.getElement()
				.getText()
				.trim()
				.split("\n");
	}
	
	private void setInputChildsSelector(By selector) {
		this.inputChildsSelector = selector;
	}
	
	private void setSelectedAttributes(List<String> listSelectedAttributes) {
		this.listSelectedAttributes = listSelectedAttributes;
	}
	
	private void setListItems(List<WebElement> listElements) {
		this.listElements = listElements;
	}
	
	private boolean isItemSelected(WebElement el) {
		return el.isSelected();
	}
	
	private void setItems() {
		setListItems(this.getElement()
				.findElements(inputChildsSelector));
	}
	
	private WebElement getItemByIndex(int index) {
		this.setItems();
		
		if (this.listElements.isEmpty()) {
			throw new BFElementNotFoundException("Any " + getElementTypeName() + " element was found.");
		}
		
		return this.listElements.get(index);
	}
	
	private WebElement getItemByText(String visibleText) {
		for (int i = 0; i < this.getItemsCount(); i++) {
			if (this.listElements.get(i)
					.getText()
					.equals(visibleText)) {
				return this.listElements.get(i);
			}
		}
		throw new BFElementNotFoundException(getElementTypeName() + " with text: " + visibleText + " wasn't found in "
				+ this.getTextArray()
						.toString());
	}
	
	private WebElement getItemByValue(String value) {
		this.setItems();
		for (int i = 0; i < this.getItemsCount(); i++) {
			if (this.listElements.get(i)
					.getAttribute("value")
					.equals(value)) {
				return this.listElements.get(i);
			}
		}
		throw new BFElementNotFoundException(
				getElementTypeName() + " with value: " + value + " wasn't found in " + this.getTextArray()
						.toString());
	}
	
	private boolean isClassContainSelectionAttributes(String classAttribute) {
		for (int i = 0; i < this.listSelectedAttributes.size(); i++) {
			if (classAttribute.contains(this.listSelectedAttributes.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	private WebElement getSelectedItem() {
		this.setItems();
		for (int i = 0; i < this.listElements.size(); i++) {
			if (this.isItemSelected(this.listElements.get(i))
					|| isClassContainSelectionAttributes(this.listElements.get(i)
							.getAttribute("class"))) {
				return this.listElements.get(i);
			}
		}
		throw new BFElementNotFoundException("Any element is selected in " + this.getTextArray()
				.toString());
	}
}