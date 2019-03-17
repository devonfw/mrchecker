package com.capgemini.mrchecker.selenium.features.samples.resolutions;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsResolution;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.enums.ResolutionEnum;
import com.capgemini.mrchecker.selenium.core.utils.ResolutionUtils;
import com.capgemini.mrchecker.selenium.pages.projectX.registration.RegistryPage;
import com.capgemini.mrchecker.test.core.BaseTest;

import io.qameta.allure.Feature;
import junitparams.JUnitParamsRunner;

@Feature("Resolution")
@Category({ TestsResolution.class, TestsSelenium.class })
@RunWith(JUnitParamsRunner.class)
public class ResolutionTest extends BaseTest {
	private RegistryPage registryPage;
	
	private static Object[] getResolutions() {
		return new Object[] {
						ResolutionEnum.w768,
						ResolutionEnum.w960,
						ResolutionEnum.w1920 };
	}
	
	@Override
	public void setUp() {
		registryPage = new RegistryPage();
	}
	
	@junitparams.Parameters(method = "getResolutions")
	@Test
	public void resolution_test(ResolutionEnum resolutionEnum) throws InterruptedException {
		ResolutionUtils.setResolution(BasePage.getDriver(), resolutionEnum);
		
		assertThat(true, is(registryPage.isButtonSubmitDisplayed()));
		TimeUnit.SECONDS.sleep(1); // This is for demo. Do not do it at home
		
	}
	
	@Override
	public void tearDown() {
	}
	
}
