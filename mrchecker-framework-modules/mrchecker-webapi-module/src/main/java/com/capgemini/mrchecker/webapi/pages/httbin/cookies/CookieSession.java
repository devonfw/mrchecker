package com.capgemini.mrchecker.webapi.pages.httbin.cookies;

import io.restassured.filter.cookie.CookieFilter;

public class CookieSession {
	
	private static CookieFilter cookieSession = null;
	
	private CookieSession() {
	}
	
	public static CookieFilter getSession() {
		if (cookieSession == null) {
			cookieSession = createSession();
		}
		return cookieSession;
	}
	
	public static void createNewSession() {
		cookieSession = createSession();
	}
	
	private static CookieFilter createSession() {
		return new CookieFilter();
	}
	
}
