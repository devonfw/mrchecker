package com.capgemini.ntc.core.tests.core;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.MultipleFailureException;
import org.junit.runners.model.Statement;

import com.capgemini.ntc.logger.BFLogger;

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

		// uncommment for manual/demo tests
		// saveScreenshot(description);
		// savePageSource(description);
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


}
