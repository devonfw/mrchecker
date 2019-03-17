package com.capgemini.mrchecker.security.headers;

import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.capgemini.mrchecker.security.EnvironmentParam;
import com.capgemini.mrchecker.security.SecurityTest;
import com.capgemini.mrchecker.security.SubUrlEnum;
import com.capgemini.mrchecker.security.session.SessionEnum;

import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

/**
 * The test verifies, that the cache-control header is configured properly.
 * OWASP ASVS requirement V9.4: Verify that the application sets appropriate anti-caching
 * headers as per the risk of the application, such as the following:
 * Expires: Tue, 03 Jul 2001 06:00:00 GMT
 * Last-Modified: {now} GMT
 * Cache-Control: no-store, no-cache, must-revalidate, max-age=0
 * Cache-Control: post-check=0, pre-check=0
 * Pragma: no-cache
 * Purpose: Sensitive information must not be cached or stored locally in the browser.
 * Anyone with physical access to the machine could potentially extract the data even after
 * the session of a legitimate user is closed.
 *
 * @author Piotr Stankiewicz, Capgemini
 */
@RunWith(JUnitParamsRunner.class)
public class CacheControlTest extends SecurityTest {
	
	private Object[] addParameters() {
		String body = "{\"sort\":[],\"categories\":[]}";
		String contentType = "application/json";
		return new Object[][] {
						{ SessionEnum.WAITER, EnvironmentParam.SECURITY_SERVER_ORIGIN,
										SubUrlEnum.DISH_SEARCH, Method.POST, contentType, body, HttpStatus.SC_OK
						},
						{ SessionEnum.ANON, EnvironmentParam.SECURITY_SERVER_ORIGIN,
										SubUrlEnum.DISH_SEARCH, Method.POST, contentType, body, HttpStatus.SC_OK
						},
		};
	}
	
	@Test
	@Parameters(method = "addParameters")
	public void testHeader(SessionEnum session, EnvironmentParam origin, SubUrlEnum path, Method method, String contentType, String body, int statusCode) {
		RequestSpecification rs = getSessionManager()
				.initBuilder(session)
				.setBaseUri(origin.getValue())
				.setBasePath(path.getValue())
				.setBody(body)
				.addHeader("content-type", contentType)
				.build();
		given(rs)
				.when()
				.request(method)
				.then()
				.statusCode(statusCode)
				.header("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate")
				.header("Pragma", "no-cache")
				.header("Expires", "0");
	}
}
