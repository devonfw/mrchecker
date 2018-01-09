package com.capgemini.ntc.core.datadriven;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.capgemini.ntc.core.datadriven.person_example.PersonMapper;
import com.capgemini.ntc.core.datadriven.person_example.PersonMapper.Person;
import com.capgemini.ntc.test.core.logger.BFLogger;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import junitparams.mappers.CsvWithHeaderMapper;

@RunWith(JUnitParamsRunner.class)
public class DataDrivenExternalExampleTest {
	private JUnitParamsTutorial testSubject = new JUnitParamsTutorial();
	
	/**
	 * ---------------------------------------------------------------------------------------------------------------
	 * --- EXAMPLE 1 -------------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------------------------
	 * dsadada
	 **/
	
	@Test
	@FileParameters("src/test/resources/datadriven/test.csv")
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
	@Test
	@FileParameters(value = "src/test/resources/datadriven/with_header.csv", mapper = CsvWithHeaderMapper.class)
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
	@Test
	@FileParameters(value = "src/test/resources/datadriven/test.csv", mapper = PersonMapper.class)
	public void loadParamsFromAnyFile(Person person) {
		BFLogger.logDebug("DataDrivenExampleTest.loadParamsFromAnyFile()");
		BFLogger.logDebug("\t" + "Name=" + person.getName() + " " + "Age=" + person.getAge());
	}
	
}