package com.capgemini.ntc.test.core.exceptions;

import com.capgemini.ntc.test.core.logger.BFLogger;

public class BFSecureModuleException extends AssertionError {
  
  /**
   * 
   */
  private static final long serialVersionUID = 6815162645071113994L;
  private static String     exceptionMessage;
  
  public BFSecureModuleException(String message) {
    super(generateExceptionMessage(message));
    BFLogger.logError(exceptionMessage);
  }
  
  private static String generateExceptionMessage(String message) {
    exceptionMessage = message;
    return exceptionMessage;
  }
  
}
