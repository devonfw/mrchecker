package com.capgemini.ntc.core.datadriven;

import java.util.List;

public class JUnitParamsTutorial {
	
	public int add(final int a, final int b) {
		return a + b;
	}
	
	public boolean contains(final List<String> list, final String a) {
		return list.contains(a);
	}
	
}
