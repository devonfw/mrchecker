package com.capgemini.mrchecker.webapi.httpbin;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.webapi.BasePageWebApiTest;
import com.capgemini.mrchecker.webapi.pages.httbin.cookies.DeleteCookiesPage;
import com.capgemini.mrchecker.webapi.pages.httbin.cookies.GetCookiesPage;
import com.capgemini.mrchecker.webapi.pages.httbin.cookies.SetCookiesPage;

import io.restassured.response.Response;

public class CookiesTest extends BasePageWebApiTest {
	
	private GetCookiesPage		getCookiesPage		= new GetCookiesPage();
	private SetCookiesPage		setCookiesPage		= new SetCookiesPage();
	private DeleteCookiesPage	deleteCookiesPage	= new DeleteCookiesPage();
	
	@Test
	public void getCookiesTest() {
		setCookiesPage.setCookie("first", "1");
		Response response = getCookiesPage.getCookies();
		BFLogger.logInfo(response.asString());
		
		setCookiesPage.setCookie("second", "2");
		Response response2 = getCookiesPage.getCookies();
		BFLogger.logInfo(response2.asString());
		
		deleteCookiesPage.deleteCookie("first", "1");
		
		Response response3 = getCookiesPage.getCookies();
		BFLogger.logInfo(response3.asString());
		assertThat(response.statusCode(), is(200));
	}
	
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
