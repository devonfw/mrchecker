package com.capgemini.framework.logger;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.log4j.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class LoggerInstance {
	private static final String FBEGIN     = "Function: ";
	private static final String FEND       = "END";
	private static final String logPattern = "%d{yyyy-MM-dd 'at' HH:mm:ss z} %M - %m%x%n";
	private static final Level  loggerLevel;
	private              File   logFile;
	private              String appenderName;
	private              org.apache.log4j.Logger logger;
	private              int    logLevel   = 0;
	
	protected LoggerInstance() {
	}
	
	private AsyncAppender wrapAsync(Appender appender) {
		var wrapper = new AsyncAppender();
		wrapper.addAppender(appender);
		return wrapper;
	}
	
	private org.apache.log4j.Logger getLogger() {
		if (this.logger == null) {
			this.logger = org.apache.log4j.Logger.getLogger(this.getAppenderName());
			this.logger.removeAllAppenders();
			this.logger.setLevel(loggerLevel);
			this.logger.addAppender(this.createEnvFileAppender());
			this.logger.addAppender(this.createFileAppender());
			this.logger.addAppender(this.wrapAsync(this.createConsoleAppender()));
		}
		
		return this.logger;
	}
	
	private FileAppender createEnvFileAppender() {
		var patternLayout = new PatternLayout("%d{yyyy-MM-dd 'at' HH:mm:ss z} %M - %m%x%n");
		
		try {
			var envLogName = this.getLogFile()
					.getPath()
					.replace(".log", "_env.log");
			var appender = new FileAppender(patternLayout, envLogName, true);
			appender.setName("EnvRollingFile");
			appender.setThreshold(LoggerEnvironmentLevel.ENVIRONMENT);
			appender.activateOptions();
			return appender;
		} catch (Exception var4) {
			System.out.println("Unable to create appender: " + var4);
			return null;
		}
	}
	
	private FileAppender createFileAppender() {
		var patternLayout = new PatternLayout("%d{yyyy-MM-dd 'at' HH:mm:ss z} %M - %m%x%n");
		
		try {
			var appender = new FileAppender(patternLayout, this.getLogFile()
					.getPath(), true);
			appender.setName(this.getAppenderName());
			appender.setThreshold(Level.DEBUG);
			appender.activateOptions();
			return appender;
		} catch (Exception var4) {
			System.out.println("Unable to create appender: " + var4);
			return null;
		}
	}
	
	private ConsoleAppender createConsoleAppender() {
		var patternLayout = new PatternLayout("%d{yyyy-MM-dd 'at' HH:mm:ss z} %M - %m%x%n");
		
		try {
			var appender = new ConsoleAppender(patternLayout);
			appender.setTarget("System.out");
			appender.setName("Console");
			appender.setThreshold(Level.DEBUG);
			appender.activateOptions();
			return appender;
		} catch (Exception var4) {
			System.out.println("Unable to create appender: " + var4);
			return null;
		}
	}
	
	private File getLogFile() {
		if (this.logFile == null) {
			this.logFile = new File(this.getDirectory()
					.getPath(), this.getAppenderName() + ".log");
			this.logFile.deleteOnExit();
		}
		
		return this.logFile;
	}
	
	private File getDirectory() {
		var directory = new File("./target/logs");
		if (!directory.exists()) {
			directory.mkdir();
		}
		
		return directory;
	}
	
	private String getAppenderName() {
		if (this.appenderName == null) {
			this.appenderName = Thread.currentThread()
					.getName();
		}
		
		return this.appenderName;
	}
	
	public void logInfo(String message) {
		this.getLogger()
				.info(this.formatMessage(message));
	}
	
	public void logEnv(String message) {
		this.getLogger()
				.log(LoggerEnvironmentLevel.ENVIRONMENT, message);
		if (message.equals("END")) {
			--this.logLevel;
		}
		
	}
	
	public void logDebug(String message) {
		var indent = new char[this.logLevel];
		Arrays.fill(indent, ' ');
		this.getLogger()
				.debug(this.formatMessage(new String(indent) + message));
	}
	
	public void logFunctionBegin(String functionName) {
		this.logDebug("Function: " + functionName);
		++this.logLevel;
	}
	
	public void logFunctionEnd() {
		--this.logLevel;
		this.logDebug("END");
	}
	
	public void logError(String message) {
		this.getLogger()
				.error(this.formatMessage(message));
		if (message.equals("END")) {
			--this.logLevel;
		}
		
	}
	
	private String formatMessage(String message) {
		return "[" + this.getAppenderName() + "] " + message;
	}
	
	protected void startSeparateLog() {
		try {
			var pw = new PrintWriter(this.getLogFile());
			pw.close();
		} catch (FileNotFoundException var2) {
			var2.printStackTrace();
		}
		
	}
	
	protected String dumpSeparateLog() {
		try {
			return Files.toString(this.getLogFile(), Charsets.UTF_8);
		} catch (IOException var2) {
			var2.printStackTrace();
			return "";
		}
	}
	
	static {
		org.apache.log4j.Logger.getRootLogger()
				.removeAppender("console");
		loggerLevel = Level.DEBUG;
	}
}
