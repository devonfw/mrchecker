package com.capgemini.framework.environment;

import com.capgemini.framework.playwright.PlaywrightConfig;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author mpabst
 * @author mdzienia
 * @since 2023-03
 */
public class AllureEnvironment {
	static final String FILE_NAME = "environment.properties";
	static final String PATH_TEMP = "target/allure-results/" + FILE_NAME;
	
	final double slowMo = PlaywrightConfig.getSlowMo();
	final String threads = System.getProperty("thread.count", "");
	
	public void write() {
		
		try {
			Files.createDirectories(Path.of(PATH_TEMP).getParent());
		} catch (IOException e) {
			ExceptionUtils.printRootCauseStackTrace(e);
		}
		try (var myWriter = new FileWriter(PATH_TEMP)) {
			myWriter.write("browser = " + PlaywrightConfig.getBrowser()
					+ "\nheadlessBrowser =" + PlaywrightConfig.getHeadless()
					+ "\nenvironment = " + System.getProperty("env", "ENV1")
					+ ((!threads.isEmpty()) ? "\nthreads = " + threads : "")
					+ ((slowMo != 0) ? "\nbrowserSlowMo = " + slowMo + "_millisecond" : "")
			);
		} catch (IOException e) {
			ExceptionUtils.printRootCauseStackTrace(e);
		}
	}
}
