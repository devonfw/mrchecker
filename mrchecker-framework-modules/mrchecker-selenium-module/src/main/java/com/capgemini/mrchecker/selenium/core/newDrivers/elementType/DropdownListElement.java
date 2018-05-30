package com.capgemini.mrchecker.selenium.core.newDrivers.elementType;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.capgemini.mrchecker.selenium.core.exceptions.BFComponentStateException;

public class DropdownListElement extends BasicElement implements IBasicElement {
	
	public DropdownListElement(By cssSelector) {
		super(ElementType.DROPDOWN, cssSelector);
	}
	
	public void selectDropdownByIndex(int index) {
		this.getObject()
				.selectByIndex(index);
		List<WebElement> list = this.getObject()
				.getOptions();
		
		if (!list.get(index)
				.isSelected()) {
			throw new BFComponentStateException(ElementType.DROPDOWN.toString(), "select", "Option with index: "
					+ String.valueOf(index) + " should be set from in " + this.getObject()
							.toString());
		}
	}
	
	public boolean isDropdownElementSelectedByIndex(int index) {
		return this.getPossibleOptions()
				.get(index)
				.isSelected();
	}
	
	public void selectDropdownByValue(String value) {
		value.trim();
		this.getObject()
				.selectByValue(value);
		if (!this.isDropdownElementSelectedByValue(value)) {
			throw new BFComponentStateException(ElementType.DROPDOWN.toString(), "select", "Option with value: "
					+ String.valueOf(value) + " should be set from in " + this.getObject()
							.toString());
		}
	}
	
	public void selectDropdownByVisibleText(String value) {
		boolean flag = false;
		
		this.getObject()
				.selectByVisibleText(value);
		
		List<String> list = this.getAllSelectedOptionsText();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i)
					.equals(value))
				flag = true;
		}
		
		if (!flag) {
			throw new RuntimeException(
					"Option with text: " + value + " wasn't selected in " + this.getObject()
							.toString());
		}
	}
	
	public List<String> getAllSelectedOptionsText() {
		List<WebElement> list = this.getObject()
				.getAllSelectedOptions();
		return this.getValuesFromWebElements(list);
	}
	
	public String getFirstSelectedOptionText() {
		String output = this.getObject()
				.getFirstSelectedOption()
				.getText()
				.trim();
		return output;
	}
	
	public int getAmountOfPossibleValues() {
		List<WebElement> list = this.getObject()
				.getOptions();
		return list.size();
	}
	
	public boolean isDropdownElementSelectedByValue(String value) {
		int index = this.getIndexDropdownElementByValue(value);
		return this.getPossibleOptions()
				.get(index)
				.isSelected();
	}
	
	private List<WebElement> getPossibleOptions() {
		return this.getObject()
				.getOptions();
	}
	
	private List<String> getPossibleValuesText() {
		List<WebElement> list = this.getObject()
				.getOptions();
		return this.getValuesFromWebElements(list);
	}
	
	private int getIndexDropdownElementByValue(String value) {
		return this.getPossibleValuesText()
				.indexOf(value);
	}
	
	private Select getObject() {
		return new Select(this.getElement());
	}
	
	private List<String> getValuesFromWebElements(List<WebElement> list) {
		List<String> output = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			output.add(list.get(i)
					.getText()
					.trim());
		}
		return output;
	}
}
