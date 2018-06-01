package com.capgemini.mrchecker.webapi.core.base.driver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Test;

import com.capgemini.mrchecker.webapi.core.base.driver.DriverManager;
import com.capgemini.mrchecker.webapi.core.base.runtime.RuntimeParameters;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.FatalStartupException;

public class DriverManagerTest {
	
	private WireMockServer driver;
	
	@Test
	public void testRuntimeEnvironmentPortHttp() {
		System.setProperty("mock_http_port", "8080");
		RuntimeParameters.MOCK_HTTP_PORT.refreshParameterValue();
		assertEquals("System parameters for 'mock_http_port' should be '8080'", "8080", RuntimeParameters.MOCK_HTTP_PORT.getValue());
	}
	
	@Test
	public void testRuntimeEnvironmentPortHttps() {
		System.setProperty("mock_https_port", "8000");
		RuntimeParameters.MOCK_HTTPS_PORT.refreshParameterValue();
		assertEquals("System parameters for 'mock_https_port' should be '8000'", "8000", RuntimeParameters.MOCK_HTTPS_PORT.getValue());
	}
	
	@Test
	public void testRuntimeEnvironmentPortHttpDefaultValue() {
		System.clearProperty("mock_http_port");
		RuntimeParameters.MOCK_HTTP_PORT.refreshParameterValue();
		assertEquals("Default value for system parameters 'mock_http_port' should be ''", "", RuntimeParameters.MOCK_HTTP_PORT.getValue());
	}
	
	@Test
	public void testRuntimeEnvironmentPortHttpsDefaultValue() {
		System.clearProperty("mock_https_port");
		RuntimeParameters.MOCK_HTTPS_PORT.refreshParameterValue();
		assertEquals("Default value for system parameters 'mock_https_port' should be ''", "", RuntimeParameters.MOCK_HTTPS_PORT.getValue());
	}
	
	@Test
	public void testWiremockServerStartPortHttpRandomPortHttps() {
		System.setProperty("mock_http_port", "8083");
		RuntimeParameters.MOCK_HTTP_PORT.refreshParameterValue();
		RuntimeParameters.MOCK_HTTPS_PORT.refreshParameterValue();
		driver = DriverManager.getDriverVirtualService();
		assertTrue("Mock server does not run", driver.isRunning());
		assertEquals("Mock server for http does not run o port 8083", 8083, driver.port());
		assertTrue("Mock server for https does not run on random port", (Integer) driver.httpsPort() instanceof Integer);
	}
	
	@Test
	public void testWiremockServerStartPortHttpsRandomPortHttp() {
		System.setProperty("mock_https_port", "8080");
		RuntimeParameters.MOCK_HTTP_PORT.refreshParameterValue();
		RuntimeParameters.MOCK_HTTPS_PORT.refreshParameterValue();
		driver = DriverManager.getDriverVirtualService();
		assertTrue("Mock server does not run", driver.isRunning());
		assertEquals("Mock server for https does not run o port 8080", 8080, driver.httpsPort());
		assertTrue("Mock server for http does not run on random port", (Integer) driver.port() instanceof Integer);
	}
	
	@Test
	public void testWiremockServerStartTwoServersWithTheSameHttpPort() {
		System.setProperty("mock_http_port", "8081");
		RuntimeParameters.MOCK_HTTP_PORT.refreshParameterValue();
		RuntimeParameters.MOCK_HTTPS_PORT.refreshParameterValue();
		
		WireMockServer driver1 = null;
		WireMockServer driver2 = null;
		DriverManager.closeDriverVirtualServer();
		try {
			// Start #1 server
			driver1 = DriverManager.getDriverVirtualService();
			assertTrue("Mock server does not run", driver1.isRunning());
			assertEquals("Mock server for http does not run o port 8081", 8081, driver1.port());
			assertTrue("Mock server for https does not run on random port", (Integer) driver1.httpsPort() instanceof Integer);
			
			// Enable to add new server instances in this thread. Simulation of multithread and bind to the same port
			DriverManager.clearAllDrivers();
			
			// Start #2 server
			driver2 = DriverManager.getDriverVirtualService();
			assertTrue("Mock server does not run", driver2.isRunning());
			assertEquals("Mock server for http does not run o port 8081", 8081, driver2.port());
			assertTrue("Mock server for https does not run on random port", (Integer) driver2.httpsPort() instanceof Integer);
		} catch (FatalStartupException e) {
			assertTrue("No information about bind error", e.getMessage()
					.contains("Address already in use: bind"));
		} finally {
			// Close all drivers
			try {
				driver1.stop();
				driver2.stop();
			} catch (Exception e) {
			}
		}
	}
	
	@After
	public void afterTest() {
		DriverManager.closeDriverVirtualServer();
		
	}
	
}
