package com.capgemini.ntc.selenium.core.base.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.capgemini.ntc.test.core.logger.BFLogger;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class PropertiesModule extends AbstractModule {
	
	private String path;
	
	public PropertiesModule() {
		// Default value for settings file
		this.path = getClass().getClassLoader()
				.getResource("")
				.getPath() + "/settings.properties";
	}
	
	public PropertiesModule(String path) {
		this.path = path;
	}
	
	@Override
	protected void configure() {
		BFLogger.logDebug("Selenium properties file path=" + this.path);
		
		if (!exists(this.path)) {
			addError("Could not configure selenium properties");
		}
		
		try {
			Properties properties = loadProperties(path);
			Names.bindProperties(binder(), properties);
		} catch (RuntimeException e) {
			addError("Could not configure selenium properties", e);
		}
	}
	
	protected static boolean exists(String path) {
		File f = new File(path);
		if (f.exists())
			return true;
		
		return false;
	}
	
	protected static Properties loadProperties(String path) {
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
