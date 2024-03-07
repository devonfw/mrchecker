package com.capgemini.frameworkExamples;

import com.capgemini.framework.enums.PrjEpics;
import com.capgemini.framework.enums.User;
import com.capgemini.framework.environment.typesafeconfig.EnvironmentConfig;
import com.capgemini.framework.logger.AllureStepLogger;
import com.capgemini.framework.tags.Status;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Epic(PrjEpics.EXAMPLES)
@Story("Password encoding example")
public class PasswordEncodingExample {
	


	@Description("Password is Encoded only in case ENV2")
	@Test
	@Tag(Status.DONE)
	void passwordEncoding_test() throws IOException {
		//  Prerequisite: Set execution to ENV2 because there the password is encoded ENC(XyxQ4vSnXoIjH9H20JhwQQ==)
		//  In project life always remember:
		//  File src/resources/secretData should never be stored in git.
		//  It should be distributed to every test developer other way and stored in that place.
		
		
		// CSV environments
		AllureStepLogger.info("CSV env");
		AllureStepLogger.info("This is encoded password:" + User.USER_ADMIN_QA.getLogin());
		String userPasswordEncoded = User.USER_ADMIN_QA.getPassword();
		AllureStepLogger.info("This is encoded password:" + userPasswordEncoded);
		
		// HOCON environments
		AllureStepLogger.info("HoCON env");
		AllureStepLogger.info("This is login:" + EnvironmentConfig.getUserQADemoLogin());
		AllureStepLogger.info("This is encoded password:" + EnvironmentConfig.getUserQADemoPassword());
		
	}
}
