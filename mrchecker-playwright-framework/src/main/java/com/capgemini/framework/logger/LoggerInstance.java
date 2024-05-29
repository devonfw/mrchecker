package com.capgemini.framework.logger;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.ThreadContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class LoggerInstance {
	private org.apache.logging.log4j.Logger logger;

	private static final String FBEGIN = "Function: ";
	private static final String FEND = "END";

	public LoggerInstance() {

		ThreadContext.put("threadName", Thread.currentThread()
				.getName());
		getLogger();
	}

	private org.apache.logging.log4j.Logger getLogger() {
		if (Objects.isNull(null)) {
			logger = LogManager.getRootLogger();
		}
		return logger;
	}

	public void log(Level level, String message) {
		logger.log(level, message);
	}

	public void logDebug(String message) {
		log(Level.DEBUG, message);
	}

	public void logInfo(String message) {
		log(Level.INFO, message);
	}

	public void logWarning(String message) {
		log(Level.WARN, message);
	}

	public void logError(String message) {
		log(Level.ERROR, message);
	}

	public void logFunctionBegin(String functionName) {
		log(Level.DEBUG, FBEGIN + functionName);
	}

	public void logFunctionEnd() {
		log(Level.DEBUG, FEND);
	}

	public void logEnv(String message) {
		log(Level.forName("ENV", 700), message);
	}

	public void logAnalytics(String message) {
		log(Level.forName("ANALYTICS", 800), message);
	}

	private String getAppenderName() {
		return ThreadContext.get("threadName");
	}

	protected File getDirectory() {
		return new File("./logs");
	}

	private File getLogFile() {
		return new File(getDirectory().getPath(), getAppenderName() + ".log");
	}

	public void startSeparateLog() {
		try {
			PrintWriter pw = new PrintWriter(getLogFile());
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public String dumpSeparateLog() {
		try {
			return Files.toString(getLogFile(), Charsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
}
