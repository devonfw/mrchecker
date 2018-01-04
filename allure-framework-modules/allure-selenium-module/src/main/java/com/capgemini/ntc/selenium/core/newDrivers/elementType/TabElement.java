package com.capgemini.ntc.selenium.core.newDrivers.elementType;

import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

/**
 * Created by LKURZAJ on 06.03.2017.
 */
public class TabElement extends RadioButtonElement implements IBasicElement {
	
	public TabElement(By cssSelector)
	/**
	 * @param cssSelector
	 *            - selector of Tab element's set
	 **/
	{
		this(cssSelector, By.cssSelector("li"));
	}
	
	public TabElement(By cssSelector, By inputChildsSelector) {
		/**
		 * @param cssSelector
		 *            - selector of Tab element's set
		 * @param inputChildsSelector
		 *            - selector of relative path from Tab element's set to basic input element
		 **/
		super(ElementType.TAB, cssSelector, inputChildsSelector, Arrays.asList("ui-tabs-active ui-state-active"));
	}
	
	public TabElement(By cssSelector, By inputChildsSelector, List<String> listSelectedAttributes) {
		/**
		 * @param cssSelector
		 *            - selector of Tab element's set
		 * @param inputChildsSelector
		 *            - selector of relative path from Tab element's set to basic input element
		 * @param listSelectedAttributes
		 *            - list of class name describing selected item
		 **/
		super(ElementType.TAB, cssSelector, inputChildsSelector, listSelectedAttributes);
	}
}
