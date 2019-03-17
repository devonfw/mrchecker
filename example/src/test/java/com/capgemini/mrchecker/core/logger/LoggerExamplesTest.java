package com.capgemini.mrchecker.core.logger;

import org.junit.Test;

import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class LoggerExamplesTest {
	
	@Test
	public void test() {
		
		BFLogger.logInfo("Used for test steps");
		
		BFLogger.logDebug("Used for non official information, "
				+ "either during test build process or in Page Object files");
		
		BFLogger.logError("Used to emphasize critical information");
		
	}
	
}
