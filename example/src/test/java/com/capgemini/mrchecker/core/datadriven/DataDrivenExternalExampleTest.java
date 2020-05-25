package com.capgemini.mrchecker.core.datadriven;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.capgemini.mrchecker.core.datadriven.person_example.PersonAggregator;
import com.capgemini.mrchecker.core.datadriven.person_example.SimpleCalc;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class DataDrivenExternalExampleTest extends BaseTest {
	private final SimpleCalc testSubject = new SimpleCalc();
	
	/**
	 * ---------------------------------------------------------------------------------------------------------------
	 * --- EXAMPLE 1 -------------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------------------------
	 **/
	@ParameterizedTest
	@CsvFileSource(resources = "/datadriven/test.csv", numLinesToSkip = 1)
	public void loadParamsFromCsv(String age, String name) {
		BFLogger.logDebug("DataDrivenExampleTest.loadParamsFromCsv()");
		BFLogger.logDebug("\t" + "Name=" + name + " " + "Age=" + age);
		
	}
	
	/**
	 * ---------------------------------------------------------------------------------------------------------------
	 * --- EXAMPLE 2 -------------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------------------------
	 * takes parameters from a CSV file with headers
	 **/
	@ParameterizedTest
	@CsvFileSource(resources = "/datadriven/with_header.csv")
	public void loadParamsFromCsvWithHeader(String age, String name) {
		BFLogger.logDebug("DataDrivenExampleTest.loadParamsFromCsvWithHeader()");
		BFLogger.logDebug("\t" + "Name=" + name + " " + "Age=" + age);
	}
	
	/**
	 * ---------------------------------------------------------------------------------------------------------------
	 * --- EXAMPLE 3 -------------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------------------------
	 * takes parameters from a CSV file and map them to specific mapper
	 **/
	@ParameterizedTest
	@CsvFileSource(resources = "/datadriven/test.csv", numLinesToSkip = 1)
	public void loadParamsFromAnyFile(@AggregateWith(PersonAggregator.class) PersonAggregator.Person person) {
		BFLogger.logDebug("DataDrivenExampleTest.loadParamsFromAnyFile()");
		BFLogger.logDebug("\t" + "Name=" + person.getName() + " " + "Age=" + person.getAge());
	}
	
	@Override
	public void setUp() {
		
	}
	
	@Override
	public void tearDown() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}