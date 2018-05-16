package com.capgemini.ntc.database;

import com.capgemini.ntc.database.core.BaseDatabase;
import org.junit.Test;

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
