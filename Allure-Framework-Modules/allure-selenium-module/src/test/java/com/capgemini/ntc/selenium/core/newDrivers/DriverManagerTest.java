package com.capgemini.ntc.selenium.core.newDrivers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;

public class DriverManagerTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private DriverManager driverManager;
	
	@Before
	public void setUp() throws Exception {
		
		
		driverManager = Guice.createInjector(new DriverManagerModule())
				.getInstance(DriverManager.class);
		
		
		
		
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
}
