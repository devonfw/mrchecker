
package com.capgemini.ntc.selenium;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class BasePageTest extends BaseTest {
	
	@Override
	public void setUp() {
		// TASK Auto-generated method stub
		
	}
	
	@Override
	public void tearDown() {
		// TASK Auto-generated method stub
		
	}
	
	@Test
	public void testGetDriver() {
		TestPage testPage = new TestPage();
		assertEquals("", "Google_WrongPage", testPage.pageTitle());
		
	}
	
	
	private class TestPage extends BasePage {
		
		@Override
		public boolean isLoaded() {
			// TASK Auto-generated method stub
			return false;
		}

		@Override
		public void load() {
			getDriver().get("http://google.com");
			
		}

		@Override
		public String pageTitle() {
			return getDriver().getTitle();
		}
	}

	
	
	
}
