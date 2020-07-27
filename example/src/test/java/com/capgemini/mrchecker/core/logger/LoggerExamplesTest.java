package com.capgemini.mrchecker.core.logger;

import com.capgemini.mrchecker.test.core.logger.BFLogger;
import org.junit.jupiter.api.Test;

public class LoggerExamplesTest {

	@Test
	public void test() {

		BFLogger.logInfo("Used for test steps");

		BFLogger.logDebug("Used for non official information, "
				+ "either during test build process or in Page Object files");

		BFLogger.logError("Used to emphasize critical information");

	}

}
