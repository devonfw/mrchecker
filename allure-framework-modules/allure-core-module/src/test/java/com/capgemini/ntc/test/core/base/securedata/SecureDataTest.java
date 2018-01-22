package com.capgemini.ntc.test.core.base.securedata;

import static org.junit.Assert.fail;

import java.nio.file.Paths;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.ntc.test.core.base.securedata.providers.SecretdataFileService;
import com.capgemini.ntc.test.core.exceptions.BFSecureModuleException;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Provides;

public class SecureDataTest {
  
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    
  }
  
  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }
  
  private ISecureDataService systemUnderTest;
  
  @Before
  public void setUp() throws Exception {
    
    systemUnderTest = Guice.createInjector(securedataTestModel())
            .getInstance(ISecureDataService.class);
    
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test(expected = BFSecureModuleException.class)
  public void secretdata_file_does_not_exsist() {
    SecretdataFileService.delInstance();
    
    ISecureDataService secureDataService = Guice.createInjector(securedataTestModel_secretdata_file_does_not_exsist())
            .getInstance(ISecureDataService.class);
    
    secureDataService.decrypt("test");
  }
  
  private AbstractModule securedataTestModel_secretdata_file_does_not_exsist() {
    return new AbstractModule() {
      
      @Override
      protected void configure() {
      }
      
      @Provides
      ISecureDataService provideSecuredataService() {
        String path = System.getProperty("user.dir") + Paths.get("/src/test/resources/secretData_wrong_path");
        SecretdataFileService.init(path);
        return SecretdataFileService.getInstance();
      }
    };
  }
  
  @Test
  public void secretdata_file_with_empty_key() {
    fail("Not yet implemented");
  }
  
  @Test
  public void secretdata_file_with_wrong_key() {
    fail("Not yet implemented");
  }
  
  @Test
  public void validate_is_text_encrypted() {
    fail("Not yet implemented");
  }
  
  @Test
  public void decrypt_with_wrong_key() {
    fail("Not yet implemented");
  }
  
  @Test
  public void decrypt_with_good_key() {
    fail("Not yet implemented");
  }
  
  @Test
  public void setPassword() {
    fail("Not yet implemented");
  }
  
  private AbstractModule securedataTestModel() {
    return new AbstractModule() {
      
      @Override
      protected void configure() {
      }
      
      @Provides
      ISecureDataService provideSecuredataService() {
        String path = System.getProperty("user.dir") + Paths.get("/src/test/resources/secretData_test");
        SecretdataFileService.init(path);
        return SecretdataFileService.getInstance();
      }
    };
  }
  
}
