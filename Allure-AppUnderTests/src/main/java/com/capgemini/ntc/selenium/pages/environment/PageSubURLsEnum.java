package com.capgemini.ntc.selenium.pages.environment;

import com.capgemini.ntc.selenium.core.Url;

public enum PageSubURLsEnum implements Url{


	REGISTRATION("registration/"),
	FRAMEANDWINDOWS("frames-and-windows/"),
	MAIN_PAGE(""),
	FAQ("content/apps/static/faqpopup/index.html"),
	AUTOMATIC_INVESTMENTS("ofaccounts/bfal"),
	LOGIN_PAGE,
	MAIN_PAGE_CLASSIC,
	ACTIVITY_TAB,
	FULL_VIEW_CONTROL,
	AUTOMATION_PRACTICE_FORM("automation-practice-form/"),
	TABS("tabs/"),
	TOOLTIP("tooltip/"),
	MENU("menu/");

	/*
	 * Sub urls are used as real locations in Bank test environment
	 */
	private String subURL;

	private PageSubURLsEnum(String subURL) {
		this.subURL = subURL;
	};

	private PageSubURLsEnum() {

	}


	@Override
	public String toString() {
		return getAddress();
	}

	@Override
	public String getAddress() {
		return subURL;
	}

	
}