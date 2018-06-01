package com.capgemini.mrchecker.core.datadriven.person_example;

import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

import junitparams.mappers.CsvWithHeaderMapper;

public class PersonMapper extends CsvWithHeaderMapper {
	@Override
	public Object[] map(Reader reader) {
		Object[] map = super.map(reader);
		List<Object[]> result = new LinkedList<Object[]>();
		for (Object lineObj : map) {
			String line = (String) lineObj;
			
			// Splitted with ","
			Object[] lineSplitted = line.split(","); // Example line { 21,John }
			
			// Order of arguments must be inline with Person class constructor argument list
			result.add(lineSplitted);
		}
		return result.toArray();
	}
	
	public static class Person {
		
		private String name;
		private Integer age;
		
		// Arguments order depends on data in CSV line
		public Person(String age, String name) {
			this.name = name;
			setAge(age);
			
		}
		
		// When there is only one argument after CSV line split, than treat this one as it is argument AGE
		public Person(String age) {
			setAge(age);
		}
		
		public String getName() {
			return name;
		}
		
		public boolean isAdult() {
			return age >= 18;
		}
		
		public int getAge() {
			return age;
		}
		
		@Override
		public String toString() {
			return "Person of age: " + age;
		}
		
		// When argument AGE is missing, then set default value = 0
		private void setAge(String age) {
			try {
				this.age = Integer.parseInt(age);
			} catch (NumberFormatException e) {
				this.age = 0; // Default value
			}
		}
	}
	
}