package com.capgemini.mrchecker.selenium.core.newDrivers.elementType;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.exceptions.BFComponentStateException;
import com.capgemini.mrchecker.selenium.core.exceptions.BFElementNotFoundException;

import java.util.Arrays;
import java.util.List;

public class CheckBox extends BasicElement {
	
	private By inputChildsSelector;
	
	public CheckBox(By cssSelector)
	/**
	 * @param cssSelector
	 *            - selector of CheckBox element's set
	 **/
	{
		this(cssSelector, By.cssSelector("input"));
	}
	
	public CheckBox(By cssSelector, By inputChildsSelector) {
		/**
		 * @param cssSelector
		 *            - selector of CheckBox element's set
		 * @param inputChildsSelector
		 *            - selector of relative path from CheckBox element's set to basic input element
		 **/
		super(ElementType.CHECKBOX, cssSelector);
		setInputChildsSelector(inputChildsSelector);
	}
	
	public void setCheckBoxByIndex(int index) {
		this.setCheckBoxByIndexTo(index, true);
	}
	
	public void setCheckBoxByValue(String value) {
		this.setCheckBoxByValueTo(value, true);
	}
	
	public void setCheckBoxByText(String text) {
		this.setCheckBoxByTextTo(text, true);
	}
	
	public void unsetCheckBoxByIndex(int index) {
		this.setCheckBoxByIndexTo(index, false);
	}
	
	public void unsetCheckBoxByValue(String value) {
		this.setCheckBoxByValueTo(value, false);
	}
	
	public void unsetCheckBoxByText(String text) {
		this.setCheckBoxByTextTo(text, false);
	}
	
	public void setAllCheckBoxes() {
		this.setAllCheckBoxesTo(true);
	}
	
	public void unsetAllCheckBoxes() {
		this.setAllCheckBoxesTo(false);
	}
	
	public boolean isCheckBoxSetByIndex(int index) {
		return this.getCheckBoxesList()
				.get(index)
				.isSelected();
	}
	
	public boolean isCheckBoxSetByValue(String value) {
		return this.getCheckBoxesList()
				.get(this.getCheckBoxIndexByValue(value))
				.isSelected();
	}
	
	public boolean isCheckBoxSetByText(String text) {
		return this.getCheckBoxesList()
				.get(this.getCheckBoxIndexByText(text))
				.isSelected();
	}
	
	public boolean isAllCheckboxesSet() {
		return this.isAllCheckBoxesSetTo(true);
	}
	
	public List<String> getTextList() {
		return Arrays.asList(getText().split("\n"));
	}
	
	private List<WebElement> getCheckBoxesList() {
		return this.getElement()
				.findElements(inputChildsSelector);
	}
	
	private void setCheckBoxByIndexTo(int index, boolean destination) {
		if (this.getCheckBoxesList()
				.get(index)
				.isSelected() != destination) {
			this.getCheckBoxesList()
					.get(index)
					.click();
		}
		
		boolean currentState = this.getCheckBoxesList()
				.get(index)
				.isSelected();
		if (currentState != destination) {
			throw new BFComponentStateException(ElementType.CHECKBOX.toString(), "set/unset",
					String.valueOf(currentState));
		}
	}
	
	private void setCheckBoxByValueTo(String value, boolean destination) {
		this.setCheckBoxToByAttribute(value, "value", destination);
	}
	
	private void setCheckBoxByTextTo(String text, boolean destination) {
		this.setCheckBoxByIndexTo(this.getCheckBoxIndexByText(text), destination);
	}
	
	private int getCheckBoxIndexByText(String text) {
		List<String> textsList = this.getTextList();
		for (int i = 0; i < textsList.size(); i++) {
			if (textsList.get(i)
					.equals(text.trim())) {
				return i;
			}
		}
		throw new BFElementNotFoundException("Checkbox with text " + text + " wasn't found.");
	}
	
	private int getCheckBoxIndexByValue(String value) {
		List<WebElement> checkBoxesList = this.getCheckBoxesList();
		for (int i = 0; i < checkBoxesList.size(); i++) {
			if (checkBoxesList.get(i)
					.getAttribute("value")
					.equals(value.trim())) {
				return i;
			}
		}
		throw new BFElementNotFoundException("Checkbox with value " + value + " wasn't found.");
	}
	
	private void setCheckBoxToByAttribute(String value, String attribute, boolean destination) {
		List<WebElement> checkboxesList = this.getCheckBoxesList();
		WebElement currentElement;
		for (int i = 0; i < checkboxesList.size(); i++) {
			currentElement = checkboxesList.get(i);
			if (currentElement.getAttribute(attribute)
					.equals(value) && currentElement.isSelected() != destination) {
				currentElement.click();
			}
		}
	}
	
	private void setAllCheckBoxesTo(boolean destination) {
		List<WebElement> checkboxesList = this.getCheckBoxesList();
		for (int i = 0; i < checkboxesList.size(); i++) {
			if (checkboxesList.get(i)
					.isSelected() != destination) {
				checkboxesList.get(i)
						.click();
			}
		}
		
		if (!this.isAllCheckBoxesSetTo(destination)) {
			throw new BFComponentStateException(ElementType.CHECKBOX.toString(), "set/unset",
					"setting to " + String.valueOf(destination));
		}
	}
	
	private boolean isAllCheckBoxesSetTo(boolean destination) {
		List<WebElement> checkboxesList = this.getCheckBoxesList();
		for (int i = 0; i < checkboxesList.size(); i++) {
			if (checkboxesList.get(i)
					.isSelected() != destination) {
				return false;
			}
		}
		return true;
	}
	
	private void setInputChildsSelector(By selector) {
		this.inputChildsSelector = selector;
	}
}