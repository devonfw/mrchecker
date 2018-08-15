package com.capgemini.mrchecker.webapi.httpbin;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.webapi.BasePageWebApiTest;
import com.capgemini.mrchecker.webapi.pages.httbin.cookies.CookieSession;
import com.capgemini.mrchecker.webapi.pages.httbin.cookies.DeleteCookiesPage;
import com.capgemini.mrchecker.webapi.pages.httbin.cookies.GetCookiesPage;
import com.capgemini.mrchecker.webapi.pages.httbin.cookies.SetCookiesPage;

import io.restassured.http.Cookie;
import io.restassured.response.Response;

public class CookiesTest extends BasePageWebApiTest {
	
	Cookie cookie = CookieSession.getSession();
	
	private GetCookiesPage		getCookiesPage		= new GetCookiesPage(cookie);
	private SetCookiesPage		setCookiesPage		= new SetCookiesPage(cookie);
	private DeleteCookiesPage	deleteCookiesPage	= new DeleteCookiesPage(cookie);
	
	@Test
	public void getCookiesTest() {
		setCookiesPage.setCookie("first", "1");
		Response response = getCookiesPage.getCookies();
		BFLogger.logInfo(response.asString());
		assertThat(response.statusCode(), is(200));
	}
	
	// @Test
	// public void setOneCookie() {
	// BFLogger.logInfo("before = " + setCookiesPage.getEndpoint());
	// Response response = setCookiesPage.setCookie("first", "1");
	// BFLogger.logInfo("after = " + setCookiesPage.getEndpoint());
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
