package com.capgemini.mrchecker.selenium.pages.environment;

public enum PageSubURLsProjectXEnum {
	
	REGISTRATION("registration/"),
	FRAMEANDWINDOWS("frames-and-windows"),
	MAIN_PAGE(""),
	FAQ("content/apps/static/faqpopup/index.html"),
	AUTOMATION_PRACTICE_FORM("automation-practice-form"),
	TABS("tabs/"),
	TOOLTIP("tooltip/"),
	MENU("menu/");
	
	/*
	 * Sub urls are used as real locations in Bank test environment
	 */
	private String subURL;
	
	private PageSubURLsProjectXEnum(String subURL) {
		this.subURL = subURL;
	};
	
	private PageSubURLsProjectXEnum() {
		
	}
	
	@Override
	public String toString() {
		return getValue();
	}
	
	public String getValue() {
		return subURL;
	}
	
}