package com.capgemini.mrchecker.webapi.core;

import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.ITestExecutionObserver;
import com.capgemini.mrchecker.test.core.ITestObserver;
import com.capgemini.mrchecker.test.core.ModuleType;
import com.capgemini.mrchecker.test.core.analytics.IAnalytics;
import com.capgemini.mrchecker.test.core.base.properties.PropertiesSettingsModule;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.webapi.core.base.driver.DriverManager;
import com.capgemini.mrchecker.webapi.core.base.properties.PropertiesFileSettings;
import com.capgemini.mrchecker.webapi.core.base.runtime.RuntimeParameters;
import com.capgemini.mrchecker.webapi.soap.SoapMessageGenerator;
import com.google.inject.Guice;
import com.jamesmurty.utils.XMLBuilder;
import org.xml.sax.SAXException;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.TransformerException;
import java.io.IOException;

abstract public class BasePageWebAPI implements ITestObserver, IWebAPI {

	public final static  String                 analitycsCategoryName = "WebAPI-Module";
	private final static PropertiesFileSettings propertiesFileSettings;
	private final static IAnalytics             analytics;
	private static DriverManager driver = null;
	private static final ITestExecutionObserver TEST_EXECUTION_OBSERVER = BaseTest.BASE_TEST_EXECUTION_OBSERVER;
	private boolean isInitialized;

	static {
		// Get analytics instance created in BaseTets
		analytics = BaseTest.getAnalytics();

		// Get and then set properties information from selenium.settings file
		propertiesFileSettings = setPropertiesSettings();

		// Read System or maven parameters
		setRuntimeParametersWebApi();

		// Read Environment variables either from environmnets.csv or any other input data.
		setEnvironmetInstance();
	}

	public BasePageWebAPI() {
		this(getDriver());
	}

	public BasePageWebAPI(DriverManager driver) {
	}

	public static IAnalytics getAnalytics() {
		return BasePageWebAPI.analytics;
	}

	public static DriverManager getDriver() {
		if (BasePageWebAPI.driver == null) {
			// Create module driver
			BasePageWebAPI.driver = new DriverManager(propertiesFileSettings);
		}
		return BasePageWebAPI.driver;
	}

	private static PropertiesFileSettings setPropertiesSettings() {
		// Get and then set properties information from settings.properties file
		PropertiesFileSettings propertiesFileSettings = Guice.createInjector(PropertiesSettingsModule.init())
				.getInstance(PropertiesFileSettings.class);
		return propertiesFileSettings;
	}

	private static void setRuntimeParametersWebApi() {
		// Read System or maven parameters
		BFLogger.logDebug(java.util.Arrays.asList(RuntimeParameters.values())
				.toString());

	}

	private static void setEnvironmetInstance() {
		/*
		 * Environment variables either from environmnets.csv or any other input data. For now there is no properties
		 * settings file for Selenium module. In future, please have a look on Core Module IEnvironmentService
		 * environmetInstance = Guice.createInjector(new EnvironmentModule()) .getInstance(IEnvironmentService.class);
		 */

	}

	public void initialize() {
		TEST_EXECUTION_OBSERVER.addObserver(this);
		this.isInitialized = true;
	}

	@Override
	public void onTestFailure() {
		BFLogger.logDebug("BasePage.onTestFailure    " + this.getClass()
				.getSimpleName());
	}

	@Override
	public void onTestSuccess() {
		// All actions needed while test method is success
		BFLogger.logDebug("BasePage.onTestSuccess    " + this.getClass()
				.getSimpleName());
	}

	@Override
	public void onTestFinish() {
		// All actions needed while test class is finishing
		BFLogger.logDebug("BasePage.onTestFinish   " + this.getClass()
				.getSimpleName());
		TEST_EXECUTION_OBSERVER.removeObserver(this);
	}

	@Override
	public void onTestClassFinish() {
		BFLogger.logDebug("BasePage.onTestClassFinish   " + this.getClass()
				.getSimpleName());
		BFLogger.logDebug("driver:" + getDriver().toString());
	}

	@Override
	public ModuleType getModuleType() {
		return ModuleType.WEBAPI;
	}

	public class SOAPTemplate {

		private XMLBuilder xmlBody;

		/*
		 * SOAP response built from Java code
		 */
		public SOAPTemplate(String root) {
			setRoot(root);
		}

		/**
		 * @return Generate SOAP request in String format
		 */
		public String getMessage() {
			String message = "";
			try {
				SOAPMessage soapMessage = SoapMessageGenerator.createSOAPmessage(this.getRoot()
						.asString());
				message = SoapMessageGenerator.printSoapMessage(soapMessage);
			} catch (SOAPException | SAXException | IOException | ParserConfigurationException | TransformerException e) {
				new Exception(e);
			}
			return message;
		}

		/**
		 * @return Root XML structure
		 */
		public XMLBuilder getRoot() {
			return xmlBody;
		}

		/*
		 * ----------------------------------
		 * Any handy actions after this point
		 * ----------------------------------
		 */
		private void setRoot(String nodeName) {
			try {
				this.xmlBody = XMLBuilder.create(nodeName);
			} catch (ParserConfigurationException | FactoryConfigurationError e) {
				new Exception(e);
			}
		}

		/*
		 * Set up an attribute for root
		 */
		public void addAttributeToRoot(String name, String value) {
			this.xmlBody.attribute(name, value);
		}
	}

}
