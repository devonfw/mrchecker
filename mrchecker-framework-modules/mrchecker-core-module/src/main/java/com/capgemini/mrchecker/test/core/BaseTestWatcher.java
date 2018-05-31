package com.capgemini.mrchecker.test.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.AssumptionViolatedException;
import org.junit.rules.ExternalResource;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.MultipleFailureException;
import org.junit.runners.model.Statement;

import com.capgemini.mrchecker.test.core.logger.BFLogger;

import ru.yandex.qatools.allure.annotations.Attachment;

public class BaseTestWatcher extends TestWatcher {
	private BaseTest baseTest;
	private long iStart;
	
	static final ThreadLocal<List<ITestObserver>> observers = new ThreadLocal<List<ITestObserver>>() {
		@Override
		protected List<ITestObserver> initialValue() {
			return new ArrayList<ITestObserver>();
		}
	};
	
	public static class TestClassRule extends ExternalResource {
		
		static final ThreadLocal<List<ITestObserver>> classObservers = new ThreadLocal<List<ITestObserver>>() {
			@Override
			protected List<ITestObserver> initialValue() {
				return new ArrayList<ITestObserver>();
			}
		};
		
		@Override
		protected void after() {
			classObservers.get()
					.clear();
		}
	}
	
	public BaseTestWatcher(BaseTest baseTest) {
		this.baseTest = baseTest;
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
		this.iStart = System.currentTimeMillis() - this.iStart; // end timing
		printTimeExecutionLog(description);
		baseTest.tearDown(); // Executed as a After for each test
		makeLogForTest(); // Finish logging and add created log as an Allure attachment
		
		observers.get()
				.forEach(ITestObserver::onTestFinish);
	}
	
	@Override
	protected void succeeded(Description description) {
		BFLogger.logInfo(description.getDisplayName() + " PASSED.");
		
		// Run test observers
		TestClassRule.classObservers.get()
				.forEach(ITestObserver::onTestSuccess);
		observers.get()
				.forEach(ITestObserver::onTestSuccess);
	}
	
	@Override
	protected void failed(Throwable e, Description description) {
		BFLogger.logInfo(description.getDisplayName() + " FAILED.");
		
		// Run test observers
		TestClassRule.classObservers.get()
				.forEach(ITestObserver::onTestFailure);
		observers.get()
				.forEach(ITestObserver::onTestFailure);
	}
	
	@Attachment("Log file")
	public String makeLogForTest() {
		return BFLogger.RestrictedMethods.dumpSeparateLog();
	}
	
	public static void addObserver(ITestObserver observer) {
		BFLogger.logDebug("To add observer: " + observer.toString());
		
		boolean anyMatchTestClassObservers = TestClassRule.classObservers.get()
				.stream()
				.anyMatch(x -> x.getModuleType()
						.equals(observer.getModuleType()));
		
		boolean anyMatchMethodObservers = observers.get()
				.stream()
				.anyMatch(x -> x.getModuleType()
						.equals(observer.getModuleType()));
		
		BFLogger.logDebug("BaseTestWatcher.observers: " + BaseTestWatcher.observers.get()
				.toString());
		BFLogger.logDebug("TestClassRule.classObservers: " + TestClassRule.classObservers.get()
				.toString());
		
		if (!(anyMatchMethodObservers | anyMatchTestClassObservers)) {
			if (isAddedFromBeforeClassMethod()) {
				TestClassRule.classObservers.get()
						.add(observer);
			} else {
				observers.get()
						.add(observer);
			}
			BFLogger.logDebug("Added observer: " + observer.toString());
			
		}
		
	}
	
	public static void removeObserver(ITestObserver observer) {
		BFLogger.logDebug("To remove observer: " + observer.toString());
		
		if (isAddedFromBeforeClassMethod()) {
			TestClassRule.classObservers.get()
					.remove(observer);
			BFLogger.logDebug("Removed observer: " + observer.toString());
		} else {
			if (!TestClassRule.classObservers.get()
					.isEmpty()) {
				observers.get()
						.remove(observer);
				BFLogger.logDebug("Removed observer: " + observer.toString());
			}
		}
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
	
}
