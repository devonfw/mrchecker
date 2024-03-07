package com.capgemini.framework.playwright;

import com.capgemini.framework.exceptions.InputDataException;
import com.capgemini.framework.logger.Logger;

import java.io.IOException;
import java.util.Properties;

public class PlaywrightConfig {
	
	protected static final Properties pwProperties = new Properties();
	protected static       boolean    tracing;
	protected static       boolean    videoRecording;
	protected static       Boolean    headless;
	protected static       Double     slowMo;
	protected static       String     browser;
	protected static       int        browserWidth;
	protected static       int        browserHeight;
	protected static       double     defaultTimeout;
	protected static       boolean    ignoreHttpsErrors;
	
	public static void init() {
		loadRunParamsFromPropertiesFile();
		setRunParams();
	}
	
	/**
	 * Takes playwright.properties file and cli variables. CLI variable is taken if defined, in other case variable from properties file.
	 */
	static void setRunParams() {
		browser = setVariable("browser", FileProperties.PW_BROWSER);
		headless = Boolean.parseBoolean(setVariable("headless", FileProperties.PW_HEADLESS));
		slowMo = Double.parseDouble(setVariable("slowMo", FileProperties.PW_SLOW_MOTION));
		browserWidth = Integer.parseInt(setVariable("browserWidth", FileProperties.PW_BROWSER_WIDTH));
		browserHeight = Integer.parseInt(setVariable("browserHeight", FileProperties.PW_BROWSER_HEIGHT));
		tracing = Boolean.parseBoolean(setVariable("tracing", FileProperties.PW_TRACING));
		videoRecording = Boolean.parseBoolean(setVariable("video", FileProperties.PW_VIDEO_RECORDING));
		defaultTimeout = Double.parseDouble(setVariable("defaultTimeout", FileProperties.PW_DEFAULT_TIMEOUT));
		ignoreHttpsErrors = Boolean.parseBoolean(setVariable("ignoreHttpsErrors", FileProperties.PW_IGNORE_HTTPS_ERRORS));
	}
	
	public static Boolean getHeadless() {
		return headless;
	}
	
	public static Double getSlowMo() {
		return slowMo;
	}
	
	public static String getBrowser() {
		return browser;
	}
	
	private static String setVariable(String cliPropName, String filePropName) {
		Logger.logEnv("PLAYWRIGHT PARAMETER: " + cliPropName + " = " + pwProperties.getProperty(filePropName));
		return System.getProperty(cliPropName, pwProperties.getProperty(filePropName));
	}
	
	private static void loadRunParamsFromPropertiesFile() {
		var loader = Thread.currentThread()
				.getContextClassLoader();
		var playwrightPropertiesFile = "playwright.properties";
		try (var propertiesFile = loader.getResourceAsStream(playwrightPropertiesFile)) {
			pwProperties.load(propertiesFile);
		} catch (IOException e) {
			Logger.logInfo("Playwright Properties file not found: " + playwrightPropertiesFile);
			throw new InputDataException("File not found test/resources/playwright.properties");
		}
	}
	
	private static class FileProperties {
		
		public static final  String PW_DEFAULT_TIMEOUT     = "playwright.defaultTimeout";
		private static final String PW_HEADLESS            = "playwright.headless";
		private static final String PW_SLOW_MOTION         = "playwright.slow.motion";
		private static final String PW_BROWSER             = "playwright.browser";
		private static final String PW_BROWSER_WIDTH       = "playwright.browserWidth";
		private static final String PW_BROWSER_HEIGHT      = "playwright.browserHeight";
		private static final String PW_TRACING             = "playwright.tracing";
		private static final String PW_VIDEO_RECORDING     = "playwright.videoRecording";
		private static final String PW_IGNORE_HTTPS_ERRORS = "playwright.ignore.https.errors";
	}
}