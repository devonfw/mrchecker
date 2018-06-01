package com.capgemini.mrchecker.selenium.core.newDrivers.elementType;

import org.openqa.selenium.By;

/**
 * Created by LKURZAJ on 03.03.2017.
 */
public class LabelElement extends BasicElement {
	
	public LabelElement(By cssSelector) {
		super(ElementType.LABEL, cssSelector);
	}
	
	public String getText() {
		return this.getElement()
				.getText();
	}
}
