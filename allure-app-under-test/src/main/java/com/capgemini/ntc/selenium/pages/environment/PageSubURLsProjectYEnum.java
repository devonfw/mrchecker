package com.capgemini.ntc.selenium.pages.environment;

public enum PageSubURLsProjectYEnum {
	
	BASIC_AUTH("basic_auth"),
	NEW_WINDOW("windows/new"),
	WINDOW("windows"),
	CHECKBOX("checkboxes"),
	KEY_PRESS("key_presses"),
	TYPOS("typos"),
	NOTIFICATIONS("notification_message_rendered"),
	NESTED_FRAMES("nested_frames"),
	LARGE_DEEP_DOM("large"),
	IFRAME("iframe"),
	FLOATING_MENU("floating_menu");
	
	/*
	 * Sub urls are used as real locations in Bank test environment and for The Internet page
	 */
	private String subURL;
	
	PageSubURLsProjectYEnum(String subURL) {
		this.subURL = subURL;
	}
	
	PageSubURLsProjectYEnum() {
		
	}
	
	@Override
	public String toString() {
		return getValue();
	}
	
	public String getValue() {
		return subURL;
	}
	
}