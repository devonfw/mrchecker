package com.capgemini.ntc.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.capgemini.ntc.selenium.pages.projectY.FloatingMenuPage;
import com.capgemini.ntc.test.core.BaseTest;

public class FloatingMenuTest extends BaseTest {
	
	private final static FloatingMenuPage floatingMenuPage = new FloatingMenuPage();
	
	@Override
	public void setUp() {
		floatingMenuPage.load();
	}
	
	@Test
	public void testVisibiltyWhileScrolling() {
		assertTrue(floatingMenuPage.isMenuDisplayed());
		int scrollPosition = 0;
		int singleScrollVal = 250;
		while (scrollPosition < floatingMenuPage.getPageHeight()) {
			floatingMenuPage.scrollPageDown(singleScrollVal);
			assertTrue(floatingMenuPage.isMenuDisplayed());
			scrollPosition += singleScrollVal;
		}
		floatingMenuPage.scrollPageUp(floatingMenuPage.getPageHeight());
	}
	
	@Test
	public void testHomeLink() {
		floatingMenuPage.clickHomeLink();
		assertEquals("http://the-internet.herokuapp.com/floating_menu#home", FloatingMenuPage.getDriver()
				.getCurrentUrl());
	}
	
	@Test
	public void testNewsLink() {
		floatingMenuPage.clickNewsLink();
		assertEquals("http://the-internet.herokuapp.com/floating_menu#news", FloatingMenuPage.getDriver()
				.getCurrentUrl());
	}
	
	@Test
	public void testContactLink() {
		floatingMenuPage.clickContactLink();
		assertEquals("http://the-internet.herokuapp.com/floating_menu#contact", FloatingMenuPage.getDriver()
				.getCurrentUrl());
	}
	
	@Test
	public void testAboutLink() {
		floatingMenuPage.clickAboutLink();
		assertEquals("http://the-internet.herokuapp.com/floating_menu#about", FloatingMenuPage.getDriver()
				.getCurrentUrl());
	}
	
	@Test
	public void testHomeLinkWithScrolling() {
		floatingMenuPage.scrollPageDown(floatingMenuPage.getPageHeight() - 100);
		floatingMenuPage.clickHomeLink();
		assertEquals("http://the-internet.herokuapp.com/floating_menu#home", FloatingMenuPage.getDriver()
				.getCurrentUrl());
		floatingMenuPage.scrollPageUp(floatingMenuPage.getPageHeight() - 100);
	}
	
	@Test
	public void testNewsLinkWithScrolling() {
		floatingMenuPage.scrollPageDown(floatingMenuPage.getPageHeight());
		floatingMenuPage.clickNewsLink();
		assertEquals("http://the-internet.herokuapp.com/floating_menu#news", FloatingMenuPage.getDriver()
				.getCurrentUrl());
		floatingMenuPage.scrollPageUp(floatingMenuPage.getPageHeight());
	}
	
	@Test
	public void testContactLinkWithScrolling() {
		floatingMenuPage.scrollPageDown(floatingMenuPage.getPageHeight() - 200);
		floatingMenuPage.clickContactLink();
		assertEquals("http://the-internet.herokuapp.com/floating_menu#contact", FloatingMenuPage.getDriver()
				.getCurrentUrl());
		floatingMenuPage.scrollPageUp(floatingMenuPage.getPageHeight() - 200);
	}
	
	@Test
	public void testAboutLinkWithScrolling() {
		floatingMenuPage.scrollPageDown(floatingMenuPage.getPageHeight());
		floatingMenuPage.clickAboutLink();
		assertEquals("http://the-internet.herokuapp.com/floating_menu#about", FloatingMenuPage.getDriver()
				.getCurrentUrl());
		floatingMenuPage.scrollPageUp(floatingMenuPage.getPageHeight());
	}
	
	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
	}
}
