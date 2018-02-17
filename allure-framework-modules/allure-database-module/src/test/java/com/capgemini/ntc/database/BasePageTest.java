package com.capgemini.ntc.database;

import org.junit.Test;

import com.capgemini.ntc.database.core.BasePage;

public class BasePageTest {
	
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
