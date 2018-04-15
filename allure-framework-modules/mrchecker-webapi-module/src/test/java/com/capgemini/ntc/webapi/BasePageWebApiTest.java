package com.capgemini.ntc.webapi;

import org.junit.Test;

import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.webapi.core.BasePageWebAPI;

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
		public String getMessage() {
			return null;
		}
		
		@Override
		public String getEndpoint() {
			return null;
		}
	}
	
}
