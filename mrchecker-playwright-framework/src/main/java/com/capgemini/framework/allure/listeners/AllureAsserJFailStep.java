package com.capgemini.framework.allure.listeners;

import com.capgemini.framework.logger.Logger;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import org.apache.commons.lang3.StringUtils;

/**
 * @author mdzienia This listener is helping to make steps red in case assertJ will fail with message defined in assertThat().as().withFailMessage() This listener is checking the last step of allure
 * 		step and compares it with failure message, then set on status failed
 */
public class AllureAsserJFailStep implements TestLifecycleListener {
	
	@Override
	public void beforeTestWrite(TestResult testResult) {
		Logger.logInfo("AllureAsserJFailStep");
		if (!Status.FAILED.equals(testResult.getStatus()) || testResult.getSteps().isEmpty()) {
			return;
		}
		
		var assertMessage = StringUtils.substringBetween(testResult.getStatusDetails().getMessage(), "[", "]");
		var indexOfLastStep = testResult.getSteps().size() - 1;
		
		if (testResult.getSteps().get(indexOfLastStep).getName().contains(assertMessage)) {
			testResult.getSteps().get(indexOfLastStep).setStatus(Status.FAILED);
		}
	}
}
