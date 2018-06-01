package com.capgemini.mrchecker.security;

import org.junit.Test;

import com.capgemini.mrchecker.security.core.BasePage;

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
