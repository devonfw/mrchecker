package com.capgemini.framework.playwright;

import com.capgemini.framework.exceptions.InputDataException;
import com.capgemini.framework.logger.AllureStepLogger;
import com.capgemini.framework.logger.Logger;
import com.microsoft.playwright.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.Objects.isNull;

public abstract class PlaywrightFactory {
	
	private static final ThreadLocal<Playwright>        tlPlaywright        = new ThreadLocal<>();
	private static final ThreadLocal<Browser>           tlBrowser           = new ThreadLocal<>();
	private static final ThreadLocal<BrowserType>       tlBrowserType       = new ThreadLocal<>();
	private static final ThreadLocal<BrowserContext>    tlBrowserContext    = new ThreadLocal<>();
	private static final ThreadLocal<APIRequestContext> tlAPIRequestContext = new ThreadLocal<>();
	private static final ThreadLocal<Page>              tlPage              = new ThreadLocal<>();
	
	public static synchronized void initPlaywright() {
		
		if (!isNull(tlPlaywright.get())) {
			return;
		}
		
		tlPlaywright.set(Playwright.create());
	}
	
	public static synchronized void initPage() {
		if (isNull(tlPage.get())) {
			tlPage.set(getBrowserContext().newPage());
		}
	}
	
	public static void initBrowserContext() {
		initBrowserContext(null);
	}
	
	public static void initBrowserContext(Browser.NewContextOptions browserOptions) {
		initPlaywright();
		initBrowser();
		
		if (!isNull(tlBrowserContext.get())) {
			return;
		}
		
		Browser.NewContextOptions options;
		
		if (!isNull(browserOptions)) {
			options = browserOptions;
		}
		else {
			
		    options = new Browser.NewContextOptions();
		}
		
		options.setViewportSize(PlaywrightConfig.browserWidth, PlaywrightConfig.browserHeight)
				.setRecordVideoSize(PlaywrightConfig.browserWidth, PlaywrightConfig.browserHeight)
				.setIgnoreHTTPSErrors(PlaywrightConfig.ignoreHttpsErrors);
		
		if (PlaywrightConfig.videoRecording) {
			options.setRecordVideoDir(Paths.get("target/videos/"));
		}

		tlBrowserContext.set(tlBrowser.get()
				.newContext(options));
		
		tlBrowserContext.get()
				.setDefaultTimeout(PlaywrightConfig.defaultTimeout);
		
		if (PlaywrightConfig.tracing) {
			tlBrowserContext.get()
					.tracing()
					.start(new Tracing.StartOptions().setScreenshots(true)
							.setSnapshots(true));
		}
		
	}
	
	public static void initBrowser() {
		initPlaywright();
		
		if (!isNull(tlBrowser.get())) {
			return;
		}
		
		if ("chrome".equalsIgnoreCase(PlaywrightConfig.browser) || "msedge".equalsIgnoreCase(PlaywrightConfig.browser) || "chromium".equalsIgnoreCase(PlaywrightConfig.browser)) {
			tlBrowserType.set(tlPlaywright.get()
					.chromium());
		} else if ("webkit".equalsIgnoreCase(PlaywrightConfig.browser)) {
			tlBrowserType.set(tlPlaywright.get()
					.webkit());
		} else if ("firefox".equalsIgnoreCase(PlaywrightConfig.browser)) {
			tlBrowserType.set(tlPlaywright.get()
					.firefox());
		}
		tlBrowser.set(tlBrowserType.get()
				.launch(new BrowserType.LaunchOptions().setChannel(PlaywrightConfig.browser)
						.setHeadless(PlaywrightConfig.headless)
						.setSlowMo(PlaywrightConfig.slowMo)));
		
	}
	
	public static void initAPIRequestContext() {
		if (!isNull(tlAPIRequestContext.get())) {
			return;
		}
		initPlaywright();
		tlAPIRequestContext.set(tlPlaywright.get()
				.request()
				.newContext());
	}
	
	public static synchronized Page getPage() {
		initBrowserContext();
		
		if (isNull(tlPage.get())) {
			initPage();
		}
		return tlPage.get();
	}
	
	public static synchronized BrowserContext getBrowserContext() {
		initPlaywright();
		initBrowser();
		
		if (isNull(tlBrowserContext.get())) {
			initBrowserContext();
		}
		return tlBrowserContext.get();
	}
	
	public static synchronized APIRequestContext getAPIRequestContext() {
		initPlaywright();
		if (isNull(tlAPIRequestContext.get())) {
			initAPIRequestContext();
		}
		return tlAPIRequestContext.get();
	}
	
	public static void initAPIRequestContext(APIRequest.NewContextOptions newContextOptions) {
		if (!isNull(tlAPIRequestContext.get())) {
			return;
		}
		tlAPIRequestContext.set(tlPlaywright.get()
				.request()
				.newContext(newContextOptions));
	}
	
	public static synchronized boolean isBrowserContextPresent() {
		return !isNull(tlBrowserContext.get());
	}
	
	public static void closeBrowser() {
		if (isNull(tlBrowser.get())) {
			return;
		}
		
		tlBrowser.get()
				.close();
		tlBrowser.remove();
		tlBrowserType.remove();
		
	}
	
	public static synchronized void closeBrowserContext(String testClassName) {
		if (isNull(tlBrowserContext.get())) {
			return;
		}
		AllureStepLogger.makeScreenshot("Final Screenshot", true);
		
		if (PlaywrightConfig.tracing) {
			try {
				tlBrowserContext.get()
						.tracing()
						.stop(new Tracing.StopOptions().setPath(Paths.get("target/traces/trace-" + testClassName + ".zip")));
				tlBrowserContext.get()
						.close();
			} catch (PlaywrightException e) {
				Logger.logInfo("For failed test tracing is closed automatically, but our tlBrowserContext variables not");
			} finally {
				tlBrowserContext.remove();
			}
		}
		
		if (PlaywrightConfig.videoRecording) {
			var existingVideoFile = tlPage.get()
					.video()
					.path();
			try {
				Files.move(existingVideoFile, existingVideoFile.resolveSibling(testClassName + ".webm"), REPLACE_EXISTING);
			} catch (IOException e) {
				throw new InputDataException(e.getMessage());
			}
		}
		
		closePage();
	}
	
	public static void closePage() {
		if (isNull(tlPage.get())) {
			return;
		}
		
		tlPage.get()
				.close();
		tlPage.remove();
		
	}
	
	public static synchronized void closeAPIRequestContext() {
		if (isNull(tlAPIRequestContext.get())) {
			return;
		}
		tlAPIRequestContext.get()
				.dispose();
		tlAPIRequestContext.remove();
	}
	
	public static void closePlaywright(String testClassName) {
		if (isNull(tlPlaywright.get())) {
			return;
		}
		closeBrowserContext(testClassName);
		closeAPIRequestContext();
		closeBrowser();
		
		tlPlaywright.get()
				.close();
		tlPlaywright.remove();
	}
}
