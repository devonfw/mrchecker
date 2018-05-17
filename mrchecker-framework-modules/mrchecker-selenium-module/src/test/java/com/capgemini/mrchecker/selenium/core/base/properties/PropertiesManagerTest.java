package com.capgemini.mrchecker.selenium.core.base.properties;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.mrchecker.selenium.core.base.properties.PropertiesSelenium;
import com.capgemini.mrchecker.test.core.base.properties.PropertiesSettingsModule;
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
		String path = System.getProperty("user.dir") + Paths.get("/src/test/resources/settings.properties");
		Injector i = Guice.createInjector(PropertiesSettingsModule.init(path));
		this.propertiesSelenium = i.getInstance(PropertiesSelenium.class);
	}
	
	@After
	public void tearDown() throws Exception {
		PropertiesSettingsModule.delInstance();
	}
	
	@Test
	public void testParamterGetChrome() {
		assertEquals("", "chromedriver.exe", propertiesSelenium.getSeleniumChrome());
	}
	
	@Test
	public void testParamterGetFirefox() {
		assertEquals("", "geckodriver.exe", propertiesSelenium.getSeleniumFirefox());
	}
	
	@Test
	public void testParamterGetIE() {
		assertEquals("", "IEDriverServer.exe", propertiesSelenium.getSeleniumIE());
	}
	
	// @Ignore
	@Test
	public void testDefaultParamters() {
		PropertiesSettingsModule.delInstance();
		
		Injector i = Guice.createInjector(PropertiesSettingsModule.init());
		PropertiesSelenium propertiesSelenium = i.getInstance(PropertiesSelenium.class);
		
		assertEquals("", "./lib/webdrivers/chrome/chromedriver.exe", propertiesSelenium.getSeleniumChrome());
	}
	
}
