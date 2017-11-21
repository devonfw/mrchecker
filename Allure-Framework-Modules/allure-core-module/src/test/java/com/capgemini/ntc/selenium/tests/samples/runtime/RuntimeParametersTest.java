package com.capgemini.ntc.selenium.tests.samples.runtime;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.ntc.test.core.base.runtime.RuntimeParameters;
import com.capgemini.ntc.test.core.base.runtime.RuntimeParametersModule;
import com.google.inject.Guice;

public class RuntimeParametersTest {
	
	private static RuntimeParameters runtimeParametersInstance;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("env", "DEV");
		
		// Read System or maven parameters
		runtimeParametersInstance = Guice.createInjector(new RuntimeParametersModule())
				.getInstance(RuntimeParameters.class);
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetProperty() {
		assertEquals("System parameters for 'env' are not equal",  "DEV", runtimeParametersInstance.getEnv());  
	}
	
	@Test
	public void testGetParams() throws Exception {
		assertEquals("List of System parameters does not equal", "{env=DEV}",  runtimeParametersInstance.getParameters().toString());
	}
	
}
