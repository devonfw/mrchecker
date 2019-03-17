package com.capgemini.mrchecker.selenium.pages.environment;

public enum PageSubURLsProjectYEnum {
	
	BASIC_AUTH("basic_auth"),
	NEW_WINDOW("windows/new"),
	WINDOW("windows"),
	CHECKBOX("checkboxes"),
	CONTEXT_MENU("context_menu"),
	KEY_PRESS("key_presses"),
	DYNAMIC_CONTENT("dynamic_content"),
	HOVERS("hovers"),
	SORTABLE_DATA_TABLES("tables"),
	REDIRECT("redirector"),
	JAVASCRIPT_ALERTS("javascript_alerts"),
	CHALLENGING_DOM("challenging_dom"),
	STATUS_CODES("status_codes"),
	LOGIN("login"),
	ABTEST("abtest"),
	BROKEN_IMAGES("broken_images"),
	DROPDOWN("dropdown"),
	HORIZONTAL_SLIDER("horizontal_slider"),
	DOWNLOAD("download"),
	FORGOT_PASSWORD("forgot_password"),
	FORGOT_PASSWORD_EMAIL_SENT("email_sent"),
	EXIT_INTENT("exit_intent"),
	DYNAMIC_LOADING("dynamic_loading"),
	DISAPPEARING_ELEMENTS("disappearing_elements"),
	DRAG_AND_DROP("drag_and_drop"),
	DYNAMIC_CONTROLS("dynamic_controls"),
	UPLOAD("upload"),
	FLOATING_MENU("floating_menu"),
	FRAMES("frames"),
	GEOLOCATION("geolocation"),
	INFINITE_SCROLL("infinite_scroll"),
	JQUERY_UI("jqueryui/menu"),
	JAVASCRIPT_ERROR("javascript_error"),
	LARGE_AND_DEEP_DOM("large"),
	NESTED_FRAMES("nested_frames"),
	NOTIFICATION_MESSAGE("notification_message"),
	DOWNLOAD_SECURE("download_secure"),
	SHIFTING_CONTENT("shifting_content"),
	SLOW_RESOURCES("slow"),
	TYPOS("typos"),
	WYSIWYGEDITOR("tinymce");
	
	/*
	 * Sub URLs are used as real locations in test environment
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