package com.capgemini.ntc.selenium.core.enums;

import com.capgemini.ntc.test.core.base.environment.GetEnvironmentParam;

public enum PageSubURLsEnum implements SubUrl {
	
	WWW_FONT_URL {
		@Override
		public String subURL() {
			return GetEnvironmentParam.WWW_FONT_URL.getAddress();
		}
	},
	TOOLS_QA {
		@Override
		public String subURL() {
			return GetEnvironmentParam.TOOLS_QA.getAddress();
		}
	},
	
	WEB_SERVICE {
		@Override
		public String subURL() {
			return GetEnvironmentParam.WEB_SERVICE.getAddress();
		}
	},
	HEROKUAPP {
		@Override
		public String subURL() {
			return GetEnvironmentParam.HEROKUAPP.getAddress();
		}
	},
	
	REGISTRATION("registration/"),
	FRAMEANDWINDOWS("frames-and-windows/"),
	MAIN_PAGE(""),
	AUTOMATION_PRACTICE_FORM("automation-practice-form/"),
	TABS("tabs/"),
	TOOLTIP("tooltip/"),
	MENU("menu/"),
	CONTEXT_MENU("context_menu");
	
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
