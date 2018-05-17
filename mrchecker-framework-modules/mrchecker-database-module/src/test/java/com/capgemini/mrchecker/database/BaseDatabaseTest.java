package com.capgemini.mrchecker.database;

import org.junit.Test;

import com.capgemini.mrchecker.database.core.BaseDatabase;

public class BaseDatabaseTest {

	@Test
	public void test() {
		MyDatabase myDatabase = new MyDatabase();

		myDatabase.myMethod();
	}

	private static class MyDatabase extends BaseDatabase {

		public String myMethod() {
			return "Welcome";
		}
	}

}
