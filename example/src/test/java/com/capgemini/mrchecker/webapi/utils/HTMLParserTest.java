package com.capgemini.mrchecker.webapi.core.utils;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HTMLParserTest {
	private final String SAMPLE_HTML = "<html><head><title>My title</title></head><body>Body content<div id=\"mydiv1\">Contents of a first div element</div><div id=\"mydiv2\">Contents of a second div element</div></body></html>";

	@Test
	public void testHTMLParser_parse_checkTitle() {
		Document doc = HTMLParser.parse(SAMPLE_HTML);
		assertNotNull(doc);
		assertEquals(doc.title(), "My title");
	}

	@Test
	public void testHTMLParser_getElements_html_checkTitle() {
		Elements elements = HTMLParser.getElements(SAMPLE_HTML, "title");
		assertEquals(elements.size(), 1);
		assertEquals(elements.first().ownText(), "My title");
	}

	@Test
	public void testHTMLParser_getElements_doc_checkTitle() {
		Document doc = HTMLParser.parse(SAMPLE_HTML);
		Elements elements = HTMLParser.getElements(doc, "title");
		assertEquals(elements.size(), 1);
		assertEquals(elements.first().ownText(), "My title");
	}

	@Test
	public void testHTMLParser_getElements_html_allDiv_text() {
		Elements elements = HTMLParser.getElements(SAMPLE_HTML, "div");
		List<String> texts = HTMLParser.getTextFromElements(elements);
		assertEquals(texts.size(), 2);
		assertEquals(texts.get(0), "Contents of a first div element");
		assertEquals(texts.get(1), "Contents of a second div element");
	}

	@Test
	public void testHTMLParser_getElements_html_allDiv_attributesId() {
		Elements elements = HTMLParser.getElements(SAMPLE_HTML, "div");
		List<String> texts = HTMLParser.getAttributeValueFromElements(elements, "id");
		assertEquals(texts.size(), 2);
		assertEquals(texts.get(0), "mydiv1");
		assertEquals(texts.get(1), "mydiv2");
	}
}
