package com.capgemini.mrchecker.test.core.testRunners;

import java.util.List;

import org.junit.AfterClass;
import org.junit.internal.AssumptionViolatedException;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.runner.notification.RunNotifier;
import org.junit.runner.notification.StoppedByUserException;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import com.capgemini.mrchecker.test.core.testRunners.core.CustomRunAfters;

/**
 * Use this runner to run test classes in parallel, with separate "driver" for each thread.
 * 
 * @author
 */
public class ParallelTestClassRunner extends BlockJUnit4ClassRunner {
	
	public ParallelTestClassRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}
	
	@Override
	public void run(final RunNotifier notifier) {
		EachTestNotifier testNotifier = new EachTestNotifier(notifier, getDescription());
		try {
			Statement statement = classBlock(notifier);
			statement.evaluate();
		} catch (AssumptionViolatedException e) {
			testNotifier.addFailedAssumption(e);
		} catch (StoppedByUserException e) {
			throw e;
		} catch (Throwable e) {
			testNotifier.addFailure(e);
		} finally {
		}
	}
	
	/**
	 * Returns a {@link Statement}: run all non-overridden {@code @AfterClass} methods on this class and superclasses
	 * before executing {@code statement}; all AfterClass methods are always executed: exceptions thrown by previous
	 * steps are combined, if necessary, with exceptions from AfterClass methods into a
	 * {@link org.junit.runners.model.MultipleFailureException}.
	 */
	@Override
	protected Statement withAfterClasses(Statement statement) {
		List<FrameworkMethod> afters = getTestClass().getAnnotatedMethods(AfterClass.class);
		return afters.isEmpty() ? statement : new CustomRunAfters(statement, afters, null);
	}
}