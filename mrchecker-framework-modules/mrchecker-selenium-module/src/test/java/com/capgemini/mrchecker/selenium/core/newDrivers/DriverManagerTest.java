package com.capgemini.mrchecker.selenium.core.newDrivers;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.mrchecker.selenium.core.base.properties.PropertiesSelenium;
import com.capgemini.mrchecker.selenium.core.newDrivers.DriverManager;
import com.capgemini.mrchecker.test.core.base.properties.PropertiesSettingsModule;
import com.google.inject.Guice;

public class DriverManagerTest {
	
	private DriverManager driverManager;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
		
		PropertiesSelenium propertiesSelenium = Guice.createInjector(PropertiesSettingsModule.init())
				.getInstance(PropertiesSelenium.class);
		
		driverManager = new DriverManager(propertiesSelenium);
		
	}
	
	@After
	public void tearDown() throws Exception {
		driverManager.stop();
	}
	
	@Test
	public void test() {
		assertTrue(true);
	}
	
}
