package com.capgemini.mrchecker.selenium.features.samples.resolutions;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.enums.ResolutionEnum;
import com.capgemini.mrchecker.selenium.core.utils.ResolutionUtils;
import com.capgemini.mrchecker.selenium.pages.projectX.registration.RegistryPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

import io.qameta.allure.Feature;

@Feature("Resolution")
@Disabled("page not existent on the Web")
@Tag("ResolutionTest")
public class ResolutionTest extends BaseTest {
	private RegistryPage registryPage;
	
	private static Stream<ResolutionEnum> getResolutions() {
		return Stream.of(
				ResolutionEnum.w768,
				ResolutionEnum.w960,
				ResolutionEnum.w1920);
	}
	
	@Override
	public void setUp() {
		registryPage = PageFactory.getPageInstance(RegistryPage.class);
	}
	
	@ParameterizedTest
	@MethodSource("getResolutions")
	public void resolution_test(ResolutionEnum resolutionEnum) throws InterruptedException {
		ResolutionUtils.setResolution(BasePage.getDriver(), resolutionEnum);
		
		assertThat(true, is(registryPage.isButtonSubmitDisplayed()));
		TimeUnit.SECONDS.sleep(1); // This is for demo. Do not do it at home
		
	}
	
}
