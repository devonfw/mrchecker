package com.capgemini.ntc.selenium.core.base.properties;

import java.io.*;
import java.util.Properties;

import com.capgemini.ntc.test.core.logger.BFLogger;

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
	}}
	


	Listing 3-18. 	Loading and 	Using db.properties
	
	public class PropertiesModule extends AbstractModule {
		protected void configure() {
			try {
				Properties databaseProperties = loadProperties("db.properties");
				Names.bindProperties(binder(), databaseProperties);
			} catch (RuntimeException e) {
				addError("Could not configure database properties", e);
			}
		}
		
		private static Properties loadProperties(String name) {
			Properties properties = new Properties();
			InputStream is = new Object() {
			}
					.getClass()
					.getEnclosingClass()
					.getResourceAsStream(name);
			try {
				properties.load(is);
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException dontCare) {
					}
				}
			}
			return properties;
		}
	}
	
	public class PropertiesExample {
		@Inject
		public void databaseURL(@Named("db.url") String url) {
			System.out.println(url);
		}
		
		public static void main(String[] args) {
			Injector i = Guice.createInjector(new PropertiesModule());
			i.getInstance(PropertiesExample.class);
		}
}Running this

example prints jdbc:mysql:// localhost/test.
