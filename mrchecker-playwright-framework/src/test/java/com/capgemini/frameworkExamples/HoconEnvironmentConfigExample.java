package com.capgemini.frameworkExamples;

import com.capgemini.framework.enums.PrjEpics;
import com.capgemini.framework.environment.typesafeconfig.EnvironmentConfig;
import com.capgemini.framework.logger.AllureStepLogger;
import com.capgemini.framework.tags.Status;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;



@Epic(PrjEpics.EXAMPLES)
@Story("Hacon project configuration example")
public class HoconEnvironmentConfigExample {
	
	
	@Description("Environment variables are stored in modern way -> see https://github.com/lightbend/config")
	@Test
	@Tag(Status.DONE)
	void hoconEnvironmentConfig_test() {
		
		AllureStepLogger.info("This is login:" + EnvironmentConfig.getUserQADemoLogin());
		AllureStepLogger.info("This is encoded password:" + EnvironmentConfig.getUserQADemoPassword());
		
	}
}
