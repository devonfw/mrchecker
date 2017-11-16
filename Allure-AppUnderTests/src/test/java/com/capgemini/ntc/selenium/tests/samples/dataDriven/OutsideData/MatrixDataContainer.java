package com.capgemini.ntc.selenium.tests.samples.dataDriven.OutsideData;

/**
 * @author Entity class for "matrix_official.json" data. As of 10/22/2015, mentioned JSON contains (a lot) more fields
 *         than this class, however, other fields are currently unused in TCs, therefore unnecessary. They can be added
 *         manually on demand safely, without breaking any code.
 */
public class MatrixDataContainer {
	String USER;
	String[][] cards;
	String[][] expectedValues;

	public MatrixDataContainer(String USER, String[][] cards) {
		this.USER = USER;
		this.cards = cards;
	}

	public String getLogin() {
		return USER;
	}

	public String[][] getExpectedValues() {
		return cards;
	}
}
