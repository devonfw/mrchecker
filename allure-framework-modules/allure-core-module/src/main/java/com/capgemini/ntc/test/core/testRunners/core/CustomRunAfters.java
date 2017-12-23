package com.capgemini.ntc.test.core.testRunners.core;

import java.util.ArrayList;
import java.util.List;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.MultipleFailureException;
import org.junit.runners.model.Statement;

import com.capgemini.ntc.test.core.logger.BFLogger;

/**
 * This class replace default RunAfters class. The difference is, that using this one, we avoid having additional tests
 * in our report. Using default JUnit RunAfters caused false report results in case test fails/breaks during AfterClass
 * execution. For example, when we execute 1 test and it fails during AfterClass, in report we will see 1 test passed
 * and 1 failed.
 * 
 * @author
 */
public class CustomRunAfters extends Statement {
	private final Statement next;
	
	private final Object target;
	
	private final List<FrameworkMethod> afters;
	
	public CustomRunAfters(Statement next, List<FrameworkMethod> afters, Object target) {
		this.next = next;
		this.afters = afters;
		this.target = target;
	}
	
	@Override
	public void evaluate() throws Throwable {
		List<Throwable> errors = new ArrayList<Throwable>();
		try {
			next.evaluate();
		} catch (Throwable e) {
			errors.add(e);
		} finally {
			for (FrameworkMethod each : afters) {
				try {
					each.invokeExplosively(target);
				} catch (Throwable e) {
					BFLogger.logError("There was an exception in @AfterClass " + each.getName() + ": " + e.toString());
				}
			}
		}
		MultipleFailureException.assertEmpty(errors);
	}
}