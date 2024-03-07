package com.capgemini.framework.exceptions;

public class EncodingException extends AssertionError {
	
	private static final long serialVersionUID = 6815162645071113994L;
	
	public EncodingException(String message) {
		super(generateExceptionMessage(message));
	}
	
	public EncodingException(String message, Throwable e) {
		super(generateExceptionMessage(message), e);
	}
	
	private static String generateExceptionMessage(String message) {
		return message == null ? "" : message;
	}
	
}
