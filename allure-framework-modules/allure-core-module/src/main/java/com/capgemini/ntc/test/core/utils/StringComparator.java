package com.capgemini.ntc.test.core.utils;

import java.util.Comparator;

public class StringComparator implements Comparator<String> {
	
	private String specials;
	private String coreSymbol;
	
	public StringComparator(String specials, String coreSymbol) {
		this.specials = specials;
		this.coreSymbol = coreSymbol;
	}
	
	public int compare(String wordA, String wordB) {
		int length;
		int result;
		char charA;
		char charB;
		
		// compare base symbol with non-base symbol
		if (wordA.matches(coreSymbol) && !wordB.matches(coreSymbol)) {
			return -1;
		}
		if (wordB.matches(coreSymbol) && !wordA.matches(coreSymbol)) {
			return 1;
		}
		if (wordA.matches("N/A")) {
			return -1;
		}
		
		if (wordA.length() > wordB.length()) {
			length = wordB.length();
		} else {
			length = wordA.length();
		}
		
		wordA = removeHyphenFromBeginning(wordA);
		wordB = removeHyphenFromBeginning(wordB);
		
		for (int i = 0; i < length; i++) {
			charA = wordA.charAt(i);
			charB = wordB.charAt(i);
			if (specials.contains(Character.toString(charA)) && specials.contains(Character.toString(charB))) { // 2
																												// special
																												// characters
				return specials.indexOf(charA) - specials.indexOf(charB);
			} else if (specials.contains(Character.toString(charA)) && !specials.contains(Character.toString(charB))) {
				return 1;
			} else if (!specials.contains(Character.toString(charA)) && specials.contains(Character.toString(charB))) {
				return -1;
			} else {
				result = charA - charB;
				if (result != 0) {
					return result;
				}
			}
		}
		return Integer.compare(wordA.length(), wordB.length());
	}
	
	private String removeHyphenFromBeginning(String string) {
		if (string.charAt(0) == '-') {
			return string.substring(1, string.length());
		}
		return string;
	}
}