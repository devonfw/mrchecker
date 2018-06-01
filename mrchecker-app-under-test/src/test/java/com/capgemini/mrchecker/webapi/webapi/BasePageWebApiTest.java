package com.capgemini.mrchecker.webapi;

import org.junit.Test;

import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.webapi.core.BasePageWebAPI;

public class BasePageWebApiTest extends BaseTest {
	
	@Override
	public void setUp() {
		
	}
	
	@Override
	public void tearDown() {
		
	}
	
	@Test
	public void test() {
		MyPage myPage = new MyPage();
		
		myPage.myMethod();
	}
	
	private static class MyPage extends BasePageWebAPI {
		
		public String myMethod() {
			return "Welcome";
		}
		
		@Override
		public String getEndpoint() {
			return null;
		}
	}
	
}
