package com.capgemini.ntc.core.datadriven.person_example;

import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

import junitparams.mappers.CsvWithHeaderMapper;

public class PersonMapper extends CsvWithHeaderMapper {
	@Override
	public Object[] map(Reader reader) {
		Object[] map = super.map(reader);
		List<Object[]> result = new LinkedList<Object[]>();
		for (Object lineObj : map) {
			String line = (String) lineObj;
			result.add(new Object[] { line.substring(2), Integer.parseInt(line.substring(0, 1)) });
		}
		return result.toArray();
	}
	
}