package com.capgemini.mrchecker.test.core.logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import org.apache.log4j.Appender;
import org.apache.log4j.AsyncAppender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class BFLoggerInstance {
	
	private final String FBEGIN = "Function: ";
	private final String FEND = "END";
	
	static {
		Logger.getRootLogger()
				.removeAppender("console");
	}
	
	private static final String logPattern = "%d{yyyy-MM-dd 'at' HH:mm:ss z} %M - %m%x%n";
	private static final Level loggerLevel = Level.DEBUG;
	
	private File directory;
	private File logFile;
	private String appenderName;
	private Logger logger;
	
	protected BFLoggerInstance() {
	}
	
	/**
	 * Adds the selected appender to a new instance of AsyncAppender
	 * 
	 * @param appender
	 * @return created AsyncAppender with appender attached
	 */
	private AsyncAppender wrapAsync(Appender appender) {
		AsyncAppender wrapper = new AsyncAppender();
		wrapper.addAppender(appender);
		return wrapper;
	}
	
	private Logger getLogger() {
		if (logger == null) {
			logger = Logger.getLogger(getAppenderName());
			logger.removeAllAppenders();
			logger.setLevel(loggerLevel);
			logger.addAppender(createEnvFileAppender());
			logger.addAppender(createFileAppender());
			logger.addAppender(wrapAsync(createConsoleAppender()));
		}
		return logger;
	}
	
	private FileAppender createEnvFileAppender() {
		PatternLayout patternLayout = new PatternLayout(logPattern);
		FileAppender appender;
		try {
			String envLogName = getLogFile().getPath()
					.replace(".log", "_env.log");
			appender = new FileAppender(patternLayout, envLogName, true);
			appender.setName("EnvRollingFile");
			appender.setThreshold(EnvironmentLevel.ENVIRONMENT);
			appender.activateOptions();
		} catch (Exception e) {
			System.out.println("Unable to create appender: " + e);
			return null;
		}
		return appender;
	}
	
	private FileAppender createFileAppender() {
		PatternLayout patternLayout = new PatternLayout(logPattern);
		FileAppender appender;
		try {
			appender = new FileAppender(patternLayout, getLogFile().getPath(), true);
			appender.setName(getAppenderName());
			appender.setThreshold(Level.DEBUG);
			appender.activateOptions();
		} catch (Exception e) {
			System.out.println("Unable to create appender: " + e);
			return null;
		}
		return appender;
	}
	
	private ConsoleAppender createConsoleAppender() {
		PatternLayout patternLayout = new PatternLayout(logPattern);
		ConsoleAppender appender;
		try {
			appender = new ConsoleAppender(patternLayout);
			appender.setTarget(ConsoleAppender.SYSTEM_OUT);
			appender.setName("Console");
			appender.setThreshold(Level.DEBUG);
			appender.activateOptions();
		} catch (Exception e) {
			System.out.println("Unable to create appender: " + e);
			return null;
		}
		return appender;
	}
	
	private File getLogFile() {
		if (logFile == null) {
			String path = getDirectory().getPath() + "\\" + getAppenderName() + ".log";
			logFile = new File(path);
			logFile.deleteOnExit();
		}
		return logFile;
	}
	
	private File getDirectory() {
		if (directory == null) {
			directory = new File("./logs");
			if (!directory.exists()) {
				directory.mkdir();
			}
		}
		return directory;
	}
	
	private String getAppenderName() {
		if (appenderName == null) {
			appenderName = Thread.currentThread()
					.getName();
		}
		return appenderName;
	}
	
	private int logLevel = 0;
	
	// logger - log INFO message
	public void logInfo(String message) {
		getLogger().info(formatMessage(message));
	}
	
	// logger - log ENV message
	public void logEnv(String message) {
		getLogger().log(EnvironmentLevel.ENVIRONMENT, message);
		if (message.equals(FEND))
			--logLevel;
	}
	
	// logger - log DEBUG message
	public void logDebug(String message) {
		char[] indent = new char[logLevel];
		Arrays.fill(indent, ' ');
		
		getLogger().debug(formatMessage(new String(indent) + message));
	}
	
	// logger - log ANALYTICS message
	public void logAnalytics(String message) {
		char[] indent = new char[logLevel];
		Arrays.fill(indent, ' ');
		getLogger().debug(formatMessage(new String(indent) + message));
	}
	
	public void logFunctionBegin(String functionName) {
		logDebug(FBEGIN + functionName);
		++logLevel;
	}
	
	public void logFunctionEnd() {
		--logLevel;
		logDebug(FEND);
	}
	
	// logger - log ERROR message
	public void logError(String message) {
		getLogger().error(formatMessage(message));
		if (message.equals(FEND))
			--logLevel;
	}
	
	private String formatMessage(String message) {
		return "[" + getAppenderName() + "] " + message;
	}
	
	/**
	 * WARING: Do not use this method outside of BaseTestWatcher Begin writing all logs to a separate file in addition
	 * to the main log file
	 */
	protected void startSeparateLog() {
		try {
			PrintWriter pw = new PrintWriter(getLogFile());
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * WARING: Do not use this method outside of BaseTestWatcher Stop writing to the separate log file, return its
	 * current content and clear the file
	 * 
	 * @return
	 */
	protected String dumpSeparateLog() {
		try {
			String logTxt = Files.toString(getLogFile(), Charsets.UTF_8);
			return logTxt;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
}