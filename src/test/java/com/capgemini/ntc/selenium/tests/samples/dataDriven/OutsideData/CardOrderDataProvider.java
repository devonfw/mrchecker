package com.capgemini.ntc.selenium.tests.tests.samples.dataDriven.OutsideData;

import com.example.core.tests.TestUtils;
import com.example.core.tests.utils.MatrixDataContainer;
import com.example.core.utils.datadriven.JsonDriven;

public class CardOrderDataProvider {
	private static final String FILENAME = TestUtils
			.getAbsolutePathFor("com/example/features/registration/form_data.json");

	public static Object[] provide() {
		return JsonDriven.provide(FILENAME, MatrixDataContainer[].class);
	}
}
