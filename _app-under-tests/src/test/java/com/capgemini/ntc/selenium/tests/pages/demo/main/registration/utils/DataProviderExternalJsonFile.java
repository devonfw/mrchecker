package com.capgemini.ntc.selenium.tests.tests.pages.demo.main.registration.utils;

import com.example.core.tests.TestUtils;
import com.example.core.tests.utils.FormDataContainer;
import com.example.core.utils.datadriven.JsonDriven;

public class DataProviderExternalJsonFile {

	private static final String FILENAME = TestUtils
			.getAbsolutePathFor("com/example/demo/cucumber/features/registration/form_data.json");

	public static Object[] provide() {
		return JsonDriven.provide(FILENAME, FormDataContainer[].class);
	}
}
