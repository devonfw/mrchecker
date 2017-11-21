package com.capgemini.ntc.test.core.base.environment;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.base.environment.EnvironmentModule;
import com.capgemini.ntc.test.core.base.environment.EnvironmentService;
import com.capgemini.ntc.test.core.base.environment.GetEnvironmentParam;
import com.capgemini.ntc.test.core.base.environment.providers.SpreadsheetEnvironmentService;
import com.capgemini.ntc.test.core.exceptions.BFInputDataException;
import com.capgemini.ntc.test.core.logger.BFLogger;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Provides;

public class EnvironmentMainTest {
	
	EnvironmentService systemUnderTest;
	
	@Before
	public void setup() {
		systemUnderTest = Guice.createInjector(environmentTestModel())
				.getInstance(EnvironmentService.class);
		
	}
	
	@After
	public void teardown() {
	}
	
	@Test
	public void testDependecyInjection() throws Exception {
		SpreadsheetEnvironmentService.delInstance();
		EnvironmentService environmentService = Guice.createInjector(new EnvironmentModule())
				.getInstance(EnvironmentService.class);
		
		environmentService.setEnvironment("DEV");
		assertEquals("http://demoqa.com/", environmentService.getServiceAddress("WWW_FONT_URL"));
		
	}
	
	@Test
	public void getServiceAddressShouldReturnCorrectServiceAddressForDefaultEnvironment() {
		SpreadsheetEnvironmentService.delInstance();
		systemUnderTest = Guice.createInjector(new EnvironmentModule())
				.getInstance(EnvironmentService.class);
		
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
		
		systemUnderTest.setEnvironment("DEV2");
	}
	
	@Test
	public void getServiceAddressShouldReturnCorrectServiceAddressForSelectedEnvironment() {
		
		String serviceName = "MY_RESEARCH_URL_VALUE";
		
		systemUnderTest.setEnvironment("DEV2");
		String actualAddress = systemUnderTest.getServiceAddress(serviceName);
		String expectedAddress = "https://myresearchqa2.company.com/";
		assertEquals(expectedAddress, actualAddress);
		
		systemUnderTest.setEnvironment("DEV1");
		actualAddress = systemUnderTest.getServiceAddress(serviceName);
		expectedAddress = "https://myresearchqa1.company.com/";
		assertEquals(expectedAddress, actualAddress);
		
		systemUnderTest.setEnvironment("DEV2");
		actualAddress = systemUnderTest.getServiceAddress(serviceName);
		expectedAddress = "https://myresearchqa2.company.com/";
		assertEquals(expectedAddress, actualAddress);
	}
	
	@Test(expected = BFInputDataException.class)
	public void setEnvironmentShouldThrowExceptionWhenEnvironmentNotFound() {
		
		systemUnderTest.setEnvironment("DEV99");
	}
	
	@Test
	public void ServicesURLsEnumIsReturningCorrectAddresses() {
		
		systemUnderTest.setEnvironment("DEV1");
		BaseTest.setEnvironmentService(systemUnderTest);
		
		String actualAddress = GetEnvironmentParam.WWW_FONT_URL.getAddress();
		String expectedAddress = "https://myresearchqa1.company.com/";
		assertEquals(expectedAddress, actualAddress);
		
		systemUnderTest.setEnvironment("DEV2");
		actualAddress = GetEnvironmentParam.WWW_FONT_URL.getAddress();
		expectedAddress = "https://myresearchqa2.company.com/";
		assertEquals(expectedAddress, actualAddress);
	}
	
	@Test
	public void envLogTest() {
		BFLogger.logEnv("----- test -----");
	}

	private AbstractModule environmentTestModel() {
		return new AbstractModule() {
			
			@Override
			protected void configure() {
			}
			
			@Provides
			EnvironmentService provideEnvironmentService() {
				String path = System.getProperty("user.dir") + Paths.get("/src/test/resources/enviroments/environments.csv");
				String environment = "QA";
				SpreadsheetEnvironmentService.init(path, environment);
				return SpreadsheetEnvironmentService.getInstance();
			}
		};
	}
	
}
