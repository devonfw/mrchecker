package com.capgemini.mrchecker.test.core.base.environment;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.base.encryption.DataEncryptionModule;
import com.capgemini.mrchecker.test.core.base.encryption.IDataEncryptionService;
import com.capgemini.mrchecker.test.core.base.environment.EnvironmentModule;
import com.capgemini.mrchecker.test.core.base.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.test.core.base.environment.IEnvironmentService;
import com.capgemini.mrchecker.test.core.base.environment.providers.SpreadsheetEnvironmentService;
import com.capgemini.mrchecker.test.core.exceptions.BFInputDataException;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Provides;

@Ignore
public class EnvironmentMainTest {
	
	IEnvironmentService systemUnderTest;
	
	@Before
	public void setup() {
		systemUnderTest = Guice.createInjector(environmentTestModel())
				.getInstance(IEnvironmentService.class);
		
		BaseTest.getEnvironmentService();
	}
	
	@After
	public void teardown() {
	}
	
	@Test
	public void testDependecyInjection() throws Exception {
		SpreadsheetEnvironmentService.delInstance();
		IEnvironmentService environmentService = Guice.createInjector(new EnvironmentModule())
				.getInstance(IEnvironmentService.class);
		
		environmentService.setEnvironment("DEV");
		assertEquals("http://demoqa.com/", environmentService.getValue("WWW_FONT_URL"));
		
	}
	
	@Test
	public void getServiceAddressShouldReturnCorrectServiceAddressForDefaultEnvironment() {
		SpreadsheetEnvironmentService.delInstance();
		systemUnderTest = Guice.createInjector(new EnvironmentModule())
				.getInstance(IEnvironmentService.class);
		
		String actualAddress = systemUnderTest.getValue("DMA_URL");
		String expectedAddress = "https://dma.company.com";
		assertEquals(expectedAddress, actualAddress);
	}
	
	@Test(expected = BFInputDataException.class)
	public void getServiceAddressShouldThrowExceptionWhenServiceNotFound() {
		
		systemUnderTest.getValue("NOT_EXISTING_SERVICE");
	}
	
	@Test
	public void setEnvironmentShouldSucceedWhenEnvironmentExist() {
		
		systemUnderTest.setEnvironment("DEV2");
		assertEquals("DEV2", systemUnderTest.getEnvironment());
	}
	
	@Test
	public void getServiceAddressShouldReturnCorrectServiceAddressForSelectedEnvironment() {
		
		String serviceName = "MY_RESEARCH_URL_VALUE";
		
		systemUnderTest.setEnvironment("DEV2");
		String actualAddress = systemUnderTest.getValue(serviceName);
		String expectedAddress = "https://myresearchqa2.company.com/";
		assertEquals(expectedAddress, actualAddress);
		
		systemUnderTest.setEnvironment("DEV1");
		actualAddress = systemUnderTest.getValue(serviceName);
		expectedAddress = "https://myresearchqa1.company.com/";
		assertEquals(expectedAddress, actualAddress);
		
		systemUnderTest.setEnvironment("DEV2");
		actualAddress = systemUnderTest.getValue(serviceName);
		expectedAddress = "https://myresearchqa2.company.com/";
		assertEquals(expectedAddress, actualAddress);
	}
	
	@Test(expected = BFInputDataException.class)
	public void setEnvironmentShouldThrowExceptionWhenEnvironmentNotFound() {
		
		systemUnderTest.setEnvironment("DEV99");
	}
	
	@Test
	public void ServicesURLsEnumIsReturningCorrectAddresses() {
		BaseTest.getEnvironmentService();
		
		systemUnderTest.setEnvironment("DEV1");
		BaseTest.setEnvironmentService(systemUnderTest);
		GetEnvironmentParam.refreshAll();
		
		String actualAddress = GetEnvironmentParam.WWW_FONT_URL.getValue();
		String expectedAddress = "https://myresearchqa1.company.com/";
		assertEquals(expectedAddress, actualAddress);
		
		systemUnderTest.setEnvironment("DEV2");
		actualAddress = GetEnvironmentParam.WWW_FONT_URL.getValue();
		expectedAddress = "https://myresearchqa2.company.com/";
		assertEquals(expectedAddress, actualAddress);
	}
	
	@Test
	public void envLogTest() {
		BFLogger.logEnv("----- test -----");
	}
	
	@Test
	public void testNoDataEncryptionServicePresent() {
		// given
		String serviceName = "PASSWORD";
		String expected = "ENC(gD6S9sHAhNb6kVsCsZd81A==)";
		systemUnderTest.setEnvironment("DEV");
		systemUnderTest.setDataEncryptionService(null);
		
		// when
		String value = systemUnderTest.getValue(serviceName);
		
		// then
		assertEquals(expected, value);
	}
	
	@Test
	public void testDataEncryptionServicePresent() {
		// given
		String serviceName = "PASSWORD";
		String expected = "test";
		IDataEncryptionService encryptionService = Guice.createInjector(new DataEncryptionModule())
				.getInstance(IDataEncryptionService.class);
		systemUnderTest.setDataEncryptionService(encryptionService);
		
		// when
		String value = systemUnderTest.getValue(serviceName);
		
		// then
		assertEquals(expected, value);
	}
	
	private AbstractModule environmentTestModel() {
		return new AbstractModule() {
			
			@Override
			protected void configure() {
			}
			
			@Provides
			IEnvironmentService provideEnvironmentService() {
				String path = System.getProperty("user.dir") + Paths.get("/src/test/resources/enviroments/environments.csv");
				String environment = "QA";
				SpreadsheetEnvironmentService.init(path, environment);
				return SpreadsheetEnvironmentService.getInstance();
			}
		};
	}
	
}
