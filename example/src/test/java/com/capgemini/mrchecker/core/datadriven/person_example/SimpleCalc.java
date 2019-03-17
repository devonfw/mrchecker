package com.capgemini.mrchecker.core.datadriven.person_example;

import java.util.List;

public class SimpleCalc {
	
	public int add(final int a, final int b) {
		return a + b;
	}
	
	public boolean contains(final List<String> list, final String a) {
		return list.contains(a);
	}
	
}
