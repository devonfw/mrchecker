package com.capgemini.ntc.selenium.core.tests.samples.environment;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.capgemini.ntc.test.core.base.environments.EnvironmentServices;
import com.capgemini.ntc.test.core.base.environments.ParametersManager;
import com.capgemini.ntc.selenium.core.exceptions.BFComponentStateException;
import com.example.selenium.pages.enums.NetBenefitsPageUrls;
import com.example.selenium.pages.enums.ServicesURLsEnum;
import com.capgemini.ntc.test.core.exceptions.BFInputDataException;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class EnvTest {

	EnvironmentServices systemUnderTest;

	@Test
	public void getServiceAddressShouldReturnCorrectServiceAddressForDefaultEnvironment() {
		systemUnderTest = new EnvironmentServices();

		String actualAddress = systemUnderTest.getServiceAddress("DMA_URL");
		String expectedAddress = "https://homepage.company.com/ftgw/dpcs/dma/";
		assertEquals(expectedAddress, actualAddress);
	}

	@Test(expected = BFComponentStateException.class)
	public void getServiceAddressShouldThrowExceptionWhenServiceNotFound() {
		systemUnderTest = new EnvironmentServices();

		systemUnderTest.getServiceAddress("NOT_EXISTING_SERVICE");
	}

	@Test
	public void setEnvironmentShouldSucceedWhenEnvironmentExist() {
		systemUnderTest = new EnvironmentServices();

		systemUnderTest.set("DEV2");
	}

	@Test
	public void getServiceAddressShouldReturnCorrectServiceAddressForSelectedEnvironment() {
		systemUnderTest = new EnvironmentServices();

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
		systemUnderTest = new EnvironmentServices();

		systemUnderTest.set("DEV999");
	}

	@Test
	public void ServicesURLsEnumIsReturningCorrectAddresses() {
		systemUnderTest = ParametersManager.environment();

		systemUnderTest.set("DEV1");
		String actualAddress = ServicesURLsEnum.WWW_FONT_URL.getAddress();
		String expectedAddress = "https://myresearchqa1.company.com/";
		assertEquals(expectedAddress, actualAddress);

		systemUnderTest.set("DEV2");
		actualAddress = ServicesURLsEnum.WWW_FONT_URL.getAddress();
		expectedAddress = "https://myresearchqa2.company.com/";
		assertEquals(expectedAddress, actualAddress);
	}

	@Test
	public void EnumsUsingServicesSubURLSShouldReturnCorrectAddress() {
		systemUnderTest = ParametersManager.environment();

		String enumAddress = NetBenefitsPageUrls.MAIN_PAGE.toString();
		String expectedAddress = systemUnderTest.getServiceAddress("SPS_WI_URL");
		assertEquals(expectedAddress, enumAddress);
	}

	@Test
	public void envLogTest() {
		BFLogger.logEnv("----- test -----");
	}

	@Test
	public void EnumsUsingServicesSubURLSShouldReturnCorrectAddressAfterEnvChange() {
		systemUnderTest = ParametersManager.environment();

		systemUnderTest.set("DEV1");
		String expectedAddress = systemUnderTest.getServiceAddress("SPS_WI_URL");
		String enumAddress = NetBenefitsPageUrls.MAIN_PAGE.toString();
		assertEquals(expectedAddress, enumAddress);
		enumAddress = NetBenefitsPageUrls.MAIN_PAGE.subURL();
		assertEquals(expectedAddress, enumAddress);

		systemUnderTest.set("DEV2");
		expectedAddress = systemUnderTest.getServiceAddress("SPS_WI_URL");
		enumAddress = NetBenefitsPageUrls.MAIN_PAGE.toString();
		assertEquals(expectedAddress, enumAddress);
		enumAddress = NetBenefitsPageUrls.MAIN_PAGE.subURL();
		assertEquals(expectedAddress, enumAddress);
	}
}
