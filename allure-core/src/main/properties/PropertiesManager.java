package com.example.core.properties;

import java.io.*;
import java.util.Properties;

import com.example.core.logger.BFLogger;

public class PropertiesManager {
	private final String FILE_URL = "settings.properties";

	/**
	 * Get a property from a given properties file.
	 * 
	 * @param property
	 * @return
	 */
	public String getProperty(final String property) {
		Properties prop = new Properties();

		try (InputStream fileInputStream = new FileInputStream(FILE_URL)) {
			prop.load(fileInputStream);
			String result = prop.getProperty(property);
			if (result != null && !result.equals("")) {
				return result;
			}
		} catch (IOException ex) {
			BFLogger.logError(ex.getMessage());
		}
		return "";
	}

	public boolean exists() {
		File f = new File(FILE_URL);
		if (f.exists())
			return true;

		return false;
	}
}
