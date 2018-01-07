package com.capgemini.ntc.selenium.core.newDrivers.elementType;

import org.openqa.selenium.By;

/**
 * Created by LKURZAJ on 08.03.2017.
 */
public class TooltipElement extends BasicElement {
	
	public TooltipElement(By selector) {
		super(ElementType.TOOLTIP, selector);
	}
	
	public boolean isTextContains(String text) {
		return this.getElement()
				.getText()
				.contains(text);
	}
}
