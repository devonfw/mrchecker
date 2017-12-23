package com.capgemini.ntc.test.core.logger;

public class BFLogger {
	
	private static ThreadLocal<BFLoggerInstance> loggers = new ThreadLocal<BFLoggerInstance>() {
		
		@Override
		protected BFLoggerInstance initialValue() {
			return new BFLoggerInstance();
		};
		
	};
	
	public static BFLoggerInstance getLog() {
		return loggers.get();
	}
	
	// logger - log INFO message
	public static void logInfo(String message) {
		getLog().logInfo(message);
	}
	
	// logger - log DEBUG message
	public static void logDebug(String message) {
		getLog().logDebug(message);
	}
	
	// logger - log ANALYTICS message
	public static void logAnalytics(String message) {
		getLog().logAnalytics(message);
	}
	
	public static void logFunctionBegin(String functionName) {
		getLog().logFunctionBegin(functionName);
	}
	
	public static void logFunctionEnd() {
		getLog().logFunctionEnd();
	}
	
	// logger - log ERROR message
	public static void logError(String message) {
		getLog().logError(message);
	}
	
	public static void logEnv(String message) {
		getLog().logEnv(message);
	}
	
	public static void logTime(long startTime, String method) {
		double elapsedTime = (System.currentTimeMillis() - startTime) / 1000d;
		getLog().logDebug("Waiting for [" + method + "] took [" + elapsedTime + " s]");
	}
	
	public static void logTime(long startTime, String method, String argument) {
		logTime(startTime, method + ": " + argument);
	}
	
	/**
	 * This inner class contains methods that should not be used unless directly approved by Code Review team
	 * 
	 * @author
	 */
	public static class RestrictedMethods {
		
		/**
		 * WARING: Do not use this method outside of BaseTestWatcher Begin writing all logs to a separate file in
		 * addition to the main log file
		 */
		public static void startSeparateLog() {
			getLog().startSeparateLog();
		}
		
		/**
		 * WARING: Do not use this method outside of BaseTestWatcher Stop writing to the separate log file, return its
		 * current content and clear the file
		 * 
		 * @return
		 */
		public static String dumpSeparateLog() {
			return getLog().dumpSeparateLog();
		}
	}
}