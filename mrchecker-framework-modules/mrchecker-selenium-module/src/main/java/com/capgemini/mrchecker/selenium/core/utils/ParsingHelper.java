package com.capgemini.mrchecker.selenium.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;

public class ParsingHelper {
	
	private ParsingHelper() {
	}
	
	/**
	 * Finds and parses the WebElement's content as a signed decimal integer.
	 * 
	 * @param by
	 *            object with location of the WebElement to be parsed.
	 * @param returnZeroIfContentIsNotParsable
	 *            boolean flag: if true - returns zero if the content does not contain a parsable integer; if false -
	 *            throws NumberFormatException if the content does not contain a parsable integer
	 * @return int with parsed value.
	 * @exception NumberFormatException
	 *                if the content does not contain a parsable integer and the returnZeroIfContentIsNotParsable flag
	 *                is set to false.
	 * @author
	 */
	public static int findAndParseIntFromWebElement(By by, boolean returnZeroIfContentIsNotParsable) {
		WebElement element = BasePage.getDriver()
				.findElementDynamic(by);
		try {
			return parseInt(element.getText());
		} catch (NumberFormatException numberFormatException) {
			if (returnZeroIfContentIsNotParsable) {
				return 0;
			} else {
				throw numberFormatException;
			}
		}
		
	}
	
	/**
	 * Parses the string argument as a signed decimal integer.
	 * 
	 * @param stringToParse
	 *            String to be parsed.
	 * @return int with parsed value.
	 * @exception NumberFormatException
	 *                if the string does not contain a parsable integer.
	 * @author
	 */
	public static int parseInt(String stringToParse) {
		String stringToParseAfterFiltering = stringToParse.replaceAll("[^\\d-]|(\\d+-)", "");
		try {
			return Integer.parseInt(stringToParseAfterFiltering);
		} catch (NumberFormatException numberFormatException) {
			throw new NumberFormatException("For input string: \"" + stringToParse + "\"");
		}
	}
	
	/**
	 * Parses the first occurency of the substring (represented by given regex)
	 * 
	 * @param stringToParse
	 *            String to be parsed.
	 * @param regexToSearch
	 *            pattern of the substring, which should be parsed
	 * @return first occurency of the substring matching the pattern or empty String when there are no substrings
	 *         matching the pattern
	 * @author
	 */
	public static String parseSubstring(String stringToParse, String regexToSearch) {
		Pattern pattern = Pattern.compile(regexToSearch);
		Matcher matcher = pattern.matcher(stringToParse);
		if (matcher.find()) {
			return matcher.group(0);
		}
		return "";
	}
	
	/**
	 * Parses Option Position symbol by removing "-" sign and all digits
	 * 
	 * @param symbol
	 * @return alphabetical symbol
	 */
	public static String parseOptionSymbol(final String symbol) {
		String optionSymbol = symbol;
		if (optionSymbol.startsWith("-")) {
			optionSymbol = symbol.replace("-", "");
			optionSymbol = optionSymbol.split("\\d")[0];
		}
		return optionSymbol;
	}
}
