package com.capgemini.ntc.test.core.base.securedata;

import java.nio.file.Paths;

import com.capgemini.ntc.test.core.base.securedata.providers.SecretdataFileService;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class SecureDataModule extends AbstractModule {
  
  @Override
  protected void configure() {
    
  }
  
  @Provides
  ISecureDataService provideSpreadsheetEnvironmentService() {
    String path = System.getProperty("user.dir") + Paths.get("/src/test/resources/secretData_test");
    SecretdataFileService.init(path);
    return SecretdataFileService.getInstance();
  }
  
}
