package com.capgemini.ntc.test.core.base.encryption;

import static org.junit.Assert.fail;

import java.io.File;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.ntc.test.core.base.encryption.providers.DataEncryptionService;
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
	
	private IDataEncryptionService systemUnderTest;
	
	@Before
	public void setUp() throws Exception {
		
		systemUnderTest = Guice.createInjector(securedataTestModel())
		        .getInstance(IDataEncryptionService.class);
		
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test(expected = BFSecureModuleException.class)
	public void secretdata_file_does_not_exsist() {
		DataEncryptionService.delInstance();
		Guice.createInjector(securedataTestModel_secretdata_file_does_not_exsist())
		        .getInstance(IDataEncryptionService.class);
	}
	
	private AbstractModule securedataTestModel_secretdata_file_does_not_exsist() {
		return new AbstractModule() {
			
			@Override
			protected void configure() {
			}
			
			@Provides
			IDataEncryptionService provideSecuredataService() {
				String path = System.getProperty("user.dir") + Paths.get("/src/test/resources/secretData_wrong_path");
				DataEncryptionService.init(new File(path));
				return DataEncryptionService.getInstance();
			}
		};
	}
	
	@Test(expected = BFSecureModuleException.class)
	public void secretdata_file_with_empty_key() {
		DataEncryptionService.delInstance();
		
		IDataEncryptionService secureDataService = Guice.createInjector(securedataTestModel_secretdata_file_with_empty_key())
		        .getInstance(IDataEncryptionService.class);
		
		secureDataService.decrypt("test");
	}
	
	private AbstractModule securedataTestModel_secretdata_file_with_empty_key() {
		return new AbstractModule() {
			
			@Override
			protected void configure() {
			}
			
			@Provides
			IDataEncryptionService provideSecuredataService() {
				String path = System.getProperty("user.dir") + Paths.get("/src/test/resources/secretData_emptykey");
				DataEncryptionService.init(new File(path));
				return DataEncryptionService.getInstance();
			}
		};
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
			IDataEncryptionService provideSecuredataService() {
				String path = System.getProperty("user.dir") + Paths.get("/src/test/resources/secretData_test");
				DataEncryptionService.init(new File(path));
				return DataEncryptionService.getInstance();
			}
		};
	}
	
}
