package com.capgemini.ntc.selenium.core.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;

import com.capgemini.ntc.test.core.exceptions.BFInputDataException;

/**
 * This class extends apache StringUtils and contains utility methods related to string operations.
 * 
 * @author
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
	
	private StringUtils() {
	}
	
	private static final Pattern pattern = Pattern.compile(".*\\(([^)]+)\\).*");
	
	/**
	 * Checks if texts matches regex
	 * 
	 * @param textToMatch
	 * @param regex
	 * @return True if text matches regex, false otherwise
	 * @author
	 */
	public static boolean isTextMatchesRegex(String textToMatch, String regex) {
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(textToMatch)
				.find();
	}
	
	/**
	 * Searches for occurrence of first regex group in textToMatch
	 * 
	 * @param textToMatch
	 * @param regex
	 *            to find
	 * @return first found match or empty string
	 */
	public static String findSubstring(String textToMatch, String regex) {
		return findSubstring(textToMatch, regex, 1);
	}
	
	/**
	 * Searches for occurrence of regex in textToMatch
	 * 
	 * @param textToMatch
	 * @param regex
	 *            to find
	 * @param groupNumber
	 *            number of group to return <b>counting from 1</b>
	 * @return first found match or empty string
	 */
	public static String findSubstring(String textToMatch, String regex, int groupNumber) {
		Pattern pattern = Pattern.compile(regex);
		Matcher patternMatcher = pattern.matcher(textToMatch);
		if (patternMatcher.find()) {
			int groupCount = patternMatcher.groupCount();
			if (groupNumber > groupCount) {
				throw new BFInputDataException("Unable to return group number " + groupNumber + ". Only " + groupCount
						+ " groups were found for regex \"" + regex + "\" in text \"" + textToMatch + "\".");
			}
			return patternMatcher.group(groupNumber);
		}
		return "";
	}
	
	/**
	 * Searches for occurrences of regex in textToMatch
	 * 
	 * @param textToMatch
	 * @param regex
	 *            to find
	 * @param groupNumber
	 *            number of group to return <b>counting from 1</b>
	 * @return all found matches or empty list
	 */
	public static List<String> findSubstrings(String textToMatch, String regex, int groupNumber) {
		Pattern pattern = Pattern.compile(regex);
		Matcher patternMatcher = pattern.matcher(textToMatch);
		List<String> toReturn = new ArrayList<String>();
		while (patternMatcher.find()) {
			int groupCount = patternMatcher.groupCount();
			if (groupNumber > groupCount) {
				throw new BFInputDataException("Unable to return group number " + groupNumber + ". Only " + groupCount
						+ " groups were found for regex \"" + regex + "\" in text \"" + textToMatch + "\".");
			}
			toReturn.add(patternMatcher.group(groupNumber));
		}
		return toReturn;
	}
	
	/**
	 * @return true if font in is bold.
	 */
	public static boolean isFontBold(WebElement text) {
		return text.getCssValue("font-weight")
				.contains("bold");
	}
	
	/**
	 * checks if there is a text in brackets "(xxx)" at the end of String and cuts it with trailing whitespaces
	 * 
	 * @param string
	 * @return
	 * @author
	 */
	public static String removeTrailingTextInBracketIfPresent(String string) {
		if (string.matches(".*\\s*\\(.*\\)$"))
			return string.replaceAll("\\s*\\(.*\\)$", "");
		return string;
	}
}
