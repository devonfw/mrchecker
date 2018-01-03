package com.capgemini.ntc.selenium.core.exceptions;

import com.capgemini.ntc.test.core.logger.BFLogger;
import org.openqa.selenium.*;

public class BFElementNotFoundException extends NoSuchElementException {
	
	private static final long serialVersionUID = 6697212034783474583L;
	private static String messageText;
	
	/**
	 * {@code BFElementNotFoundException} is the class that can be thrown during any type of {@code findElement} and/or
	 * {@code findElementDynamic}
	 * <p>
	 * {@code BFElementNotFoundException} and its subclasses are <em>unchecked exceptions</em>. Given exception will be
	 * throw when <em>element</em> is not found directly or by timeout
	 *
	 * @author
	 * @param by
	 *            - {@link org.openqa.selenium.By}
	 */
	public BFElementNotFoundException(By by) {
		this(by, "", false, -1);
	}
	
	/**
	 * {@code BFElementNotFoundException} is the class that can be thrown during any type of {@code findElement} and/or
	 * {@code findElementDynamic}
	 * <p>
	 * {@code BFElementNotFoundException} and its subclasses are <em>unchecked exceptions</em>. Given exception will be
	 * throw when <em>element</em> is not found directly or by timeout
	 *
	 * @author
	 * @param by
	 *            - {@link org.openqa.selenium.By}
	 * @param message
	 *            - any addition text in {@link #message} will be added at the beginning of log
	 */
	public BFElementNotFoundException(By by, String message) {
		this(by, message, false, -1);
	}
	
	/**
	 * {@code BFElementNotFoundException} is the class that can be thrown during any type of {@code findElement} and/or
	 * {@code findElementDynamic}
	 * <p>
	 * {@code BFElementNotFoundException} and its subclasses are <em>unchecked exceptions</em>. Given exception will be
	 * throw when <em>element</em> is not found directly or by timeout
	 *
	 * @author
	 * @param by
	 *            - {@link org.openqa.selenium.By}
	 * @param isTimeout
	 *            - use this when log must have information that element was not found by timeout
	 */
	public BFElementNotFoundException(By by, boolean isTimeout) {
		this(by, "", isTimeout, -1);
		
	}
	
	/**
	 * {@code BFElementNotFoundException} is the class that can be thrown during any type of {@code findElement} and/or
	 * {@code findElementDynamic}
	 * <p>
	 * {@code BFElementNotFoundException} and its subclasses are <em>unchecked exceptions</em>. Given exception will be
	 * throw when <em>element</em> is not found directly or by timeout
	 *
	 * @author
	 * @param by
	 *            - {@link org.openqa.selenium.By}
	 * @param message
	 *            - any addition text in {@link #message} will be added at the beginning of log
	 * @param isTimeout
	 *            - use this when log must have information that element was not found by timeout
	 */
	public BFElementNotFoundException(By by, String message, boolean isTimeout) {
		this(by, message, isTimeout, -1);
	}
	
	/**
	 * {@code BFElementNotFoundException} is the class that can be thrown during any type of {@code findElement} and/or
	 * {@code findElementDynamic}
	 * <p>
	 * {@code BFElementNotFoundException} and its subclasses are <em>unchecked exceptions</em>. Given exception will be
	 * throw when <em>element</em> is not found directly or by timeout
	 *
	 * @author
	 * @param by
	 *            - {@link org.openqa.selenium.By}
	 * @param isTimeout
	 *            - use this when log must have information that element was not found by timeout
	 * @param timeoutValue
	 *            - used with {@link #isTimeout}. Give timeout in sec will be added in logs
	 */
	public BFElementNotFoundException(By by, boolean isTimeout, int timeoutValue) {
		this(by, "", isTimeout, timeoutValue);
	}
	
	/**
	 * {@code BFElementNotFoundException} is the class that can be thrown during any type of {@code findElement} and/or
	 * {@code findElementDynamic}
	 * <p>
	 * {@code BFElementNotFoundException} and its subclasses are <em>unchecked exceptions</em>. Given exception will be
	 * throw when <em>element</em> is not found directly or by timeout
	 *
	 * @author
	 * @param by
	 *            - {@link org.openqa.selenium.By}
	 * @param message
	 *            - any addition text in {@link #message} will be added at the beginning of log
	 * @param isTimeout
	 *            - use this when log must have information that element was not found by timeout
	 * @param timeoutValue
	 *            - used with {@link #isTimeout}. Give timeout in sec will be added in logs
	 */
	public BFElementNotFoundException(By by, String message, boolean isTimeout, int timeoutValue) {
		super(generateMessage(by, message, isTimeout, timeoutValue));
		if (message.isEmpty())
			return;
		BFLogger.logDebug(messageText);
	}
	
	public BFElementNotFoundException(String message) {
		super(message);
		BFLogger.logDebug(message);
	}
	
	private static String generateMessage(By by, String message, boolean isTimeout, int timeoutValue) {
		messageText = addTimeoutMessasg(message, isTimeout, timeoutValue) + generateStandardMessage(by);
		return messageText;
	}
	
	private static String generateStandardMessage(By by) {
		return getExceutedMethodName() + "\nElement '" + by.toString() + "' was not found. Check printscreen.\n";
		
	}
	
	private static String getExceutedMethodName() {
		String executedMethodName = "\nExecuted method: ";
		try {
			String stackTraceElements = "";
			for (int i = 0; i++ < 16;) {
				String stackTraceElement = Thread.currentThread()
						.getStackTrace()[i].toString();
				if (stackTraceElement.contains("com.example.webframe.pages") && !stackTraceElement
						.contains("com.example.webframe.pages.exceptions.BFElementNotFoundException")) {
					stackTraceElements = stackTraceElements
							.concat("\n" + Thread.currentThread()
									.getStackTrace()[i].toString());
				}
			}
			executedMethodName = executedMethodName + stackTraceElements + "\n";
		} catch (ArrayIndexOutOfBoundsException e) {
			executedMethodName = "";
		}
		return executedMethodName;
	}
	
	private static String addTimeoutMessasg(String message, boolean isTimeout, int timeoutValue) {
		if (isTimeout) {
			String messageTimeout = " Due to TIMEOUT. ";
			if (timeoutValue >= 0)
				messageTimeout = " Due to TIMEOUT= " + timeoutValue + "sec.";
			message = message + messageTimeout;
		}
		return message;
	}
	
}
