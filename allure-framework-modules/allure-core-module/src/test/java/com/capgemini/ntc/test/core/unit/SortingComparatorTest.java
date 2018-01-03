package com.capgemini.ntc.test.core.unit;

import java.util.Arrays;

import org.junit.Test;

import com.capgemini.ntc.test.core.utils.StringComparator;

public class SortingComparatorTest {
	
	private String coreSymbol = "**";
	private String specials = "@#$^*-{}[]:/.‘|,~&()%+_\\";
	private String[] strings = { "ABC", "ABB", "ABBA", "1A", "12", "*A", "@;", "@,", "121", "ABD**", "abc", "abb", "abba" };
	
	@Test
	public void comparatorTest() {
		
		Arrays.sort(strings, new StringComparator(specials, coreSymbol));
		for (String word : strings) {
			System.out.println(word);
		}
	}
}
