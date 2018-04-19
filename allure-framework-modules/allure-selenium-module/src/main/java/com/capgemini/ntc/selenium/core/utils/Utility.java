package com.capgemini.ntc.selenium.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.test.core.logger.BFLogger;

/**
 * This class contains uncategorized utility functions. If more 2 or more functions can be put into a separate Utils
 * file please do it. Please check other Utlis classes before adding anything here.
 * 
 * @author
 */
public class Utility {
	
	static public enum SortOrder {
		ASCENDING(1),
		DESCENDING(-1);
		int param;
		
		SortOrder(int param) {
			this.param = param;
		}
	}
	
	/**
	 * Matches String with pattern
	 * 
	 * @param String
	 *            pattern to compile
	 * @param String
	 *            toCompare
	 * @return boolean representation of result of matching toCompare and compiled pattern
	 */
	public static boolean isStringMatchingPattern(String pattern, String toCompare) {
		return Pattern.matches(pattern, toCompare);
	}
	
	/**
	 * Searches for pattern in String
	 * 
	 * @param String
	 *            pattern to compile
	 * @param String
	 *            toCompare
	 * @return result of finding pattern in String toCompare
	 */
	public static boolean isStringContainingPattern(String pattern, String toCompare) {
		Pattern p = Pattern.compile(pattern);
		Matcher matcher = p.matcher(toCompare);
		return matcher.find();
	}
	
	/**
	 * Check if strings in first table are alike ordered than strings in second table.
	 * 
	 * @param namesToCheck
	 * @param namesToSearchIn
	 * @return true if namesToCheck are in alike order in namesToSearchIn. Second table don't have to contain all
	 *         strings from first table e.g. ["a", "c"], ["a", "b", "c"] -> true e.g. ["a", "c"], ["a", "b"] -> true
	 *         e.g. ["a", "b", "c"], ["a", "c", "b"] -> false
	 */
	public static boolean isInGivenOrder(String[] namesToCheck, String[] namesToSearchIn) {
		int[] indexAtSearchInTable = new int[namesToCheck.length];
		for (int i = 0; i < namesToCheck.length; i++) {
			boolean found = false;
			String nameToCheck = namesToCheck[i];
			BFLogger.logDebug("[isInGivenOrder()] Name to check: " + nameToCheck);
			for (int j = 0; j < namesToSearchIn.length; j++) {
				String nameToSearch = namesToSearchIn[j];
				if (nameToCheck.equalsIgnoreCase(nameToSearch)) {
					indexAtSearchInTable[i] = j;
					BFLogger.logDebug("[isInGivenOrder()] Found name: " + nameToSearch);
					found = true;
					break;
				}
			}
			// if group was not found assign -1 as it index in account selector;
			if (found) {
				continue;
			}
			indexAtSearchInTable[i] = -1;
			BFLogger.logDebug("[isInGivenOrder()] Not found name: " + nameToCheck);
		}
		int auxPrevIndex = -1;
		for (int index : indexAtSearchInTable) {
			if (index == -1) {
				// skip groups that were not found
				continue;
			}
			if (index < auxPrevIndex) {
				return false;
			}
			auxPrevIndex = index;
		}
		return true;
	}
	
	public static String convertValueColorFromRgbToHex(String colorAsString) {
		String[] numbers = colorAsString.replace("rgba(", "")
						.replace(")", "")
						.split(",");
		int number1 = Integer.parseInt(numbers[0]);
		numbers[1] = numbers[1].trim();
		int number2 = Integer.parseInt(numbers[1]);
		numbers[2] = numbers[2].trim();
		int number3 = Integer.parseInt(numbers[2]);
		String colorInHex = String.format("#%02x%02x%02x", number1, number2, number3);
		return colorInHex;
	}
	
	/**
	 * Returns CSS classes from a WebElement pointed by given selector
	 * 
	 * @return CSS classes from a WebElement
	 * @author
	 */
	public static String[] getCssClassesFromWebElement(By selector) {
		@SuppressWarnings("deprecation")
		WebElement messagesPopupWindow = BasePage.getDriver()
						.findElement(selector);
		String cssClasses = messagesPopupWindow.getAttribute("class");
		return cssClasses.split(" ");
	}
	
}
