package com.capgemini.mrchecker.webapi.pages.httbin.cookies;

import io.restassured.http.Cookie;

public class CookieSession {
	
	private static Cookie cookieSession = null;
	
	private CookieSession() {
	}
	
	public static Cookie getSession() {
		if (cookieSession == null) {
			cookieSession = createSession();
		}
		return cookieSession;
	}
	
	private static Cookie createSession() {
		return new Cookie.Builder("session_id", "112").setSecured(true)
				.setComment("session id cookie")
				.build();
	}
}
