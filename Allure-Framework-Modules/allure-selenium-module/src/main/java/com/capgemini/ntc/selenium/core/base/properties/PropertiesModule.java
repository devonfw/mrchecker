package com.capgemini.ntc.selenium.core.base.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

import com.capgemini.ntc.test.core.base.environment.EnvironmentServiceI;
import com.capgemini.ntc.test.core.base.environment.providers.SpreadsheetEnvironmentService;
import com.capgemini.ntc.test.core.logger.BFLogger;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

@Singleton
public class PropertiesModule extends AbstractModule {
	
	private static PropertiesModule instance;
	private String path;
	
	private PropertiesModule(String path) {
		this.path = path;
		BFLogger.logDebug("Selenium properties file path=" + this.path);
	}
	
	@Override
	protected void configure() {
		
		if (!exists(this.path)) {
			addError("Could not configure selenium properties. Path='" + this.path + "' does not exist");
		}
		
		try {
			Properties properties = loadProperties(path);
			Names.bindProperties(binder(), properties);
		} catch (RuntimeException e) {
			addError("Could not configure selenium properties", e);
		}
	}
	
	public static PropertiesModule init() {
		String path = System.getProperty("user.dir")
				+ Paths.get("/src/resources/selenium.properties"); 
		return PropertiesModule.init(path);
	}
	
	public static PropertiesModule init(String path) {
		if (instance == null) {
			synchronized (PropertiesModule.class) {
				if (instance == null) {
					instance = new PropertiesModule(path);
				}
			}
		}
		return instance;
	}
	
	public static void delInstance() {
		PropertiesModule.instance = null;
	}
	
	private boolean exists(String path) {
		File f = new File(path);
		if (f.exists())
			return true;
		
		return false;
	}
	
	private Properties loadProperties(String path) {
		Properties properties = new Properties();
		InputStream fileInputStream = null;
		
		try {
			fileInputStream = new FileInputStream(path);
			properties.load(fileInputStream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException dontCare) {
				}
			}
		}
		return properties;
	}
	
}
