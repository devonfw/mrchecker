package com.capgemini.mrchecker.common.allure.utils;

import com.capgemini.mrchecker.test.core.logger.BFLogger;

import io.qameta.allure.Step;

public class StepLogger {
	@Step("{step}")
	public static void step(String step) {
		BFLogger.logInfo(step);
	}
	
	@Step("[INFO] {step}")
	public static void stepInfo(String step) {
		BFLogger.logInfo(step);
	}
	

}
