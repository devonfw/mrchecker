package com.capgemini.mrchecker.database.base;

import com.capgemini.mrchecker.database.base.database.MySqlDatabase;
import com.capgemini.mrchecker.test.core.base.properties.PropertiesSettingsModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class EntityManagerTest {


	private MySqlDatabase mySqlDatabase;

	@Before
	public void setUp() throws Exception {
		mySqlDatabase = new MySqlDatabase();
	}

	@Test
	public void testShouldValidateDatabaseUnitName() {
		//given
		String exampleDatabaseUnitName = "mySqlExample";

		//when
		//then
		assertTrue(mySqlDatabase.getDatabaseUnitName().equals(exampleDatabaseUnitName));
	}

	@Test
	public void testShouldValidateEntityManagerCreation() {
		//given
		//when
		//then
		assertNotNull(mySqlDatabase.getEntityManager());
	}

}
