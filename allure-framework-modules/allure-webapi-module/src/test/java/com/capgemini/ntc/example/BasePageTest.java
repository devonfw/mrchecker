package com.capgemini.ntc.example;

import org.junit.Test;

import com.capgemini.ntc.example.core.BasePage;
import com.capgemini.ntc.test.core.BaseTest;

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
