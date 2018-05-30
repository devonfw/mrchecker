package com.capgemini.mrchecker.selenium.core.newDrivers.elementType;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.utils.ScrollUtils;

public class Button extends BasicElement {
	
	public Button(By cssSelector) {
		super(ElementType.BUTTON, cssSelector);
	}
	
	public void click() {
		ScrollUtils.scrollElementIntoView(getElement());
		getElement().click();
	}
	
}
