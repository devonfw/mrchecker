package com.capgemini.ntc.webapi.core.utils;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
/*
 * This is extension for Matcher from hamcrest to use matches in assertThat construction:
 * assertThat(selenium.getTitle(), matches("Template T\d{3}"));
 */

public class RegexMatcher extends BaseMatcher<Object> {
	private final String regex;
	
	public RegexMatcher(String regex) {
		this.regex = regex;
	}
	
	public boolean matches(Object o) {
		return ((String) o).matches(regex);
	}
	
	public void describeTo(Description description) {
		description.appendText("matches regex=" + regex);
	}
	
	public static RegexMatcher matches(String regex) {
		return new RegexMatcher(regex);
	}
}
