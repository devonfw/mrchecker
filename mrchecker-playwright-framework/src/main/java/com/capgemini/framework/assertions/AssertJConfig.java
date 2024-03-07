package com.capgemini.framework.assertions;

import com.capgemini.framework.logger.AllureStepLogger;
import org.assertj.core.api.Assertions;

public class AssertJConfig {
	public static void initJAssert() {
		Assertions.setPrintAssertionsDescription(true);
		Assertions.setDescriptionConsumer(description -> AllureStepLogger.check(description.toString()));
	}
	
}
