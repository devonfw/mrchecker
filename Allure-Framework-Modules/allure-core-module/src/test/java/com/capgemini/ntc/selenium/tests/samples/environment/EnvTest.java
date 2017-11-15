package com.capgemini.ntc.selenium.tests.samples.environment;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.ntc.test.core.base.environments.Env;
import com.capgemini.ntc.test.core.base.environments.EnvironmentService;
import com.capgemini.ntc.test.core.base.environments.GetEnvironmentParam;
import com.capgemini.ntc.test.core.base.environments.SpreadsheetEnvironmentService;
import com.capgemini.ntc.test.core.exceptions.BFInputDataException;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class EnvTest {
	
	EnvironmentService systemUnderTest;
	
	@Before
	public void setup() {
		systemUnderTest = new Env.SingletonBuilder().build();
		
		
		
		//  OR this type to initialize //
//		String path = "C:\\Repo\\devonfw-testing\\Allure-Framework-Modules\\allure-selenium-module\\src\\resources\\enviroments\\environments.csv";
//		EnvironmentService envInstance = new SpreadsheetEnvironmentService.SingletonBuilder(path).build();
//		systemUnderTest = new Env.SingletonBuilder(envInstance).build();
//		
	}
	
	@Test
	public void getServiceAddressShouldReturnCorrectServiceAddressForDefaultEnvironment() {
		String actualAddress = systemUnderTest.getServiceAddress("DMA_URL");
		String expectedAddress = "https://homepage.company.com/ftgw/dpcs/dma/";
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
		
		systemUnderTest.set("DEV999");
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
