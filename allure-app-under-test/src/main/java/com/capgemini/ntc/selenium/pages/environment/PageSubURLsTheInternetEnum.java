package com.capgemini.ntc.selenium.pages.environment;

public enum PageSubURLsTheInternetEnum {
	
	BASIC_AUTH("basic_auth"),
	NEW_WINDOW("windows/new"),
	WINDOW("windows"),
	CHECKBOX("checkboxes");
	
	/*
	 * Sub urls are used as real locations in Bank test environment
	 */
	private String subURL;
	
	private PageSubURLsTheInternetEnum(String subURL) {
		this.subURL = subURL;
	};
	
	private PageSubURLsTheInternetEnum() {
		
	}
	
	@Override
	public String toString() {
		return getValue();
	}
	
	public String getValue() {
		return subURL;
	}
	
}