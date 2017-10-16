package com.capgemini.ntc.selenium.core.tests.samples.dataDriven.OutsideData;

import com.capgemini.ntc.test.core.tests.TestUtils;
import com.capgemini.ntc.test.core.tests.utils.MatrixDataContainer;
import com.capgemini.ntc.test.core.utils.datadriven.JsonDriven;

public class CardOrderDataProvider {
	private static final String FILENAME = TestUtils
			.getAbsolutePathFor("com/example/features/registration/form_data.json");

	public static Object[] provide() {
		return JsonDriven.provide(FILENAME, MatrixDataContainer[].class);
	}
}
