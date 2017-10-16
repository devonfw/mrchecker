package com.capgemini.ntc.selenium.tests.tests.testTag.tag1;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.example.core.logger.BFLogger;
import com.example.core.tests.core.BaseTest;
import com.capgemini.ntc.selenium.pages.features.registration.RegistryPage;
import com.capgemini.ntc.selenium.tests.testSuites.testType.TestsChrome;
import com.capgemini.ntc.selenium.tests.testSuites.testType.TestsTag1;

import ru.yandex.qatools.allure.annotations.Features;

@Features("TAG1")
@Category({ TestsTag1.class, TestsChrome.class })
public class FristTest_tag1 extends BaseTest{
	private RegistryPage registryPage;


	@Override
	public void setUp() {
		BFLogger.logInfo("[Step 1] As a standard user I will open Registry Page,  So that my I can fill data");
		registryPage = new RegistryPage();
		
	}

	@Override
	public void tearDown() {
	}
	
	@Test
	public void QCID_StayOnResistryPage_Tag1_First() throws InterruptedException {

		BFLogger.logInfo(
				"[Step 2] As a standard user I click Submit button,  So that I will stay on Registry page");
		registryPage.clickSubmit();
		assertThat(true, is(registryPage.isLoaded()));
		
		TimeUnit.SECONDS.sleep(3); //This is for demo. Do not do it at home 
	}

	@Test
	public void testTag1_Frist() {
		BFLogger.logInfo("FristTest_tag1.test()");
	}
	
	
}
