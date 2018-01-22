package com.capgemini.ntc.test.core.logger;

import org.apache.log4j.Level;

@SuppressWarnings("serial")
public class EnvironmentLevel extends Level {
	public static final int ENVIRONMENT_INT = ERROR_INT + 10;
	
	/**
	 * Level representing my log level
	 */
	public static final Level ENVIRONMENT = new EnvironmentLevel(ENVIRONMENT_INT, "ENVIRONMENT", 10);
	
	/**
	 * Constructor
	 */
	protected EnvironmentLevel(int arg0, String arg1, int arg2) {
		super(arg0, arg1, arg2);
		
	}
	
	public static Level toLevel(String logArgument) {
		if (logArgument != null && logArgument.toUpperCase()
				.equals("ENVIRONMENT")) {
			return ENVIRONMENT;
		}
		return (Level) toLevel(logArgument);
	}
	
	public static Level toLevel(int val) {
		if (val == ENVIRONMENT_INT) {
			return ENVIRONMENT;
		}
		return (Level) toLevel(val, Level.DEBUG);
	}
	
	public static Level toLevel(int val, Level defaultLevel) {
		if (val == ENVIRONMENT_INT) {
			return ENVIRONMENT;
		}
		return Level.toLevel(val, defaultLevel);
	}
	
	public static Level toLevel(String logArgument, Level defaultLevel) {
		if (logArgument != null && logArgument.toUpperCase()
				.equals("ENVIRONMENT")) {
			return ENVIRONMENT;
		}
		return Level.toLevel(logArgument, defaultLevel);
	}
}
