package com.capgemini.mrchecker.webapi.httpbin;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

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
		
		System.out.println("Step 2 - Send GET to " + getCookiesPage.getEndpoint());
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
		
		System.out.println("Step 2 - Send GET to " + setCookiesPage.getEndpoint());
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
		
		System.out.println("Step 2 - Send GET to " + deleteCookiesPage.getEndpoint());
		Response response = deleteCookiesPage.deleteCookie("cookiesTwo", "2");
		
		System.out.println("Step 3 - Validate response status code. Expected was " + expectedStatusCode + ".");
		assertThat(response.statusCode(), is(expectedStatusCode));
	}
	
	@Test
	public void setTwoCookies() {
		System.out.println("Step 2 - Send GET to " + setCookiesPage.getEndpoint());
		setCookiesPage.setCookie("firstCookie", "1");
		setCookiesPage.setCookie("secondCookie", "2");
		
		Response response = getCookiesPage.getCookies();
		System.out.println(response.toString());
	}
	
	@Test
	public void setRandomCookies() {
	}
	
	@Test
	public void deleteTwoCookies() {
	}
	
	@Test
	public void deleteRandomCookies() {
	}
	
	// @Test
	// public void getCookiesTest() {
	// setCookiesPage.setCookie("first", "1");
	// Response response = getCookiesPage.getCookies();
	// BFLogger.logInfo(response.asString());
	//
	// setCookiesPage.setCookie("second", "2");
	// Response response2 = getCookiesPage.getCookies();
	// BFLogger.logInfo(response2.asString());
	//
	// deleteCookiesPage.deleteCookie("first", "1");
	//
	// Response response3 = getCookiesPage.getCookies();
	// BFLogger.logInfo(response3.asString());
	// assertThat(response.statusCode(), is(200));
	// }
	
	// @Test
	// public void setOneCookie() {
	// Response response = setCookiesPage.setCookie("first", "1");
	//
	// BFLogger.logInfo(response.asString());
	// assertThat(response.statusCode(), is(200));
	// }
	//
	// @Test
	// public void deleteCookie() {
	// Response response = deleteCookiesPage.deleteCookie("second", "1");
	// BFLogger.logInfo(response.asString());
	// assertThat(response.statusCode(), is(200));
	// }
	//
	// @Test
	// public void getCookiesTest2() {
	// Response response = getCookiesPage.getCookies();
	// BFLogger.logInfo(response.asString());
	// assertThat(response.statusCode(), is(200));
	// }
	
}
