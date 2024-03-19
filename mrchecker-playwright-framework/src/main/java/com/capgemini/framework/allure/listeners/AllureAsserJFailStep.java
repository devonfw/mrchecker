package com.capgemini.framework.allure.listeners;

import com.capgemini.framework.logger.AllureStepLogger;
import com.capgemini.framework.logger.Logger;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import io.qameta.allure.model.TestResult;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author mdzienia This listener is helping to make steps red in case assertJ will fail with message defined in assertThat().as().withFailMessage() This listener is checking the last step of allure
 * 		step and compares it with failure message, then set on status failed
 */
public class AllureAsserJFailStep implements TestLifecycleListener {
	private final static String KNOWN_BUG_PREFIX = "[KNOWN BUG] ";
	private final static String TEAR_DOWN_STEP_PREFIX = "[TEAR DOWN]";



	@Override
	public void beforeTestWrite(TestResult testResult) {
		Logger.logInfo("AllureAsserJFailStep");
		String currentErrorMessage = testResult.getStatusDetails().getMessage();
		String failedMessage = StringUtils.substringBetween(currentErrorMessage.replace(KNOWN_BUG_PREFIX, ""), "[", "]");
		String assertMessage = AllureStepLogger.KEYWORD_CHECK + failedMessage;

		if (!(Status.FAILED.equals(testResult.getStatus()) || Status.SKIPPED.equals(testResult.getStatus())) || testResult.getSteps().isEmpty()) {
			return;
		}
		List<StepResult> steps = testResult.getSteps();
		for (int i = steps.size() - 1; i >= 0; i--) {
			StepResult step = steps.get(i);
			// Case for all action steps and preconditions
			if (!step.getName().contains(TEAR_DOWN_STEP_PREFIX)) {
				if (updateFailedStepsStatus(step, assertMessage)) {
					break;
				}
			} else {
				// case for teardown steps
				if (step.getName().contains(TEAR_DOWN_STEP_PREFIX) && Status.FAILED.equals(step.getStatus())) {
					String tdCurrentErrorMessage = step.getStatusDetails().getMessage();
					String tdFailedMessage = StringUtils.substringBetween(tdCurrentErrorMessage.replace(KNOWN_BUG_PREFIX, ""), "[", "]");
					String tdAssertMessage = AllureStepLogger.KEYWORD_CHECK + tdFailedMessage;
					updateFailedStepsStatus(step, tdAssertMessage);
				}
			}
		}
	}


	private Boolean updateFailedStepsStatus(StepResult rootStep, String assertMessage) {
		if (rootStep.getSteps().isEmpty() && rootStep.getName().equals(assertMessage)) {
			rootStep.setStatus(Status.FAILED);
			return true;
		} else {
			List<StepResult> steps = rootStep.getSteps();
			for (int i = steps.size() - 1; i >= 0; i--) {
				StepResult step = steps.get(i);
				if (updateFailedStepsStatus(step, assertMessage)) {
					return true;
				}
			}
			return false;
		}
	}
}
