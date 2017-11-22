package com.capgemini.ntc.selenium.core.base.runtime;

import java.util.Arrays;
import java.util.Collection;

import com.capgemini.ntc.test.core.base.environment.providers.SpreadsheetEnvironmentService;
import com.capgemini.ntc.test.core.base.runtime.RuntimeParametersImp;

/**
 * This class stores various system properties
 * 
 * @author
 *
 */
public enum SystemRuntimeParameters implements RuntimeParametersImp{
	INSTANCE(SpreadsheetEnvironmentService.INSTANCE);

	public static boolean maintenanceMode = false;
	private int IMPLICITYWAITTIMER = 2; // in seconds

	private String env;
	private String browser;
	private String browserVersion;
	private String os;
	private String seleniumGrid;
	private String maintenanceParam;
	private Collection<Object[]> params;
	private String authenticationType;
	private SpreadsheetEnvironmentService environmentServices;

	private SystemRuntimeParameters(SpreadsheetEnvironmentService environmentServices) {
		this.environmentServices = environmentServices;
		init();
	}


	/**
	 * 
	 * @return browser name, e.g. chrome, firefox
	 */
	public String getBrowser() {
		return browser;
	}

	/**
	 * 
	 * @return browser version, e.g. 2.4
	 */
	public String getBrowserVersion() {
		return browserVersion;
	}

	/**
	 * 
	 * @return operating system, e.g. vista
	 */
	public String getOs() {
		return os;
	}

	public String getSeleniumGrid() {
		return seleniumGrid;
	}

	/**
	 * 
	 * @return true if Selenium grid should be used, false otherwise
	 */
	public boolean isSeleniumGrid() {
		return seleniumGrid != null;
	}

	/**
	 * 
	 * @return maximal wait time of implicit wait
	 */
	public int getImplicityWaitTimer() {
		return IMPLICITYWAITTIMER;
	}

	/**
	 * 
	 * @return collection containing all parameters
	 */
	public Collection<Object[]> getParameters() {
		return params;
	}
	
	/**
	 * 
	 * @return type of authentication; allow options: SSO
	 */
	public String getAuthenticationType() {
		return authenticationType;
	}

	private void init() {
		getParameters();
		setDefaults();
		createParamsCollection();
	}

	@Override
	public void getParameters() {
		env = System.getProperty("env");
		browser = System.getProperty("browser");
		browserVersion = System.getProperty("browserVersion");
		seleniumGrid = System.getProperty("seleniumGrid");
		os = System.getProperty("os");
		maintenanceParam = "";// System.getProperty("maintenance");
		authenticationType = System.getProperty("authenticationType");
	}

	private void setDefaults() {

		if (null != os)
			os = os.toLowerCase();
		if (null != browser) {
			browser = browser.toLowerCase();
			if (browser.equals("ie")) {
				browser = "internet explorer";
			}
			if (null != browserVersion)
				browserVersion = browserVersion.toLowerCase();
		}
		if (null != env) {
			env = env.toUpperCase();
		} else {
			env = "DEV"; // default test environment DEV
		}

		if (null == seleniumGrid || seleniumGrid.equals("")) {
			if (null == browser || browser.equals("")) {
				browser = "chrome";
			}
			if (null == browserVersion || browserVersion.equals("")) {
				browserVersion = "8.0";
			}
			if (null == os || os.equals("")) {
				os = "windows";
			}
		}
		
		if(authenticationType == null){
			authenticationType = "SSO";
		}

		environmentServices.setEnvironment(env);
	}

	private void createParamsCollection() {
		params = Arrays.asList(new Object[][] { { browser, browserVersion, os, seleniumGrid, env, maintenanceMode } });
	}
}
