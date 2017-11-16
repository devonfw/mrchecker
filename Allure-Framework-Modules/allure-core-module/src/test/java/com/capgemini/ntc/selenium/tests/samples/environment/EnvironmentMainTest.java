package com.capgemini.ntc.selenium.tests.samples.environment;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.capgemini.ntc.test.core.base.environment.EnvironmentMain;
import com.capgemini.ntc.test.core.base.environment.EnvironmentMain.SingletonBuilder;
import com.capgemini.ntc.test.core.base.environment.EnvironmentModule;
import com.capgemini.ntc.test.core.base.environment.EnvironmentService;
import com.capgemini.ntc.test.core.base.environment.GetEnvironmentParam;
import com.capgemini.ntc.test.core.base.environment.providers.SpreadsheetEnvironmentService;
import com.capgemini.ntc.test.core.exceptions.BFInputDataException;
import com.capgemini.ntc.test.core.logger.BFLogger;
import com.google.inject.Guice;

public class EnvironmentMainTest {
	
	EnvironmentService systemUnderTest;
	
	@Before
	public void setup() {
		// systemUnderTest = new EnvironmentMain(SpreadsheetEnvironmentService.init()).getEnvironmentService();
		
		// OR this type to initialize //
		String path = System.getProperty("user.dir") + Paths.get("/src/test/resources/enviroments/environments.csv");
		EnvironmentService envInstance = SpreadsheetEnvironmentService.init(path);
		systemUnderTest = new EnvironmentMain.SingletonBuilder(envInstance).build();
		
	}
	
	@After
	public void teardown() {
	}
	
	@Test
	public void testDependecyInjection() throws Exception {
		
		EnvironmentService environmentService = Guice.createInjector(new EnvironmentModule())
				.getInstance(EnvironmentMain.SingletonBuilder.class)
				.build();
		
		environmentService.set("DEV");
		assertEquals("http://demoqa.com/", environmentService.getServiceAddress("WWW_FONT_URL"));
		
	}
	
	@Test
	public void getServiceAddressShouldReturnCorrectServiceAddressForDefaultEnvironment() {
		SpreadsheetEnvironmentService.delInstance();
		String path = System.getProperty("user.dir") + Paths.get("/src/test/resources/enviroments/environments.csv");
		EnvironmentService envInstance = SpreadsheetEnvironmentService.init(path);
		systemUnderTest = new EnvironmentMain.SingletonBuilder(envInstance).build();
		
		String actualAddress = systemUnderTest.getServiceAddress("DMA_URL");
		String expectedAddress = "https://dma.company.com/";
		assertEquals(expectedAddress, actualAddress);
	}
	
	@Test(expected = BFInputDataException.class)
	public void getServiceAddressShouldThrowExceptionWhenServiceNotFound() {
		
		systemUnderTest.getServiceAddress("NOT_EXISTING_SERVICE");
	}
	
	@Test
	public void setEnvironmentShouldSucceedWhenEnvironmentExist() {
		
		systemUnderTest.set("DEV2");
	}
	
	@Test
	public void getServiceAddressShouldReturnCorrectServiceAddressForSelectedEnvironment() {
		
		String serviceName = "MY_RESEARCH_URL_VALUE";
		
		systemUnderTest.set("DEV2");
		String actualAddress = systemUnderTest.getServiceAddress(serviceName);
		String expectedAddress = "https://myresearchqa2.company.com/";
		assertEquals(expectedAddress, actualAddress);
		
		systemUnderTest.set("DEV1");
		actualAddress = systemUnderTest.getServiceAddress(serviceName);
		expectedAddress = "https://myresearchqa1.company.com/";
		assertEquals(expectedAddress, actualAddress);
		
		systemUnderTest.set("DEV2");
		actualAddress = systemUnderTest.getServiceAddress(serviceName);
		expectedAddress = "https://myresearchqa2.company.com/";
		assertEquals(expectedAddress, actualAddress);
	}
	
	@Test(expected = BFInputDataException.class)
	public void setEnvironmentShouldThrowExceptionWhenEnvironmentNotFound() {
		
		systemUnderTest.set("DEV99");
	}
	
	@Test
	public void ServicesURLsEnumIsReturningCorrectAddresses() {
		
		systemUnderTest.set("DEV1");
		String actualAddress = GetEnvironmentParam.WWW_FONT_URL.getAddress();
		String expectedAddress = "https://myresearchqa1.company.com/";
		assertEquals(expectedAddress, actualAddress);
		
		systemUnderTest.set("DEV2");
		actualAddress = GetEnvironmentParam.WWW_FONT_URL.getAddress();
		expectedAddress = "https://myresearchqa2.company.com/";
		assertEquals(expectedAddress, actualAddress);
	}
	
	@Test
	public void envLogTest() {
		BFLogger.logEnv("----- test -----");
	}
	
}
