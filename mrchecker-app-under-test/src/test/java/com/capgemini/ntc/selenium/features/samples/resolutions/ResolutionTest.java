package com.capgemini.ntc.selenium.features.samples.resolutions;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import com.capgemini.ntc.core.groupTestCases.testSuites.tags.TestsResolution;
import com.capgemini.ntc.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.enums.ResolutionEnum;
import com.capgemini.ntc.selenium.core.utils.ResolutionUtils;
import com.capgemini.ntc.selenium.pages.projectX.registration.RegistryPage;
import com.capgemini.ntc.test.core.BaseTest;

import junitparams.JUnitParamsRunner;
import ru.yandex.qatools.allure.annotations.Features;

@Features("Resolution")
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
