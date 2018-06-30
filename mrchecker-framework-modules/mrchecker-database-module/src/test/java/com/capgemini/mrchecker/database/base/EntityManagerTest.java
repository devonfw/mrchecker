package com.capgemini.mrchecker.database.base;

import com.capgemini.mrchecker.database.base.database.MySqlDatabase;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class EntityManagerTest {

	@Test
	public void testEntityManagerImplementation() {
		//given
		String exampleDatabaseUnitName = "mySqlExample";

		//when
		MySqlDatabase mySqlDatabase = new MySqlDatabase();

		//then
		assertNotNull(mySqlDatabase.getEntityManager());
		assertTrue(mySqlDatabase.getDatabaseUnitName().equals(exampleDatabaseUnitName));
	}

}
