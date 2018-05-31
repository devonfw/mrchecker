package com.capgemini.mrchecker.example;

import org.junit.Test;

import com.capgemini.mrchecker.example.core.BasePage;

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
