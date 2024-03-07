package com.capgemini.framework.logger;

public class Logger {
	private static final ThreadLocal<LoggerInstance> loggers = new ThreadLocal<LoggerInstance>() {
		protected LoggerInstance initialValue() {
			return new LoggerInstance();
		}
	};
	
	private Logger() {
	}
	
	public static LoggerInstance getLog() {
		return loggers.get();
	}
	
	public static void logInfo(String message) {
		getLog().logInfo(message);
	}
	
	public static void logDebug(String message) {
		getLog().logDebug(message);
	}
	
	public static void logFunctionBegin(String functionName) {
		getLog().logFunctionBegin(functionName);
	}
	
	public static void logFunctionEnd() {
		getLog().logFunctionEnd();
	}
	
	public static void logError(String message) {
		getLog().logError(message);
	}
	
	public static void logEnv(String message) {
		getLog().logEnv(message);
	}
	
	public static void logTime(long startTime, String method) {
		var elapsedTime = (double) (System.currentTimeMillis() - startTime) / 1000.0D;
		getLog().logDebug("Waiting for [" + method + "] took [" + elapsedTime + " s]");
	}
	
	public static void logTime(long startTime, String method, String argument) {
		logTime(startTime, method + ": " + argument);
	}
	
	public static void removeLogInstance() {
		loggers.remove();
	}
	
	public static class RestrictedMethods {
		private RestrictedMethods() {
		}
		
		public static void startSeparateLog() {
			Logger.getLog()
					.startSeparateLog();
		}
		
		public static String dumpSeparateLog() {
			return Logger.getLog()
					.dumpSeparateLog();
		}
	}
}
