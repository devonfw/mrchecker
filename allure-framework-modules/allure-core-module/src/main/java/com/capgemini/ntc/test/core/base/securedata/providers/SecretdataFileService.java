package com.capgemini.ntc.test.core.base.securedata.providers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.exceptions.EncryptionInitializationException;

import com.capgemini.ntc.test.core.base.securedata.ISecureDataService;
import com.capgemini.ntc.test.core.exceptions.BFSecureModuleException;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class SecretdataFileService implements ISecureDataService {
  
  private static ISecureDataService  instance;
  private String                     path;
  private StandardPBEStringEncryptor encryptor;
  
  public SecretdataFileService(String path) {
    this.path = path;
    this.encryptor = new StandardPBEStringEncryptor();
    
    File secretdataFile = new File(path);
    if (!secretdataFile.exists()) {
      BFLogger.logError("Unable to find secret data: " + path);
      return;
    }
    
    setPasswordFromFile(getPasswordFromFile(secretdataFile));
    
  }
  
  public static ISecureDataService init(String path) {
    if (instance == null) {
      synchronized (SecretdataFileService.class) {
        if (instance == null) {
          instance = new SecretdataFileService(path);
        }
      }
    }
    return instance;
  }
  
  public static ISecureDataService getInstance() {
    return SecretdataFileService.instance;
  }
  
  public static void delInstance() {
    SecretdataFileService.instance = null;
  }
  
  @Override
  public String encrypt(String text) {
    String encrypt = "";
    try {
      encrypt = encryptor.encrypt(text);
    } catch (EncryptionInitializationException e) {
      throw new BFSecureModuleException(e.getMessage());
    }
    return encrypt;
  }
  
  @Override
  public String decrypt(String text) {
    String decrypt = "";
    try {
      decrypt = encryptor.decrypt(text);
    } catch (EncryptionInitializationException e) {
      throw new BFSecureModuleException(e.getMessage());
    }
    return decrypt;
  }
  
  @Override
  public void setPassword(String password) {
    encryptor.setPassword(password);
  }
  
  private void setPasswordFromFile(String password) {
    if (null != password) {
      this.setPassword(password);
    }
  }
  
  private String getPasswordFromFile(File secretdataFile) {
    Scanner scanner = null;
    String password = null;
    try {
      scanner = new Scanner(secretdataFile);
      password = getPasswordFromOpenFile(scanner, password);
    } catch (FileNotFoundException e) {
      BFLogger.logError("secretData file not found loading error");
      BFLogger.logError(e.getMessage());
    } finally {
      if (null != scanner) {
        scanner.close();
      }
      
    }
    return password;
  }
  
  private String getPasswordFromOpenFile(Scanner scanner, String password) {
    if (scanner.hasNext()) {
      password = scanner.next();
    } else {
      BFLogger.logError("In secret data file: " + this.path + "  no key/text was found");
    }
    return password;
  }
}
