package com.capgemini.ntc.test.core.base.encryption;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

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

public class DataEncryptionTest {
	
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
		
		Guice.createInjector(securedataTestModel_secretdata_file_with_empty_key())
		        .getInstance(IDataEncryptionService.class);
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
	
	public void secretdata_file_with_wrong_key() {
		DataEncryptionService.delInstance();
		Guice.createInjector(securedataTestModel_secretdata_file_with_wrong_key())
		        .getInstance(IDataEncryptionService.class);
		// That's ok. At this point we don't have any means to tell it's the wrong key.
	}
	
	private AbstractModule securedataTestModel_secretdata_file_with_wrong_key() {
		return new AbstractModule() {
			
			@Override
			protected void configure() {
			}
			
			@Provides
			IDataEncryptionService provideSecuredataService() {
				String path = System.getProperty("user.dir") + Paths.get("/src/test/resources/secretData_test2");
				DataEncryptionService.init(new File(path));
				return DataEncryptionService.getInstance();
			}
		};
	}
	
	@Test
	public void validate_is_text_encrypted() {
		assertTrue(systemUnderTest.isEncrypted("ENC(ffqqqsdf)"));
		assertFalse(systemUnderTest.isEncrypted("ENC(ffqqqsdf"));
		assertFalse(systemUnderTest.isEncrypted("enc(ffqqqsdf)"));
		assertFalse(systemUnderTest.isEncrypted("ffqqqsdf"));
	}
	
	@Test(expected = BFSecureModuleException.class)
	public void decrypt_with_wrong_key() {
		// given
		String ciphertext = systemUnderTest.encrypt("test");
		DataEncryptionService.delInstance();
		IDataEncryptionService service = Guice.createInjector(securedataTestModel_secretdata_file_with_wrong_key())
		        .getInstance(IDataEncryptionService.class);
		
		// when
		service.decrypt(ciphertext);
	}
	
	@Test
	public void decrypt_with_good_key() {
		// given
		String plaintext = "test";
		String ciphertext = systemUnderTest.encrypt(plaintext);
		
		// when
		String plaintext2 = systemUnderTest.decrypt(ciphertext);
		
		// then
		assertEquals(plaintext, plaintext2);
		assertNotEquals(plaintext, ciphertext);
	}
	
	@Test(expected = BFSecureModuleException.class)
	public void set_secret_null() {
		systemUnderTest.setSecret(null);
	}
	
	@Test(expected = BFSecureModuleException.class)
	public void set_secret_short() {
		systemUnderTest.setSecret("1234567");
	}
	
	@Test(expected = BFSecureModuleException.class)
	public void set_secret_trimable() {
		systemUnderTest.setSecret(" 1234567");
	}
	
	@Test
	public void set_secret_ok() {
		systemUnderTest.setSecret("12345678");
	}
	
	@Test()
	public void dataEncryptions_with_default_secretdata_file() {
		// given
		IDataEncryptionService service = Guice.createInjector(new DataEncryptionModule())
		        .getInstance(IDataEncryptionService.class);
		String plaintext = "default";
		
		// when
		String ciphertext = service.encrypt(plaintext);
		String plaintext2 = service.decrypt(ciphertext);
		
		// then
		assertEquals(plaintext, plaintext2);
		assertNotEquals(ciphertext, plaintext);
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
