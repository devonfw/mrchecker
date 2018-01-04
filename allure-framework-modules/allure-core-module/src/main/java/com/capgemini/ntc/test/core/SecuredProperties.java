package com.capgemini.ntc.test.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

import com.capgemini.ntc.test.core.logger.BFLogger;

public class SecuredProperties {
	
	private static Properties properties;
	
	static {
		
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("secretData"));
			String password = scanner.next();
			encryptor.setPassword(password);
			scanner.close();
			
		} catch (FileNotFoundException e) {
			BFLogger.logError("secretData file not found loading error");
			BFLogger.logError(e.getMessage());
		}
		
		properties = new EncryptableProperties(encryptor);
		
		try {
			properties.load(new FileInputStream("application.properties"));
		} catch (IOException e) {
			BFLogger.logError("application.properties loading error");
			BFLogger.logEnv(e.getMessage());
		}
	}
	
	public static String get(String key) {
		return properties.getProperty(key);
	}
	
}
