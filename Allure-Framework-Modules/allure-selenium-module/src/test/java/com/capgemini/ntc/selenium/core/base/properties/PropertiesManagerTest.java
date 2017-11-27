package com.capgemini.ntc.selenium.core.base.properties;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class PropertiesManagerTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private PropertiesSelenium propertiesSelenium;
	
	@Before
	public void setUp() throws Exception {
		String path = System.getProperty("user.dir") + Paths.get("/src/test/resources/selenium.properties");
		Injector i = Guice.createInjector(new PropertiesModule(path));
		this.propertiesSelenium = i.getInstance(PropertiesSelenium.class);
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test() {
		
		assertEquals("", "./lib/webdrivers/chrome/chromedriver.exe", propertiesSelenium.getChromeDriverPath());
	}
	
}
