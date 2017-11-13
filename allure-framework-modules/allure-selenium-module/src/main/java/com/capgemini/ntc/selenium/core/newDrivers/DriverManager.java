package com.capgemini.ntc.selenium.core.newDrivers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.picocontainer.Startable;

import com.capgemini.ntc.selenium.core.enums.ResolutionEnum;
import com.capgemini.ntc.selenium.core.exceptions.BFSeleniumGridNotConnectedException;
import com.capgemini.ntc.selenium.core.utils.ResolutionUtils;
import com.capgemini.ntc.test.core.base.environments.ParametersManager;
import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.test.core.properties.PropertiesManager;


public class DriverManager implements Startable {

	private final static PropertiesManager propMan = new PropertiesManager();
	private static ThreadLocal<INewWebDriver> drivers = new ThreadLocal<INewWebDriver>();
	private static final ResolutionEnum DEFAULT_RESOLUTION = ResolutionEnum.w1600;
	private static ParametersManager parametersManager;

	public DriverManager(ParametersManager parametersManager) {
		DriverManager.parametersManager = parametersManager;
		
	}
	
	@Override
	public void start() {
		DriverManager.getDriver();
		
		
	}

	@Override
	public void stop() {
		try {
			closeDriver();
			BFLogger.logDebug("Closing Driver in stop()");
			BFLogger.logInfo(String.format("All clicks took %.2fs", 1.0 * NewRemoteWebElement.dropClickTimer() / 1000));
		} catch (Exception e) {
		}
	}
	
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		try {
			closeDriver();
			BFLogger.logDebug("Closing Driver in finalize()");
		} catch (Exception e) {
		}

	}

	public static INewWebDriver getDriver() {
		INewWebDriver driver = drivers.get();
		if (driver == null) {
			driver = createDriver();
			drivers.set(driver);
		}
		return driver;
	}

	public static void closeDriver() {
		INewWebDriver driver = drivers.get();
		if (driver == null) {
			BFLogger.logDebug("closeDriver() was called but there was no driver for this thread.");
		} else {
			try {
				BFLogger.logDebug("Closing WebDriver for this thread. " + parametersManager.parameters().getBrowser());
				driver.quit();
			} catch (UnreachableBrowserException e) {
				BFLogger.logDebug("Ooops! Something went wrong while closing the driver: ");
				e.printStackTrace();
			} finally {
				driver = null;
				drivers.remove();
			}
		}
	}

	/**
	 * Method sets desired 'driver' depends on chosen parameters
	 */
	private static INewWebDriver createDriver() {
		BFLogger.logDebug("Creating new " + parametersManager.parameters().getBrowser() + " WebDriver.");
		INewWebDriver driver;
		if (parametersManager.parameters().isSeleniumGrid()) {
			driver = setupGrid();
		} else {
			driver = setupBrowser();
		}
		driver.manage().timeouts().implicitlyWait(parametersManager.parameters().getImplicityWaitTimer(), TimeUnit.SECONDS);
		ResolutionUtils.setResolution(driver, DriverManager.DEFAULT_RESOLUTION);
		NewRemoteWebElement.setClickTimer();
		return driver;
	}

	/**
	 * Method sets Selenium Grid
	 */
	private static INewWebDriver setupGrid() {
		try {
			return Driver.SELENIUMGRID.getDriver();
		} catch (WebDriverException e) {
			throw new BFSeleniumGridNotConnectedException(e);
		}
	}

	/**
	 * Method sets desired 'driver' depends on chosen parameters
	 */
	private static INewWebDriver setupBrowser() {
		String browser = parametersManager.parameters().getBrowser();
		switch (browser) {
			case "chrome":
				return Driver.CHROME.getDriver();
			case "firefox":
				return Driver.FIREFOX.getDriver();
			case "internet explorer":
				return Driver.IE.getDriver();
			case "phantomjs":
				return Driver.PHANTOMJS.getDriver();
			default:
				throw new RuntimeException("Unable to setup [" + browser + "] browser. Browser not recognized.");
		}
	}

	private enum Driver {

		CHROME {
			@Override
			public INewWebDriver getDriver() {
				String browserPath = propMan.getProperty("chrome");
				System.setProperty("webdriver.chrome.driver", browserPath);
				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("download.default_directory", System.getProperty("java.io.tmpdir"));
				chromePrefs.put("profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", chromePrefs);
				options.addArguments("--test-type");
				DesiredCapabilities cap = DesiredCapabilities.chrome();
				cap.setCapability(ChromeOptions.CAPABILITY, options);

				INewWebDriver driver = new NewChromeDriver(cap);
				return driver;
			}

		},
		PHANTOMJS {
			@Override
			public INewWebDriver getDriver() {
				String browserPath = propMan.getProperty("phantomjs");
				ArrayList<String> cliArgsCap = new ArrayList<String>();
				cliArgsCap.add("--web-security=false");
				cliArgsCap.add("--ssl-protocol=any");
				cliArgsCap.add("--ignore-ssl-errors=true");
				DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
				capabilities.setCapability("takesScreenshot", true);
				capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
				capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS,
						new String[] { "--logLevel=2" });
				capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, browserPath);
				return new NewPhantomJSDriver(capabilities);
			}
		},
		FIREFOX {
			@Override
			public INewWebDriver getDriver() {
				FirefoxProfile profile = new FirefoxProfile();
				String browserPath = propMan.getProperty("firefox");
				System.setProperty("webdriver.gecko.driver", browserPath);
				profile.setPreference("webdriver.firefox.marionette", true);
				profile.setPreference("browser.download.folderlist", 2);
				profile.setPreference("browser.helperapps.neverAsk.saveToDisk",
						"text/comma-separated-values, application/vnd.ms-excel, application/msword, application/csv, application/ris, text/csv, image/png, application/pdf, text/html, text/plain, application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream");
				profile.setPreference("browser.download.manager.showWhenStarting", false);
				profile.setPreference("browser.download.useDownloadDir", true);
				profile.setPreference("browser.helperApps.alwaysAsk.force", false);
				profile.setPreference("browser.download.dir", System.getProperty("java.io.tmpdir"));
				return new NewFirefoxDriver(profile);
			}
		},
		IE {
			@Override
			public INewWebDriver getDriver() {
				String browserPath = propMan.getProperty("ie");
				System.setProperty("webdriver.ie.driver", browserPath);
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				// Due to some issues with IE11 this line must be commented
				// ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
				// true);
				return new NewInternetExplorerDriver(ieCapabilities);
			}

		},
		SAFARI {
		},
		SELENIUMGRID {

			@Override
			public INewWebDriver getDriver() {
				final String SELENIUM_GRID_URL = parametersManager.parameters().getSeleniumGrid();
				BFLogger.logDebug("Connecting to the selenium grid: " + SELENIUM_GRID_URL);
				DesiredCapabilities capabilities = new DesiredCapabilities();
				String operatingSystem = DriverManager.parametersManager.parameters().getOs();
				// TODO add others os's
				switch (operatingSystem) {
					case "windows":
						capabilities.setPlatform(Platform.WINDOWS);
						break;
					case "vista":
						capabilities.setPlatform(Platform.VISTA);
						break;
					case "mac":
						capabilities.setPlatform(Platform.MAC);
						break;
				}

				capabilities.setVersion(parametersManager.parameters().getBrowserVersion());
				capabilities.setBrowserName(parametersManager.parameters().getBrowser());
				NewRemoteWebDriver newRemoteWebDriver = null;
				try {
					newRemoteWebDriver = new NewRemoteWebDriver(new URL(SELENIUM_GRID_URL), capabilities);
				} catch (MalformedURLException e) {
					e.printStackTrace();
					System.out.println("Unable to find selenium grid URL: " + SELENIUM_GRID_URL);
				}
				return newRemoteWebDriver;
			}
		};

		public INewWebDriver getDriver() {
			return null;
		}

	}


}
