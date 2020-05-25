package com.capgemini.mrchecker.selenium.projectX.registration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.*;

import com.capgemini.mrchecker.selenium.pages.projectX.registration.RegistryPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

@Disabled("Registration site not not on the Web")
public class RegistryPageTest extends BaseTest {
	
	private RegistryPage registryPage;
	
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
	}
	
	@AfterAll
	public static void tearDownAfterClass() throws Exception {
	}
	
	@BeforeEach
	public void setUp() {
		BFLogger.logInfo("[Step 1] As a standard user I will open Registry Page,  So that my I can fill data");
		registryPage = new RegistryPage();
		registryPage.initialize();
	}
	
	@AfterEach
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
	public void EXAMPLE_FAIL_TEST() {
		
		BFLogger.logInfo(
				"[Step 2] As a standard user I click Submit button,  So that my data will be saved");
		registryPage.clickSubmit();
		assertThat("Submit display button was not visible", false, is(registryPage.isButtonSubmitDisplayed()));
	}
}
