package com.capgemini.framework.logger;

import org.apache.log4j.Level;

public class LoggerEnvironmentLevel extends Level {
	public static final int   ENVIRONMENT_INT = 40010;
	public static final Level ENVIRONMENT     = new LoggerEnvironmentLevel(40010, "ENVIRONMENT", 10);
	
	protected LoggerEnvironmentLevel(int arg0, String arg1, int arg2) {
		super(arg0, arg1, arg2);
	}
	
	public static Level toLevel(String logArgument) {
		return toLevel(logArgument, Level.DEBUG);
	}
	
	public static Level toLevel(String logArgument, Level defaultLevel) {
		return logArgument != null && logArgument.equalsIgnoreCase("ENVIRONMENT") ? ENVIRONMENT : Level.toLevel(logArgument, defaultLevel);
	}
	
	public static Level toLevel(int val) {
		return toLevel(val, Level.DEBUG);
	}
	
	public static Level toLevel(int val, Level defaultLevel) {
		return val == 40010 ? ENVIRONMENT : Level.toLevel(val, defaultLevel);
	}
}
