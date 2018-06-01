package com.capgemini.mrchecker.selenium.core.newDrivers.elementType;

import org.openqa.selenium.By;

public class InputTextElement extends BasicElement implements IBasicElement {
	
	public InputTextElement(By cssSelector) {
		super(ElementType.INPUT_TEXT, cssSelector);
	}
	
	public void setInputText(String text) {
		getElement().sendKeys(text);
	}
	
	public void clearInputText() {
		getElement().clear();
	}
	
	public void submit() {
		getElement().submit();
	}
	
}
