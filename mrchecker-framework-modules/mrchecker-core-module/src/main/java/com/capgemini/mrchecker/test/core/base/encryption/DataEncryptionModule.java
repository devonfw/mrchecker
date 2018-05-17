package com.capgemini.mrchecker.test.core.base.encryption;

import java.io.File;
import java.nio.file.Paths;

import com.capgemini.mrchecker.test.core.base.encryption.providers.DataEncryptionService;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class DataEncryptionModule extends AbstractModule {
	
	@Override
	protected void configure() {
		
	}
	
	@Provides
	@Singleton
	IDataEncryptionService provideSpreadsheetEnvironmentService() {
		String path = System.getProperty("user.dir") + Paths.get("/src/resources/secretData");
		DataEncryptionService.init(new File(path));
		return DataEncryptionService.getInstance();
	}
	
}
