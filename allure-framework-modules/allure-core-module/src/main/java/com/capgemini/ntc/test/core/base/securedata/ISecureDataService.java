package com.capgemini.ntc.test.core.base.securedata;

import com.capgemini.ntc.test.core.exceptions.BFSecureModuleException;

/**
 * @author LUSTEFAN
 *
 */
/**
 * @author LUSTEFAN
 */
public interface ISecureDataService {
  
  /**
   * Encrypt given text
   * 
   * @param text
   * @return encrypted text as string
   */
  public String encrypt(String text) throws BFSecureModuleException;
  
  /**
   * Decrypt given text
   * 
   * @param text
   * @return decrypted text as string
   */
  public String decrypt(String text) throws BFSecureModuleException;
  
  /**
   * Set password used while encrypt/decrypt
   * 
   * @param password
   */
  public void setPassword(String password);
  
}
