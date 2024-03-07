package com.capgemini.framework.playwright;

import com.capgemini.framework.logger.Logger;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class TestExecutionWatcher implements TestWatcher {
	
	@Override
	public void testDisabled(ExtensionContext extensionContext, Optional<String> optional) {
		Logger.logInfo("### TEST DISABLED ### " + extensionContext.getDisplayName());
	}
	
	@Override
	public void testSuccessful(ExtensionContext extensionContext) {
		Logger.logInfo("### TEST PASSED ### " + extensionContext.getDisplayName());
	}
	
	@Override
	public void testAborted(ExtensionContext extensionContext, Throwable throwable) {
		Logger.logInfo("### TEST ABORTED ### " + extensionContext.getDisplayName());
	}
	
	@Override
	public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
		Logger.logInfo("### TEST FAILED ### " + extensionContext.getDisplayName());
	}
	
}
