package com.capgemini.mrchecker.database;

import org.junit.Test;

public class BaseDatabaseTest {

	@Test
	public void test() {
		MyDatabase myDatabase = new MyDatabase();

		myDatabase.myMethod();
	}

	public static class MyDatabase extends BaseDatabaseTest {

		public String myMethod() {
			return "Welcome";
		}
	}

}
