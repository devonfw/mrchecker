package com.capgemini.ntc.core.datadriven;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.capgemini.ntc.core.datadriven.person_example.PersonMapper;
import com.capgemini.ntc.core.datadriven.person_example.PersonTest;
import com.capgemini.ntc.test.core.logger.BFLogger;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.mappers.CsvWithHeaderMapper;

@RunWith(JUnitParamsRunner.class)
public class DataDrivenExampleTest {
	private JUnitParamsTutorial testSubject = new JUnitParamsTutorial();
	
	// takes parameters from the inside the annotation
	@Test
	@Parameters({ "1, 2, 3",
					"3, 4, 7",
					"5, 6, 11",
					"7, 8, 15" })
	public void addProducesCorrectValue_usingAnnotatedParameters(final int a, final int b, final int expectedResult) {
		assertEquals(expectedResult, testSubject.add(a, b));
	}
	
	// takes parameters from the addParameters method
	@Test
	@Parameters(method = "addParameters")
	public void addProducesCorrectValue_usingNamedMethodParameters(final int a, final int b, final int expectedResult) {
		assertEquals(expectedResult, testSubject.add(a, b));
	}
	
	private Object[] addParameters() {
		return new Object[] {
						new Object[] { 1, 2, 3 },
						new Object[] { 3, 4, 7 },
						new Object[] { 5, 6, 11 },
						new Object[] { 7, 8, 15 }
		};
	}
	
	// takes parameters from the methods in MyContainsTestProvider
	@Test
	@Parameters(source = MyContainsTestProvider.class)
	public void testContains_usingSeperateClass(final List<String> list, final String a, final boolean expectedResult) {
		assertEquals(expectedResult, testSubject.contains(list, a));
	}
	
	public static class MyContainsTestProvider {
		public static Object[] provideContainsTrueParameters() {
			return new Object[] {
							new Object[] { Arrays.asList("a", "b", "c", "d", "e"), "c", true },
							new Object[] { Arrays.asList("a", "b", "c", "d", "e"), "e", true },
							new Object[] { Arrays.asList("a", "b"), "b", true },
							new Object[] { Arrays.asList("a"), "a", true }
			};
		}
		
		public static Object[] provideContainsFalseParameters() {
			return new Object[] {
							new Object[] { Arrays.asList("a", "b", "c", "d", "e"), "f", false },
							new Object[] { Arrays.asList("a", "b", "c", "d", "e"), "z", false },
							new Object[] { Arrays.asList("a", "b"), "e", false },
							new Object[] { Arrays.asList(), "e", false }
			};
		}
	}
	
	@Ignore
	// takes parameters from a CSV file
	@Test
	@FileParameters("resources/JUnitParamsTutorialParameters.csv")
	public void addProducesCorrectValue_usingCSV(final int a, final int b, final int expectedResult) {
		assertEquals(expectedResult, testSubject.add(a, b));
	}
	
	@Test
	@FileParameters("src/test/resources/datadriven/test.csv")
	public void loadParamsFromCsv(int age, String name) {
		BFLogger.logDebug("DataDrivenExampleTest.loadParamsFromCsv()");
		BFLogger.logDebug("Name=" + name + " " + "Age=" + age);
	}
	
	// takes parameters from a CSV file with headers
	@Test
	@FileParameters(value = "src/test/resources/datadriven/with_header.csv", mapper = CsvWithHeaderMapper.class)
	public void loadParamsFromCsvWithHeader(int id, String name) {
		BFLogger.logDebug("DataDrivenExampleTest.loadParamsFromCsvWithHeader()");
		BFLogger.logDebug("Name=" + name + " " + "ID=" + id);
	}
	
	// takes parameters from a CSV file and map them to specific mapper
	@Test
	@FileParameters(value = "src/test/resources/datadriven/test.csv", mapper = PersonMapper.class)
	public void loadParamsFromAnyFile(PersonTest.Person person) {
		BFLogger.logDebug("DataDrivenExampleTest.loadParamsFromAnyFile()");
		BFLogger.logDebug("Name=" + person.getName() + " " + "Age=" + person.getAge());
	}
	
}