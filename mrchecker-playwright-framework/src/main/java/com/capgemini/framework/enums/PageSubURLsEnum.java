package com.capgemini.framework.enums;

import java.util.Arrays;
import java.util.List;

public enum PageSubURLsEnum {
	NO_URL(""),
	LOGIN("https://google.com");

	
	private final String subURL;
	
	PageSubURLsEnum(String url) {
		this.subURL = url;
	}
	
	@Override
	public String toString() {
		return getValue();
	}
	
	public String getValue() {
		return subURL;
	}
	
	public static List<String> getValues() {
		return Arrays.stream(values())
				.map(PageSubURLsEnum::toString)
				.toList();
	}
}