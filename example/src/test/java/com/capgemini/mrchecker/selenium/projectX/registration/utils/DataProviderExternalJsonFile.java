package com.capgemini.mrchecker.selenium.projectX.registration.utils;

import java.util.Arrays;
import java.util.stream.Stream;

import com.capgemini.mrchecker.selenium.core.utils.TestUtils;
import com.capgemini.mrchecker.test.core.utils.datadriven.JsonDriven;

public class DataProviderExternalJsonFile {
	
	private static final String FILENAME = TestUtils
			.getAbsolutePathFor("com/example/demo/cucumber/features/registration/form_data.json");
	
	public static Stream<FormDataContainer> provide() {
		return Arrays.stream(JsonDriven.provide(FILENAME, FormDataContainer[].class));
	}
}
