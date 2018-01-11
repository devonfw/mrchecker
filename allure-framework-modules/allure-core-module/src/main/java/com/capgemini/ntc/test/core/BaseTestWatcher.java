package com.capgemini.ntc.test.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.AssumptionViolatedException;
import org.junit.rules.ExternalResource;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.MultipleFailureException;
import org.junit.runners.model.Statement;

import com.capgemini.ntc.test.core.logger.BFLogger;

import ru.yandex.qatools.allure.annotations.Attachment;

public class BaseTestWatcher extends TestWatcher {
	private BaseTest	baseTest;
	private long		iStart;
	
	public BaseTestWatcher(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
	
	static CopyOnWriteArrayList<ITestObserver> observers = new CopyOnWriteArrayList<>();
	
	public static class TestClassRule extends ExternalResource {
		
		static CopyOnWriteArrayList<ITestObserver> classObservers = new CopyOnWriteArrayList<>();
		
		@Override
		protected void after() {
			classObservers.clear();
		}
	}
	
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
	private void skippedQuietly(org.junit.internal.AssumptionViolatedException e, Description description, List<Throwable> errors) {
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
		BaseTest.getAnalytics()
						.sendClassName();
		
		baseTest.setUp(); // Executed as a Before for each test
	}
	
	@Override
	protected void finished(Description description) {
		System.out.println("BaseTestWatcher.finished()");
		this.iStart = System.currentTimeMillis() - this.iStart; // end timing
		printTimeExecutionLog(description);
		baseTest.tearDown(); // Executed as a After for each test
		makeLogForTest(); // Finish logging and add created log as an Allure attachment
		
		// Clear observers for single test
		observers.clear();
	}
	
	@Override
	protected void succeeded(Description description) {
		BFLogger.logInfo(description.getDisplayName() + " PASSED.");
		
		// Run test observers
		observers.forEach(ITestObserver::onTestSuccess);
		
		// // Clear observers for single test
		// observers.clear();
		
	}
	
	@Override
	protected void failed(Throwable e, Description description) {
		BFLogger.logInfo(description.getDisplayName() + " FAILED.");
		
		// Run test observers
		TestClassRule.classObservers.forEach(ITestObserver::onTestFailure);
		observers.forEach(ITestObserver::onTestFailure);
		
		// Clear observers for single test
		// observers.clear();
		
	}
	
	@Attachment("Log file")
	public String makeLogForTest() {
		return BFLogger.RestrictedMethods.dumpSeparateLog();
	}
	
	public static void addObserver(ITestObserver observer) {
		BFLogger.logDebug("Adding observer: " + observer.toString());
		if (isAddedFromBeforeClassMethod()) {
			TestClassRule.classObservers.add(observer);
		} else {
			observers.add(observer);
		}
		
	}
	
	private static boolean isAddedFromBeforeClassMethod() {
		for (StackTraceElement elem : Thread.currentThread()
						.getStackTrace()) {
			try {
				Method method = Class.forName(elem.getClassName())
								.getDeclaredMethod(elem.getMethodName());
				if (method.getDeclaredAnnotation(org.junit.BeforeClass.class) != null) {
					// Adding from BeforeClass-annotated method
					return true;
				}
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				continue;
			}
		}
		return false;
	}
	
	private void printTimeExecutionLog(Description description) {
		BFLogger.logInfo(description.getDisplayName() + getFormatedTestDuration());
	}
	
	private String getFormatedTestDuration() {
		return String.format(" duration: %1.2f min", (float) this.iStart / (60 * 1000));
	}
	
	public static void removeObserver(ITestObserver observer) {
		BFLogger.logDebug("Removing observer: " + observer.toString());
		if (isAddedFromBeforeClassMethod()) {
			TestClassRule.classObservers.remove(observer);
		} else {
			observers.remove(observer);
		}
		
	}
}
