package com.capgemini.mrchecker.selenium.core.utils;

import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.enums.ColorsEnum;

public class ColorsUtils {
	
	private ColorsUtils() {
	}
	
	/**
	 * @param element
	 *            - WebElement
	 * @param color
	 *            from ColorsEnum
	 * @return True if element text is displayed in black, false otherwise
	 * @author
	 */
	public static boolean isElementTextInGivenColor(WebElement element, ColorsEnum color) {
		String elementColor = element.getCssValue("color");
		if (elementColor == null || !elementColor.contains(color.toString())) {
			return false;
		}
		return true;
	}
	
	/**
	 * @param element
	 *            - WebElement
	 * @param validator
	 *            - IColorValidator
	 * @return True if element has correct color coresponding to IColorValidator, false otherwise
	 * @author
	 */
	public static boolean isColorCorrectForValue(WebElement element, IColorValidator<WebElement> validator) {
		return validator.isValid(element);
	}
	
}