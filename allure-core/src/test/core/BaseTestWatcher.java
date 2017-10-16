package com.example.core.tests.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.MultipleFailureException;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;

import com.example.core.logger.BFLogger;
import com.example.selenium.core.newDrivers.DriverManager;

import ru.yandex.qatools.allure.annotations.Attachment;

public class BaseTestWatcher extends TestWatcher {
	private BaseTest baseTest;

	public BaseTestWatcher(BaseTest baseTest) {
		this.baseTest = baseTest;
	}

	private long iStart;

	@Override
	public Statement apply(final Statement base, final Description description) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				List<Throwable> errors = new ArrayList<Throwable>();

				try {
					starting(description);
					base.evaluate();
					succeeded(description);
				} catch (org.junit.internal.AssumptionViolatedException e) {
					errors.add(e);
					skippedQuietly(e, description, errors);
				} catch (Throwable e) {
					errors.add(e);
					failed(e, description);
				} finally {
					finished(description);
				}

				MultipleFailureException.assertEmpty(errors);
			}
		};
	}

	@SuppressWarnings("deprecation")
	private void skippedQuietly(org.junit.internal.AssumptionViolatedException e, Description description,
			List<Throwable> errors) {
		try {
			if (e instanceof AssumptionViolatedException) {
				skipped((AssumptionViolatedException) e, description);
			} else {
				skipped(e, description);
			}
		} catch (Throwable e1) {
			errors.add(e1);
		}
	}

	@Override
	protected void starting(Description description) {
		BFLogger.RestrictedMethods.startSeparateLog(); // start logging for single test
		BFLogger.logInfo(description.getDisplayName() + " STARTED.");
		this.iStart = System.currentTimeMillis(); // start timing
		baseTest.setUp(); // Executed as a Before for each test
	}

	@Override
	protected void finished(Description description) {
		this.iStart = System.currentTimeMillis() - this.iStart; // end timing
		// BFLogger.logInfo(description.getDisplayName() + " duration: " + this.iStart);
		baseTest.tearDown(); // Executed as a After for each test
		makeLogForTest(); // Finish logging and add created log as an Allure attachment

	}

	@Override
	protected void succeeded(Description description) {
		this.iStart = System.currentTimeMillis() - this.iStart; // end timing
		printTimeExecutionLog(description);
		BFLogger.logInfo(description.getDisplayName() + " PASSED.");
	}

	private void printTimeExecutionLog(Description description) {
		BFLogger.logInfo(description.getDisplayName()
				+ String.format(" duration: %1.2f min", (float) this.iStart / (60 * 1000)));
	}

	@Override
	protected void failed(Throwable e, Description description) {
		this.iStart = System.currentTimeMillis() - this.iStart; // end timing
		printTimeExecutionLog(description);
		BFLogger.logInfo(description.getDisplayName() + " FAILED.");
		makeScreenshotOnFailure();
		makeSourcePageOnFailure();

		// uncommment for manual/demo tests
		// saveScreenshot(description);
		// savePageSource(description);
	}

	@Attachment("Screenshot on failure")
	public byte[] makeScreenshotOnFailure() {
		byte[] screenshot = null;
		try {
			screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
		} catch (UnhandledAlertException e) {
			BFLogger.logDebug("[makeScreenshotOnFailure] Unable to take screenshot.");
		}
		return screenshot;
	}

	@Attachment("Source Page on failure")
	public String makeSourcePageOnFailure() {
		return DriverManager.getDriver().getPageSource();
	}

	@Attachment("Log file")
	public String makeLogForTest() {
		return BFLogger.RestrictedMethods.dumpSeparateLog();
	}

	private File getDestinationFile(String directoryName, String testName, String fileType) {
		String userDirectory = "./test-output/" + directoryName; // TODO: Setup correct directory where we will be
																	// saving tests reports
		File directory = new File(userDirectory);
		if (!directory.exists()) {
			directory.mkdir();
		}
		String fileName = testName + getCurrentTime() + "." + fileType;
		String absoluteFileName = userDirectory + "/" + fileName;
		return new File(absoluteFileName);
	}

	private String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("__dd-MM-yyyy__HH-mm-ss");
		Date date = new Date();
		return sdf.format(date);
	}

	private void saveScreenshot(Description description) {
		TakesScreenshot takesScreenshot = (TakesScreenshot) DriverManager.getDriver();
		File screenshotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = getDestinationFile(description.getClassName(), description.getDisplayName(), "png");
		try {
			FileUtils.copyFile(screenshotFile, destFile);
			BFLogger.logDebug("Screenshot saved in: " + destFile.getPath());
		} catch (IOException ioe) {
			BFLogger.logDebug("Screenshot could not be saved: " + ioe.getMessage());
			throw new RuntimeException(ioe);
		}
	}

	private void savePageSource(Description description) {
		String pageSource = DriverManager.getDriver().getPageSource();
		File destFile = getDestinationFile(description.getClassName(), description.getDisplayName(), "html");
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(destFile, false)))) {
			out.println(pageSource);
			BFLogger.logDebug("Page source saved in: " + destFile.getPath());
		} catch (IOException e) {
			BFLogger.logDebug("Page source could not be saved: " + e.getMessage());
		}
	}

}
