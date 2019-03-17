package com.capgemini.mrchecker.core.datadriven;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.capgemini.mrchecker.core.datadriven.person_example.SimpleCalc;
import com.capgemini.mrchecker.test.core.BaseTest;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class DataDrivenInternalExampleTest extends BaseTest {
	private SimpleCalc testSubject = new SimpleCalc();
	
	/**
	 * ---------------------------------------------------------------------------------------------------------------
	 * --- EXAMPLE 1 -------------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------------------------
	 * takes parameters from the inside the annotation
	 **/
	@Test
	@Parameters({ "1, 2, 3",
			"3, 4, 7",
			"5, 6, 11",
			"7, 8, 15" })
	public void addProducesCorrectValue_usingAnnotatedParameters(final int a, final int b, final int expectedResult) {
		assertEquals(expectedResult, testSubject.add(a, b));
	}
	
	/**
	 * ---------------------------------------------------------------------------------------------------------------
	 * --- EXAMPLE 2 -------------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------------------------
	 * takes parameters from the addParameters method
	 **/
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
	
	/**
	 * ---------------------------------------------------------------------------------------------------------------
	 * --- EXAMPLE 3 -------------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------------------------
	 * takes parameters from the methods in MyContainsTestProvider
	 **/
	@Test
	@Parameters(source = MyContainsTestProvider.class)
	public void testContains_usingSeperateClass(final List<String> list, final String searchString, final boolean expectedResult) {
		assertEquals(expectedResult, testSubject.contains(list, searchString));
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
	
	@Override
	public void setUp() {
	}
	
	@Override
	public void tearDown() {
		
	}
	
}