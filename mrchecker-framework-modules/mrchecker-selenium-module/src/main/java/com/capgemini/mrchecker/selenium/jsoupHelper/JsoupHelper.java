/**
 * The MIT License Copyright © 2009 - 2013 Jonathan Hedley (jonathan@hedley.net)
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions: The above copyright
 * notice and this permission notice shall be included in all copies or substantial portions of the Software.
 */
package com.capgemini.mrchecker.selenium.jsoupHelper;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.exceptions.BFElementNotFoundException;

/**
 * This class should be used when there are many WebElements to search through, like table with hundreds of rows. It
 * speeds up searching for given values or WebElements a lot.
 * 
 * @author
 */
public class JsoupHelper {
	
	private JsoupHelper() {
	}
	
	/**
	 * Returns texts from <b>all (both visible and invisible)</b> WebElements specified by {@code toSearch selector} and
	 * all of its <b>children</b>. <br>
	 * <u>NOTE: Text returned by this method may differ from this returned by Selenium's {@code getText()} method.</u>
	 * 
	 * @param searchArea
	 *            narrows searching to specified WebElement
	 * @param toSearch
	 *            selector of WebElements with desired text
	 * @return texts from WebElements that match the selector and are inside {@code searchArea} or empty list
	 */
	public static List<String> findTexts(WebElement searchArea, By toSearch) {
		Document doc = initDocument(searchArea);
		return getTexts(toSearch, doc);
	}
	
	/**
	 * Returns texts from <b>all (both visible and invisible)</b> WebElements specified by {@code toSearch selector} and
	 * all of its <b>children</b> <br>
	 * <u>NOTE: Text returned by this method may differ from this returned by Selenium's {@code getText()} method.</u>
	 * 
	 * @param toSearch
	 *            selector of WebElements with desired text
	 * @return texts from all WebElements that match the selector or empty list
	 */
	public static List<String> findTexts(By toSearch) {
		Document doc = initDocument();
		return getTexts(toSearch, doc);
	}
	
	/**
	 * Returns texts <b>directly</b> from <b>all (both visible and invisible)</b> WebElements specified by
	 * {@code toSearch selector}. <br>
	 * <u>NOTE: Text returned by this method may differ from this returned by Selenium's {@code getText()} method.</u>
	 * 
	 * @param searchArea
	 *            narrows searching to specified WebElement
	 * @param toSearch
	 *            selector of WebElements with desired text
	 * @return texts from WebElements that match the selector and are inside {@code searchArea} or empty list
	 */
	public static List<String> findOwnTexts(WebElement searchArea, By toSearch) {
		Document doc = initDocument(searchArea);
		return getOwnTexts(toSearch, doc);
	}
	
	/**
	 * Returns texts <b>directly</b> from <b>all (both visible and invisible)</b> WebElements specified by
	 * {@code toSearch selector}. <br>
	 * <u>NOTE: Text returned by this method may differ from this returned by Selenium's {@code getText()} method.</u>
	 * 
	 * @param toSearch
	 *            selector of WebElements with desired text
	 * @return texts from all WebElements that match the selector or empty list
	 */
	public static List<String> findOwnTexts(By toSearch) {
		Document doc = initDocument();
		return getOwnTexts(toSearch, doc);
	}
	
	/**
	 * Returns {@code attribute} from <b>all (both visible and invisible)</b> WebElements specified by
	 * {@code toSearch selector}
	 * 
	 * @param searchArea
	 *            narrows searching to specified WebElement
	 * @param toSearch
	 *            selector of WebElements with desired attribute
	 * @param attribute
	 *            e.g. "class", "style"
	 * @return attributes from WebElements that match the selector and are inside {@code searchArea} or null when given
	 *         attribute not found
	 */
	public static List<String> findAttributes(WebElement searchArea, By toSearch, String attribute) {
		Document doc = initDocument(searchArea);
		return getAttributes(toSearch, attribute, doc);
	}
	
	/**
	 * Tries to find text within given web element. Note: To provide reliable information, passed web element should
	 * contain as small as possible area of page.
	 * 
	 * @param searchArea
	 *            - part of page as a WebElement
	 * @param textToFind
	 *            - text to find
	 * @return true when given text was found (<b>whether it was visible or not</b>); false otherwise
	 */
	public static boolean isContainText(WebElement searchArea, String textToFind) {
		Document doc = initDocument(searchArea);
		Element body = doc.body();
		Elements elements = body.getAllElements();
		for (Element element : elements) {
			String text = element.text();
			if (text.contains(textToFind)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns {@code attribute} from <b>all (both visible and invisible)</b> WebElements specified by
	 * {@code toSearch selector}
	 * 
	 * @param toSearch
	 *            selector of WebElements with desired attribute
	 * @param attribute
	 *            e.g. "class", "style"
	 * @return attributes from WebElements that match the selector or null when given attribute not found
	 */
	public static List<String> findAttributes(By toSearch, String attribute) {
		Document doc = initDocument();
		return getAttributes(toSearch, attribute, doc);
	}
	
	/**
	 * Tries to retrieve full CSS selectors for given text found in elements localized by By parameter
	 * 
	 * @param elementToReturn
	 *            - selector
	 * @param valueToCheck
	 *            - desired text to search for
	 * @return String list with found selectors
	 */
	public static List<String> getSelectors(By elementToReturn, String valueToCheck) {
		By elementToCheck = By.cssSelector("*");
		return getSelectors(elementToReturn, elementToCheck, valueToCheck);
	}
	
	/**
	 * Returns selectors to WebElements specified by {@code elementToReturn}, with {@code text} in WebElement specified
	 * by {@code elementToCheck}. <br>
	 * Example: this method can return selectors to rows from table, which contain desired value in certain column
	 * 
	 * @param elementToReturn
	 *            common selector, that is same for many WebElements
	 * @param elementToCheck
	 *            element, where text that would be checked can be found
	 * @param valueToCheck
	 * @return list of selectors to WebElements that meet the requirements, or empty list if none found
	 */
	public static List<String> getSelectors(By elementToReturn, By elementToCheck, String valueToCheck) {
		List<String> result = new ArrayList<String>();
		Document doc = initDocument();
		String selector = createStringSelector(elementToReturn);
		
		Elements rowListE = doc.body()
						.select(selector);
		for (Element row : rowListE) {
			String selectorOfElementTocheck = createStringSelector(elementToCheck);
			String containsQuery = valueToCheck == null || valueToCheck.isEmpty()
							? ""
							: ":contains(" + valueToCheck + ")";
			Elements returnCandidates = row.select(selectorOfElementTocheck + containsQuery);
			if (!returnCandidates.isEmpty())
				result.add(row.cssSelector());
		}
		return result;
	}
	
	/**
	 * Returns WebElements specified by {@code elementToReturn}, with {@code text} in WebElement specified by
	 * {@code elementToCheck}. <br>
	 * Example: this method can return rows from table, which contain desired value in certain column
	 * 
	 * @param elementToReturn
	 *            common selector, that is same for many WebElements
	 * @param elementToCheck
	 *            element, where text that would be checked can be found
	 * @param text
	 *            , which desired elements contains
	 * @return list of {@link WebElement} that meet the requirements
	 * @throws {@link
	 *             BFElementNotFoundException} when no matching WebElements found
	 */
	public static List<WebElement> findElements(By elementToReturn, By elementToCheck, String text) {
		List<String> selectors = getSelectors(elementToReturn, elementToCheck, text);
		return getElements(text, selectors);
	}
	
	/**
	 * Returns WebElements specified by {@code elementToReturn}, with desired {@code text}. <br>
	 * Example: this method can return rows from table, which contain desired value anywhere in row
	 * 
	 * @param elementToReturn
	 *            common selector, that is same for many WebElements
	 * @param text
	 *            which desired elements contains
	 * @return list of {@link WebElement} that meet the requirements
	 * @throws {@link
	 *             BFElementNotFoundException} when no matching WebElements found
	 */
	public static List<WebElement> findElements(By elementToReturn, String text) {
		List<String> selectors = getSelectors(elementToReturn, text);
		return getElements(text, selectors);
	}
	
	private static List<String> getTexts(By toSearch, Document doc) {
		String selector = createStringSelector(toSearch);
		Element body = doc.body();
		Elements elements = body.select(selector);
		return getTextsFromElements(elements);
	}
	
	private static List<String> getTextsFromElements(Elements elements) {
		List<String> result = new ArrayList<String>();
		for (Element element : elements) {
			String text = element.text();
			result.add(text);
		}
		return result;
	}
	
	private static List<String> getOwnTexts(By toSearch, Document doc) {
		String selector = createStringSelector(toSearch);
		List<String> result = new ArrayList<String>();
		Element body = doc.body();
		Elements elements = body.select(selector);
		for (Element element : elements) {
			result.add(element.ownText());
		}
		return result;
	}
	
	private static List<WebElement> getElements(String text, List<String> selectors) {
		if (selectors.isEmpty()) {
			throw new BFElementNotFoundException("Element with text [" + text + "] not found.");
		}
		List<WebElement> elementsToReturn = new ArrayList<WebElement>();
		for (String selector : selectors) {
			By elementSelector = By.cssSelector(selector);
			@SuppressWarnings("deprecation")
			WebElement foundElement = BasePage.getDriver()
							.findElement(elementSelector);
			elementsToReturn.add(foundElement);
		}
		return elementsToReturn;
	}
	
	private static List<String> getAttributes(By toSearch, String attribute, Document doc) {
		List<String> result = new ArrayList<String>();
		String selector = createStringSelector(toSearch);
		Element body = doc.body();
		Elements rowListE = body.select(selector);
		for (Element row : rowListE) {
			String attributeResult = row.attr(attribute);
			if (!attributeResult.equals(""))
				result.add(row.attr(attribute));
			else
				result.add(null);
		}
		return result;
	}
	
	private static Document initDocument(WebElement searchArea) {
		String innerHtml = searchArea.getAttribute("innerHTML");
		if (isConversionToTableRequired(innerHtml)) {
			innerHtml = convertToTable(innerHtml);
		} else if (isConversionToListRequired(innerHtml)) {
			innerHtml = convertToList(innerHtml);
		}
		Document doc = Jsoup.parse("<html><body>" + innerHtml + "</body></html>");
		return doc;
	}
	
	private static boolean isConversionToTableRequired(String innerHtml) {
		String[] tableIdentifiers = { "<table", "<thead", "<tbody", "<tr", "<td" };
		if (innerHtml.contains(tableIdentifiers[0])) {
			return false;
		} else {
			for (int i = 1; i < tableIdentifiers.length; i++) {
				if (innerHtml.contains(tableIdentifiers[i]))
					return true;
			}
		}
		return false;
	}
	
	private static String convertToTable(String innerHtml) {
		innerHtml = "<table>" + innerHtml + "</table>";
		return innerHtml;
	}
	
	private static boolean isConversionToListRequired(String innerHtml) {
		if (innerHtml.contains("<ul") && innerHtml.contains("<ol")) {
			return false;
		} else {
			if (innerHtml.contains("<li"))
				return true;
		}
		return false;
	}
	
	private static String convertToList(String innerHtml) {
		innerHtml = "<ul>" + innerHtml + "</ul>";
		return innerHtml;
	}
	
	private static Document initDocument() {
		Document doc = Jsoup.parse(BasePage.getDriver()
						.getPageSource());
		return doc;
	}
	
	private static String createStringSelector(By from) {
		String selector = from.toString();
		int substringBegin = from.toString()
						.indexOf(":") + 2;
		selector = selector.substring(substringBegin);
		selector = removeQuotes(selector);
		return selector;
	}
	
	private static String removeQuotes(String selector) {
		String openingQuote = "=\\s*'";
		String closingQuote = "'\\s*\\]";
		selector = selector.replaceAll(openingQuote, "=");
		selector = selector.replaceAll(closingQuote, "]");
		return selector;
	}
	
}