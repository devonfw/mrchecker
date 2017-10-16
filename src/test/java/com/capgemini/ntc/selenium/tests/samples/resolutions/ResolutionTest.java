package com.capgemini.ntc.selenium.tests.tests.samples.resolutions;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import com.capgemini.ntc.selenium.pages.features.registration.RegistryPage;
import com.capgemini.ntc.selenium.tests.testSuites.testType.TestsResolution;
import com.capgemini.ntc.selenium.tests.testSuites.testType.TestsSelenium;
import com.example.core.enums.ResolutionEnum;
import com.example.core.tests.core.BaseTest;
import com.example.core.tests.testRunners.ParallelParameterized;

import ru.yandex.qatools.allure.annotations.Features;

@Features("Resolution")
@Category({ TestsResolution.class, TestsSelenium.class })
@RunWith(ParallelParameterized.class)
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
		setResolution(resolutionEnum);

		assertThat(true, is(registryPage.isButtonSubmitDisplayed()));
		TimeUnit.SECONDS.sleep(1); //This is for demo. Do not do it at home 

	}

	@Override
	public void tearDown() {
	}

}
