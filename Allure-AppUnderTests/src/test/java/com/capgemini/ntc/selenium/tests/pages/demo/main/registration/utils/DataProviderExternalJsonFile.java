package com.capgemini.ntc.selenium.tests.pages.demo.main.registration.utils;

import com.capgemini.ntc.selenium.core.utils.TestUtils;
import com.capgemini.ntc.test.core.utils.datadriven.JsonDriven;

public class DataProviderExternalJsonFile {

	private static final String FILENAME = TestUtils
			.getAbsolutePathFor("com/example/demo/cucumber/features/registration/form_data.json");

	public static Object[] provide() {
		return JsonDriven.provide(FILENAME, FormDataContainer[].class);
	}
}
