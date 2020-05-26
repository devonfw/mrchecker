package com.capgemini.mrchecker.core.groupTestCases.testCases.tag1;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsIE;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsTag1;
import com.capgemini.mrchecker.selenium.pages.projectX.registration.RegistryPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

import io.qameta.allure.Feature;

@Feature("TAG1")
@TestsTag1
@TestsIE
public class SecondTest_tag1_Test extends BaseTest {
	
	private RegistryPage registryPage;
	
	@Override
	public void setUp() {
		BFLogger.logInfo("[Step 1] As a standard user I will open Registry Page,  So that my I can fill data");
		registryPage = new RegistryPage();
		registryPage.initialize();
		
	}
	
	@Override
	public void tearDown() {
	}
	
	@Test
	public void QCID_StayOnResistryPage_Tag1_Second() throws InterruptedException {
		
		BFLogger.logInfo(
				"[Step 2] As a standard user I click Submit button,  So that I will stay on Registry page");
		registryPage.clickSubmit();
		assertThat(registryPage.isLoaded(), is(equalTo(true)));
		
		TimeUnit.SECONDS.sleep(3); // This is for demo. Do not do it at home
	}
	
	@Test
	public void testTag1_Second() {
		BFLogger.logInfo("SecondTest_tag1.test()");
		
	}
	
}
