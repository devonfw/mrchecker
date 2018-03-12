package com.capgemini.ntc.webapi;

import org.junit.Test;

import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.webapi.core.BasePage;

public class BasePageTest extends BaseTest {
	
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
	
	private static class MyPage extends BasePage {
		
		public String myMethod() {
			return "Welcome";
		}
	}
	
}
