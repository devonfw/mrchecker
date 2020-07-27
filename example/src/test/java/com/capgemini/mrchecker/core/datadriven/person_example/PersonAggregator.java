package com.capgemini.mrchecker.core.datadriven.person_example;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

public class PersonAggregator implements ArgumentsAggregator {
	
	@Override
	public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
		return new Person(argumentsAccessor.getString(0),
				argumentsAccessor.getString(1));
	}
	
	public static class Person {
		
		private String	name;
		private Integer	age;
		
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
		
		// When argument AGE is missing, then set default value = 0
		private void setAge(String age) {
			try {
				this.age = Integer.parseInt(age);
			} catch (NumberFormatException e) {
				this.age = 0; // Default value
			}
		}
		
		@Override
		public String toString() {
			return "Person of age: " + age;
		}
	}
}
