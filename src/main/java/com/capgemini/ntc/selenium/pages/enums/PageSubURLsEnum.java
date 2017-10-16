package com.capgemini.ntc.selenium.pages.enums;

import com.example.core.enums.SubUrl;

public enum PageSubURLsEnum implements SubUrl {

	WWW_FONT_URL {
		@Override
		public String subURL() {
			return ServicesURLsEnum.WWW_FONT_URL.getAddress();
		}
	},
	TOOLS_QA {
		@Override
		public String subURL() {
			return ServicesURLsEnum.TOOLS_QA.getAddress();
		}
	},
	
	WEB_SERVICE {
		@Override
		public String subURL() {
			return ServicesURLsEnum.WEB_SERVICE.getAddress();
		}
	},
	
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
	public String subURL() {
		return subURL;
	}

	@Override
	public String toString() {
		return subURL();
	}
}