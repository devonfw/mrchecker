package com.capgemini.ntc.selenium.pages.environment;

public enum PageSubURLsProjectYEnum {
	
	BASIC_AUTH("basic_auth"),
	NEW_WINDOW("windows/new"),
	WINDOW("windows"),
	CHECKBOX("checkboxes"),
	CONTEXT_MENU("context_menu"),
	KEY_PRESS("key_presses"),
	HOVERS("hovers"),
	CHALLENGING_DOM("challenging_dom"),
	STATUS_CODES("status_codes");
	
	/*
	 * Sub urls are used as real locations in Bank test environment
	 */
	private String subURL;
	
	private PageSubURLsProjectYEnum(String subURL) {
		this.subURL = subURL;
	};
	
	private PageSubURLsProjectYEnum() {
		
	}
	
	@Override
	public String toString() {
		return getValue();
	}
	
	public String getValue() {
		return subURL;
	}
	
}