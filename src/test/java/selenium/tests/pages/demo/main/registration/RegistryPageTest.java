package com.example.selenium.tests.tests.pages.demo.main.registration;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import org.junit.*;

import com.example.core.logger.BFLogger;
import com.example.core.tests.core.BaseTest;
import com.example.selenium.pages.features.registration.RegistryPage;

public class RegistryPageTest extends BaseTest {

	private RegistryPage registryPage;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	@Before
	public void setUp() {
		BFLogger.logInfo("[Step 1] As a standard user I will open Registry Page,  So that my I can fill data");
		registryPage = new RegistryPage();
	}
	@After
	public void tearDown() {
	}
	@Test
	public void QCID_12342343_StayOnResistryPage() {

		BFLogger.logInfo(
				"[Step 2] As a standard user I click Submit button,  So that I will stay on Registry page");
		registryPage.clickSubmit();
		assertThat(true, is(registryPage.isLoaded()));
	}
	@Test
	public void QCID_3784343_DataAreSaved() {

		BFLogger.logInfo(
				"[Step 2] As a standard user I click Submit button,  So that my data will be saved");
		registryPage.clickSubmit();
		assertThat(false, is(registryPage.isButtonSubmitDisplayed()));
	}
}
