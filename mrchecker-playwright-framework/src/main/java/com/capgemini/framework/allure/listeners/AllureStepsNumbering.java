package com.capgemini.framework.allure.listeners;

import com.capgemini.framework.logger.Logger;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.TestResult;

/**
 * @author mdzienia
 * This listener is adding numbers to steps for better overview
 */
public class AllureStepsNumbering implements TestLifecycleListener {

    @Override
    public void beforeTestWrite(TestResult testResult) {
        Logger.logInfo("AllureStepsNumbering");
        var stepNumber = 0;

        for(var step: testResult.getSteps()){
            var stepName = step.getName();
            if (!"--Screenshot--".equals(stepName) && !"Preconditions".equals(stepName)) {
                stepNumber++;
                step.setName(stepNumber + " | " + step.getName());
            }
        }
    }
}
