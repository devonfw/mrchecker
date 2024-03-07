package com.capgemini.framework.environment.typesafeconfig;

import com.capgemini.framework.environment.encryption.IDataEncryptionService;
import com.capgemini.framework.environment.encryption.providers.DataEncryptionService;
import com.capgemini.framework.logger.Logger;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.Optional;

/**
 * Environment variables can be defined with HOCON (Human-Optimized Config Object Notation) library
 * Supports files in three formats: Java properties, JSON, and a human-friendly JSON superset
 * https://github.com/lightbend/config/blob/main/HOCON.md
 */
public class EnvironmentConfig {
	
	// File name with environments variable stored on classpath (see pom.xml surefire plugin configuration)
	private static final String                           ENVIRONMENT_FILENAME = "environments/environments.conf";
	private static final String                           ENVIRONMENT_NAME  = System.getProperty("env", "ENV1");
	private static       Optional<IDataEncryptionService> encryptionService;
	private static final Config                           config;

	public static final String APP_CONFIG = "app-config.";

	static {
		Logger.logEnv("RUN PARAMETER: Selected Environment: " + ENVIRONMENT_NAME);
		
		DataEncryptionService.init();
		encryptionService = Optional.ofNullable(DataEncryptionService.getInstance());
		
		config = ConfigFactory.load(ENVIRONMENT_FILENAME);
		var envPath = APP_CONFIG + ENVIRONMENT_NAME;

	}
	
	public static String getUserQADemoLogin() {
		return config.getString(APP_CONFIG + ENVIRONMENT_NAME + ".QA_USER.LOGIN");

	}

	public static String getUserQADemoPassword() {
		var optionalEncryptedValue = config.getString(APP_CONFIG + ENVIRONMENT_NAME + ".QA_USER.PASSWORD");
		
		return optionalDecrypt(optionalEncryptedValue); // if your password is encoded with Jasyp
	}
	
	private static String optionalDecrypt(String value) {
		
		var  cos = encryptionService.isPresent();
		var cos2 =encryptionService.get();
		if (encryptionService.isPresent() && encryptionService.get()
				.isEncrypted(value)) {
			value = encryptionService.get()
					.decrypt(value);
		}
		
		return value;
	}
	
	public static String getDemoQAStudentRegistrationForm() {
		return config.getString(APP_CONFIG + ENVIRONMENT_NAME + ".StudentRegistrationFormUrl");
	}
}
