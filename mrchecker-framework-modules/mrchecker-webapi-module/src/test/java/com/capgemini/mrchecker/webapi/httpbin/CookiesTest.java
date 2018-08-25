package com.capgemini.mrchecker.webapi.httpbin;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.webapi.BasePageWebApiTest;
import com.capgemini.mrchecker.webapi.pages.httbin.cookies.CookieSession;
import com.capgemini.mrchecker.webapi.pages.httbin.cookies.DeleteCookiesPage;
import com.capgemini.mrchecker.webapi.pages.httbin.cookies.GetCookiesPage;
import com.capgemini.mrchecker.webapi.pages.httbin.cookies.SetCookiesPage;

import io.restassured.response.Response;

public class CookiesTest extends BasePageWebApiTest {
	
	private GetCookiesPage		getCookiesPage		= new GetCookiesPage();
	private SetCookiesPage		setCookiesPage		= new SetCookiesPage();
	private DeleteCookiesPage	deleteCookiesPage	= new DeleteCookiesPage();
	
	@Before
	public void testSetUp() {
		System.out.println("Step 1 - Create new cookie session");
		CookieSession.createNewSession();
		System.out.println("Created session id: " + CookieSession.getSession()
				.hashCode());
	}
	
	@Test
	public void getCookiesTestStatusCode() {
		int expectedStatusCode = 200;
		
		System.out.println("Step 2 - GET Cookies. Send GET to " + getCookiesPage.getEndpoint());
		Response response = getCookiesPage.getCookies();
		System.out.println("Step 3 - Validate response status code. Expected was " + expectedStatusCode + ".");
		assertThat(response.statusCode(), is(expectedStatusCode));
	}
	
	@Test
	public void setCookiesTestStatusCode() {
		// After set some cookie httpbin.org trying to redirect for /cookies endpoint.
		// To keep same cookie session redirect must be turned off.
		// That's why 302(REDIRECT) is expected.
		int expectedStatusCode = 302;
		
		System.out.println("Step 2 - SET Cookies. Send GET to " + setCookiesPage.getEndpoint());
		Response response = setCookiesPage.setCookie("cookiesOne", "1");
		System.out.println("Step 3 - Validate response status code. Expected was " + expectedStatusCode + ".");
		assertThat(response.statusCode(), is(expectedStatusCode));
	}
	
	@Test
	public void deleteCookiesTestStatusCode() {
		// After delete some cookie httpbin.org trying to redirect for /cookies endpoint.
		// To keep same cookie session redirect must be turned off.
		// That's why 302(REDIRECT) is expected.
		int expectedStatusCode = 302;
		
		System.out.println("Step 2 - DELETE Cookies. Send GET to " + deleteCookiesPage.getEndpoint());
		Response response = deleteCookiesPage.deleteCookie("cookiesTwo", "2");
		
		System.out.println("Step 3 - Validate response status code. Expected was " + expectedStatusCode + ".");
		assertThat(response.statusCode(), is(expectedStatusCode));
	}
	
	@Test
	public void setTwoCookies() {
		String firstCookieKey = "firstCookie";
		String firstCookieValue = "1";
		
		String secondCookieKey = "secondCookie";
		String secondCookieValue = "2";
		
		int expectedSizeOfCookiesResponseMap = 2;
		
		System.out.println("Step 2 - SET Cookies. Send GET to " + setCookiesPage.getEndpoint());
		setCookiesPage.setCookie(firstCookieKey, firstCookieValue);
		setCookiesPage.setCookie(secondCookieKey, secondCookieValue);
		
		System.out.println("Step 3 - GET Cookies. Send GET to " + getCookiesPage.getEndpoint());
		Map<String, Object> cookies = getCookiesPage.getCookies()
				.jsonPath()
				.getMap("cookies");
		
		BFLogger.logInfo("Step 4 - Validate 'cookies' response");
		assertThat(cookies.size(), equalTo(expectedSizeOfCookiesResponseMap));
		assertTrue(cookies.keySet()
				.contains(firstCookieKey));
		assertEquals(firstCookieValue, cookies.get(firstCookieKey));
		assertTrue(cookies.keySet()
				.contains(secondCookieKey));
		assertEquals(secondCookieValue, cookies.get(secondCookieKey));
	}
	
	@Test
	public void deleteCookie() {
		String firstCookieKey = "firstCookie";
		String firstCookieValue = "1";
		
		int expectedSizeOfCookiesResponseMap = 1;
		String expectedValueAfterDelete = "";
		
		System.out.println("Step 2 - SET Cookies. Send GET to " + setCookiesPage.getEndpoint());
		setCookiesPage.setCookie(firstCookieKey, firstCookieValue);
		
		System.out.println("Step 3 - GET Cookies. Send GET to " + getCookiesPage.getEndpoint());
		Map<String, Object> cookies = getCookiesPage.getCookies()
				.jsonPath()
				.getMap("cookies");
		
		BFLogger.logInfo("Step 4 - Validate 'cookies' response");
		assertThat(cookies.size(), equalTo(expectedSizeOfCookiesResponseMap));
		assertTrue(cookies.keySet()
				.contains(firstCookieKey));
		assertTrue(cookies.get(firstCookieKey)
				.equals(firstCookieValue));
		
		System.out.println("Step 5 - DELETE Cookies. Send GET to " + deleteCookiesPage.getEndpoint());
		deleteCookiesPage.deleteCookie(firstCookieKey, firstCookieValue);
		
		System.out.println("Step 6 - GET Cookies. Send GET to " + getCookiesPage.getEndpoint());
		Map<String, Object> cookiesAfterDelete = getCookiesPage.getCookies()
				.jsonPath()
				.getMap("cookies");
		
		// httpbin.org/delete remove only value from provided cookie.
		// So expected value after delete is empty String.
		System.out.println(cookiesAfterDelete);
		assertThat(cookies.size(), equalTo(expectedSizeOfCookiesResponseMap));
		assertTrue(cookiesAfterDelete.keySet()
				.contains(firstCookieKey));
		assertEquals(expectedValueAfterDelete, cookiesAfterDelete.get(firstCookieKey));
	}
}
