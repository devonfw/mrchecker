package com.capgemini.mrchecker.security.session;

/**
 * This enumeration defines various user sessions (based on the role names)
 * available in the application.
 * 
 * @author Marek Puchalski, Capgemini
 */
public enum SessionEnum {
	ANON, // Session of the anonymous user, so actually no session at all
	WAITER;
}
