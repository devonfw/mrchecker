package com.capgemini.ntc.selenium.core.newDrivers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.ntc.selenium.core.base.properties.PropertiesModule;
import com.capgemini.ntc.selenium.core.base.properties.PropertiesSelenium;
import com.capgemini.ntc.selenium.core.base.runtime.RuntimeParameters;
import com.google.inject.Guice;
import com.google.inject.name.Names;

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
		
		
		
		PropertiesSelenium propertiesSelenium = Guice.createInjector(PropertiesModule.init())
				.getInstance(PropertiesSelenium.class);
		
		
		driverManager = new DriverManager(propertiesSelenium);
		
		
	
		
		
		
	}
	
	@After
	public void tearDown() throws Exception {
		driverManager.stop();
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
}
