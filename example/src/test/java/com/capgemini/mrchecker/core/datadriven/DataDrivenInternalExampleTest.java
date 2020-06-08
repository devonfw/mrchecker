package com.capgemini.mrchecker.core.datadriven;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.capgemini.mrchecker.core.datadriven.person_example.SimpleCalc;
import com.capgemini.mrchecker.test.core.BaseTest;

public class DataDrivenInternalExampleTest extends BaseTest {
	private final SimpleCalc testSubject = new SimpleCalc();
	
	// /**
	// * ---------------------------------------------------------------------------------------------------------------
	// * --- EXAMPLE 1 -------------------------------------------------------------------------------------------------
	// * ---------------------------------------------------------------------------------------------------------------
	// * NOT SUPPORTED in JUNIT5 - takes parameters from the inside the annotation
	// **/
	// @ParameterizedTest
	// @ValueSource(ints ={1, 2, 3},
	// {3, 4, 7},
	// {5, 6, 11},
	// {{7, 8, 15}})
	// public void addProducesCorrectValue_usingAnnotatedValueSource(final int a, final int b, final int expectedResult)
	// {
	// assertEquals(expectedResult, testSubject.add(a, b));
	// }
	
	/**
	 * ---------------------------------------------------------------------------------------------------------------
	 * --- EXAMPLE 2 -------------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------------------------
	 * takes parameters from the argumentsStream method
	 **/
	@ParameterizedTest
	@MethodSource("argumentsStream")
	public void addProducesCorrectValue_usingMethodSourceArgumentsStream(final int a, final int b, final int expectedResult) {
		assertEquals(expectedResult, testSubject.add(a, b));
	}
	
	static Stream<Arguments> argumentsStream() {
		return Stream.of(
				Arguments.of(1, 2, 3),
				Arguments.of(3, 4, 7),
				Arguments.of(5, 6, 11),
				Arguments.of(7, 8, 15));
	}
	
	@ParameterizedTest
	@MethodSource("arrayStream")
	public void addProducesCorrectValue_usingMethodSourceArrayStream(final int[] values) {
		assertEquals(values[2], testSubject.add(values[0], values[1]));
	}
	
	static Stream<int[]> arrayStream() {
		return Stream.of(new int[] { 1, 2, 3, }, new int[] { 3, 4, 7 }, new int[] { 5, 6, 11 }, new int[] { 7, 8, 15 });
	}
	
	/**
	 * ---------------------------------------------------------------------------------------------------------------
	 * --- EXAMPLE 3 -------------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------------------------
	 * takes parameters from a method in MyContainsTestProvider
	 **/
	@ParameterizedTest
	@MethodSource("com.capgemini.mrchecker.core.datadriven.MyContainsTestProvider#provideContainsTrueParameters")
	public void testContains_usingSeparateClass(final List<String> list, final String searchString, final boolean expectedResult) {
		assertEquals(expectedResult, testSubject.contains(list, searchString));
	}
}

@SuppressWarnings("unused")
class MyContainsTestProvider {
	public static Stream<Arguments> provideContainsTrueParameters() {
		return Stream.of(
				Arguments.of(Arrays.asList("a", "b", "c", "d", "e"), "c", true),
				Arguments.of(Arrays.asList("a", "b", "c", "d", "e"), "e", true),
				Arguments.of(Arrays.asList("a", "b"), "b", true),
				Arguments.of(Collections.singletonList("a"), "a", true));
	}
	
	public static Stream<Arguments> provideContainsFalseParameters() {
		return Stream.of(
				Arguments.of(Arrays.asList("a", "b", "c", "d", "e"), "f", false),
				Arguments.of(Arrays.asList("a", "b", "c", "d", "e"), "z", false),
				Arguments.of(Arrays.asList("a", "b"), "e", false),
				Arguments.of(Collections.emptyList(), "e", false));
	}
}
