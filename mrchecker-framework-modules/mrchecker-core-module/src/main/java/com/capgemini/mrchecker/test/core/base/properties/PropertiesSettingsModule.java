package com.capgemini.mrchecker.test.core.base.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

@Singleton
public class PropertiesSettingsModule extends AbstractModule {
	
	private static PropertiesSettingsModule instance;
	private String path;
	
	private PropertiesSettingsModule(String path) {
		this.path = path;
		BFLogger.logDebug("Properties settings file path=" + this.path);
	}
	
	@Override
	protected void configure() {
		
		if (!exists(this.path)) {
			addError("Could not configure properties file. Path='" + this.path + "' does not exist");
		}
		
		try {
			Properties properties = loadProperties(path);
			Names.bindProperties(binder(), properties);
		} catch (RuntimeException e) {
			addError("Could not configure properties file", e);
		}
	}
	
	public static PropertiesSettingsModule init() {
		String path = System.getProperty("user.dir") + Paths.get("/src/resources/settings.properties");
		return PropertiesSettingsModule.init(path);
	}
	
	public static PropertiesSettingsModule init(String path) {
		if (instance == null) {
			synchronized (PropertiesSettingsModule.class) {
				if (instance == null) {
					instance = new PropertiesSettingsModule(path);
				}
			}
		}
		return instance;
	}
	
	public static void delInstance() {
		PropertiesSettingsModule.instance = null;
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
