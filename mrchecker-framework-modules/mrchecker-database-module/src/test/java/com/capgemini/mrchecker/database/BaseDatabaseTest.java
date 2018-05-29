package com.capgemini.mrchecker.database;

import org.junit.Test;

public class BaseDatabaseTest {

	@Test
	public void test() {
		MyDatabase myDatabase = new MyDatabase();

		myDatabase.myMethod();
	}

	private static class MyDatabase extends BaseDatabaseTest {

		public String myMethod() {
			return "Welcome";
		}
	}

}
