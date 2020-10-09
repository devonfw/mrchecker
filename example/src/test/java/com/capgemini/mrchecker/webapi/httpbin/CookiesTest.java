package com.capgemini.mrchecker.webapi.httpbin;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsWebApi;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.test.core.utils.PageFactory;
import com.capgemini.mrchecker.webapi.pages.httbin.cookies.CookieSession;
import com.capgemini.mrchecker.webapi.pages.httbin.cookies.DeleteCookiesPage;
import com.capgemini.mrchecker.webapi.pages.httbin.cookies.GetCookiesPage;
import com.capgemini.mrchecker.webapi.pages.httbin.cookies.SetCookiesPage;

import io.restassured.response.Response;

@TestsWebApi
public class CookiesTest extends BaseTest {
	
	private static GetCookiesPage		getCookiesPage;
	private static SetCookiesPage		setCookiesPage;
	private static DeleteCookiesPage	deleteCookiesPage;
	
	@BeforeAll
	public static void setUpClass() {
		getCookiesPage = PageFactory.getPageInstance(GetCookiesPage.class);
		setCookiesPage = PageFactory.getPageInstance(SetCookiesPage.class);
		deleteCookiesPage = PageFactory.getPageInstance(DeleteCookiesPage.class);
	}
	
	@BeforeEach
	public void testSetUp() {
		BFLogger.logInfo("Step 1 - Create new cookie session");
		CookieSession.createNewSession();
		BFLogger.logInfo("Created session id: " + CookieSession.getSession()
				.hashCode());
	}
	
	@Test
	public void getCookiesTestStatusCode() {
		int expectedStatusCode = 200;
		
		BFLogger.logInfo("Step 2 - GET Cookies. Send GET to " + getCookiesPage.getEndpoint());
		Response response = getCookiesPage.getCookies();
		BFLogger.logInfo("Step 3 - Validate response status code. Expected was " + expectedStatusCode + ".");
		assertThat(response.statusCode(), is(expectedStatusCode));
	}
	
	@Test
	public void setCookiesTestStatusCode() {
		// After set some cookie httpbin.org trying to redirect for /cookies endpoint.
		// To keep same cookie session redirect must be turned off.
		// That's why 302(REDIRECT) is expected.
		int expectedStatusCode = 302;
		
		BFLogger.logInfo("Step 2 - SET Cookies. Send GET to " + setCookiesPage.getEndpoint());
		Response response = setCookiesPage.setCookie("cookiesOne", "1");
		BFLogger.logInfo("Step 3 - Validate response status code. Expected was " + expectedStatusCode + ".");
		assertThat(response.statusCode(), is(expectedStatusCode));
	}
	
	@Test
	public void deleteCookiesTestStatusCode() {
		// After delete some cookie httpbin.org trying to redirect for /cookies endpoint.
		// To keep same cookie session redirect must be turned off.
		// That's why 302(REDIRECT) is expected.
		int expectedStatusCode = 302;
		
		BFLogger.logInfo("Step 2 - DELETE Cookies. Send GET to " + deleteCookiesPage.getEndpoint());
		Response response = deleteCookiesPage.deleteCookie("cookiesTwo", "2");
		
		BFLogger.logInfo("Step 3 - Validate response status code. Expected was " + expectedStatusCode + ".");
		assertThat(response.statusCode(), is(expectedStatusCode));
	}
	
	@Test
	public void setTwoCookies() {
		String firstCookieKey = "firstCookie";
		String firstCookieValue = "1";
		
		String secondCookieKey = "secondCookie";
		String secondCookieValue = "2";
		
		int expectedSizeOfCookiesResponseMap = 2;
		
		BFLogger.logInfo("Step 2 - SET Cookies. Send GET to " + setCookiesPage.getEndpoint());
		setCookiesPage.setCookie(firstCookieKey, firstCookieValue);
		setCookiesPage.setCookie(secondCookieKey, secondCookieValue);
		
		BFLogger.logInfo("Step 3 - GET Cookies. Send GET to " + getCookiesPage.getEndpoint());
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
		
		BFLogger.logInfo("Step 2 - SET Cookies. Send GET to " + setCookiesPage.getEndpoint());
		setCookiesPage.setCookie(firstCookieKey, firstCookieValue);
		
		BFLogger.logInfo("Step 3 - GET Cookies. Send GET to " + getCookiesPage.getEndpoint());
		Map<String, Object> cookies = getCookiesPage.getCookies()
				.jsonPath()
				.getMap("cookies");
		
		BFLogger.logInfo("Step 4 - Validate 'cookies' response");
		assertThat(cookies.size(), equalTo(expectedSizeOfCookiesResponseMap));
		assertTrue(cookies.keySet()
				.contains(firstCookieKey));
		assertTrue(cookies.get(firstCookieKey)
				.equals(firstCookieValue));
		
		BFLogger.logInfo("Step 5 - DELETE Cookies. Send GET to " + deleteCookiesPage.getEndpoint());
		deleteCookiesPage.deleteCookie(firstCookieKey, firstCookieValue);
		
		BFLogger.logInfo("Step 6 - GET Cookies. Send GET to " + getCookiesPage.getEndpoint());
		Map<String, Object> cookiesAfterDelete = getCookiesPage.getCookies()
				.jsonPath()
				.getMap("cookies");
		
		BFLogger.logInfo("Step 7 - Validate 'cookies' response after deletion");
		// httpbin.org/delete remove only value from provided cookie.
		// So expected value after delete is empty String.
		assertThat(cookies.size(), equalTo(expectedSizeOfCookiesResponseMap));
		assertTrue(cookiesAfterDelete.keySet()
				.contains(firstCookieKey));
		assertEquals(expectedValueAfterDelete, cookiesAfterDelete.get(firstCookieKey));
	}
}
