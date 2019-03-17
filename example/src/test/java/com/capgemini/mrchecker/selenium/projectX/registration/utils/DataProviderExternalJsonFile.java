package com.capgemini.mrchecker.selenium.projectX.registration.utils;

import com.capgemini.mrchecker.selenium.core.utils.TestUtils;
import com.capgemini.mrchecker.test.core.utils.datadriven.JsonDriven;

public class DataProviderExternalJsonFile {
	
	private static final String FILENAME = TestUtils
			.getAbsolutePathFor("com/example/demo/cucumber/features/registration/form_data.json");
	
	public static Object[] provide() {
		return JsonDriven.provide(FILENAME, FormDataContainer[].class);
	}
}
