package com.capgemini.mrchecker.core.groupTestCases.testCases.tag2;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSmoke;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsTag2;
import com.capgemini.mrchecker.selenium.pages.projectX.registration.RegistryPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

import io.qameta.allure.Feature;

@Feature("TAG3")
@Feature("TAG2")
@TestsTag2
@TestsSmoke
public class ThirdTest_tag2_Test extends BaseTest {
	
	private RegistryPage registryPage;
	
	@Override
	public void setUp() {
		BFLogger.logInfo("[Step 1] As a standard user I will open Registry Page,  So that my I can fill data");
		registryPage = PageFactory.getPageInstance(RegistryPage.class);
	}
	
	@Test
	public void QCID_StayOnResistryPage_Tag2_Third() throws InterruptedException {
		
		BFLogger.logInfo(
				"[Step 2] As a standard user I click Submit button,  So that I will stay on Registry page");
		registryPage.clickSubmit();
		assertThat(registryPage.isLoaded(), is(equalTo(true)));
		
		TimeUnit.SECONDS.sleep(3); // This is for demo. Do not do it at home
		
	}
	
	@Test
	public void testTag2_Third() {
		BFLogger.logInfo("ThirdTest_tag2.test()");
		
	}
	
}
