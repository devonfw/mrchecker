package com.capgemini.ntc.selenium.core.tests.samples.resolutions;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import com.example.selenium.pages.features.registration.RegistryPage;
import com.capgemini.ntc.selenium.core.testsuites.testType.TestsResolution;
import com.capgemini.ntc.selenium.core.testsuites.testType.TestsSelenium;
import com.capgemini.ntc.test.core.enums.ResolutionEnum;
import com.capgemini.ntc.test.core.tests.core.BaseTest;
import com.capgemini.ntc.test.core.tests.testRunners.ParallelParameterized;

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
