package com.capgemini.mrchecker.selenium.projectX.registration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.selenium.pages.projectX.registration.RegistryPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

@Disabled("Registration site not not on the Web")
public class RegistryPageTest extends BaseTest {
	
	private RegistryPage registryPage;
	
	@BeforeEach
	public void setUp() {
		BFLogger.logInfo("[Step 1] As a standard user I will open Registry Page,  So that my I can fill data");
		registryPage = PageFactory.getPageInstance(RegistryPage.class);
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
