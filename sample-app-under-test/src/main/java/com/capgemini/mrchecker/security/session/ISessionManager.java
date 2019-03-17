package com.capgemini.mrchecker.security.session;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Headers;

/**
 * Defines a set of methods to be provided by the session managing mechanisms.
 *
 * @author Marek Puchalski, Capgemini
 */
public interface ISessionManager {
	
	/**
	 * Initializes the RestAssured request builder with information about the
	 * session authorization headers.
	 *
	 * @param session
	 *            Identifies the session.
	 * @return Initialized builder.
	 */
	RequestSpecBuilder initBuilder(SessionEnum session);
	
	/**
	 * Initializes the builder without pointing to a particular session. Although every
	 * test could do it for itself, it makes sense to make this operation global in case
	 * changes to all system requests need to be made.
	 *
	 * @return Initialized builder.
	 */
	RequestSpecBuilder initBuilder();
	
	/**
	 * The most common case to authenticate requests is over the request headers. Some security
	 * tests will need direct access to the headers to manipulate the data for the test reasons.
	 * 
	 * @param session
	 *            Identifies the session.
	 * @return Session headers.
	 */
	Headers getAuthHeaders(SessionEnum session);
	
}