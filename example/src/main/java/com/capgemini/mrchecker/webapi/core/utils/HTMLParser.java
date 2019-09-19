package com.capgemini.mrchecker.webapi.core.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

public final class HTMLParser {

	private HTMLParser() {
	}

	public static Document parse(String html) {
		return Jsoup.parse(html);
	}

	public static Elements getElements(String html, String tag) {
		return getElements(parse(html), tag);
	}

	public static Elements getElements(Document document, String tag) {
		return document.select(tag);
	}

	public static List<String> getAttributeValueFromElements(Elements elements, String attributeKey) {
		return elements.stream().map(el -> el.attr(attributeKey)).collect(Collectors.toList());
	}

	public static List<String> getTextFromElements(Elements elements) {
		return elements.stream().map(Element::text).collect(Collectors.toList());
	}
}