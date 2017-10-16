package com.example.core.download;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

import org.apache.commons.csv.*;

import com.example.selenium.core.exceptions.BFElementNotFoundException;
import com.example.selenium.core.exceptions.BFInitializationException;

/**
 * This class contains utils related to CSV files analyze.
 * 
 * @author
 */
public class CsvAnalyzer {

	private CSVParser csvParser;
	private List<CSVRecord> csvRecords;
	private File file;
	private Map<String, Integer> headerMap;

	public CsvAnalyzer(File file) {
		super();
		this.file = file;
		initialize();
	}

	private void initialize() {
		try {
			this.csvParser = CSVParser.parse(file, Charset.defaultCharset(),
					CSVFormat.EXCEL.withDelimiter(',').withHeader());
			csvRecords = csvParser.getRecords();
			headerMap = csvParser.getHeaderMap();
		} catch (IOException e) {
			throw new BFInitializationException(e, "Cannot initialize CSV Parser for file: " + file.getName());
		}
	}

	public List<CSVRecord> getRecords() {
		return csvRecords;
	}

	/**
	 * @param columnNameToSearchIn
	 *            - column name to search text specified by rowContentToSearch
	 * @param rowContentToSearch
	 *            - text to search in column specified by columnNameToSearchIn
	 * @param columnNameToReturn
	 *            - column name to return value from found row
	 * @return Cell content specified by row and column. <br/>
	 *         <b>Example:</b> For account with FBXAA in 'Symbol' column we need content from 'Quantity' column then
	 *         arguments will be:<br/>
	 *         columnNameToSearchIn = Symbol<br/>
	 *         rowContentToSearch = FBXAA<br/>
	 *         columnNameToReturn = Quantity
	 * @throws BFElementNotFoundException
	 *             if row not found or row does not contain specified column
	 * @author
	 */
	public String getCellContentByRowColumn(String columnNameToSearchIn, String rowContentToSearch,
			String columnNameToReturn) {
		Integer columnIndexToReturn = headerMap.get(columnNameToReturn);

		CSVRecord csvRecord = getRowByRowColumn(columnNameToSearchIn, rowContentToSearch);
		if (columnIndexToReturn >= csvRecord.size()) {
			throw new BFElementNotFoundException(
					"Row with '" + rowContentToSearch + "' does not have column '" + columnNameToSearchIn + "'");
		} else {
			return csvRecord.get(columnIndexToReturn);
		}
	}

	/**
	 * @param columnName
	 *            - name of column header
	 * @return List with content from specified column (without header)
	 * @author
	 */
	public List<String> getColumnContentAsList(String columnName) {
		List<String> columnsAsList = new ArrayList<String>(csvRecords.size());
		Integer columnIndex = headerMap.get(columnName);

		for (CSVRecord csvRecord : csvRecords) {
			if (columnIndex >= csvRecord.size()) {
				continue;
			}
			String record = csvRecord.get(columnIndex);
			columnsAsList.add(record);
		}
		return columnsAsList;
	}

	/**
	 * @param columnName
	 *            - name of column header
	 * @param rowNumber
	 *            - row number starting from 0 not including header row
	 * @return Value in specified cell <br/>
	 *         <b>Example:</b> We need content from 'Quantity' column from first row then arguments will be:<br/>
	 *         columnName = Quantity<br/>
	 *         rowNumber = 0<br/>
	 * @author
	 */
	public String getColumnRowValue(String columnName, int rowNumber) {
		Integer columnIndex = headerMap.get(columnName);
		return csvRecords.get(rowNumber).get(columnIndex);
	}

	/**
	 * @return Map of csv headers and their indexes
	 * @author
	 */
	public Map<String, Integer> getHeader() {
		return headerMap;
	}

	/**
	 * @return Map of csv headers and their indexes
	 * @author
	 */
	public String[] getHeaders() {
		Set<String> headers = headerMap.keySet();
		return headers.toArray(new String[headers.size()]);
	}

	/**
	 * @param columnNameToSearchIn
	 *            - column name to search text specified by rowContentToSearch
	 * @param rowContentToSearch
	 *            - text to search in column specified by columnNameToSearchIn
	 * @return CSVRecord if row found <br/>
	 *         <b>Example:</b> For account with FBXAA in 'Symbol' column we need whole row then arguments will be:<br/>
	 *         columnNameToSearchIn = Symbol<br/>
	 *         rowContentToSearch = FBXAA<br/>
	 * @throws BFElementNotFoundException
	 *             if row not found
	 * @author
	 */
	public CSVRecord getRowByRowColumn(String columnNameToSearchIn, String rowContentToSearch) {
		Integer columnIndexToSearchIn = headerMap.get(columnNameToSearchIn);
		for (CSVRecord csvRecord : csvRecords) {
			if (columnIndexToSearchIn >= csvRecord.size()) {
				continue;
			}
			if (csvRecord.get(columnIndexToSearchIn).equals(rowContentToSearch)) {
				return csvRecord;
			}
		}
		throw new BFElementNotFoundException(
				"Could not find row with content '" + rowContentToSearch + "' in column '" + columnNameToSearchIn
						+ "'");
	}

	/**
	 * @param text
	 *            - text to search for
	 * @return True if file contains text, false otherwise
	 * @author
	 */
	public boolean isFileContainsText(String text) {
		for (CSVRecord csvRecord : csvRecords) {
			if (csvRecord.toString().contains(text)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if there is a row containing given value (it wont check headers)
	 * 
	 * @param value
	 * @return true if there is a row containing given value, false otherwise
	 */
	public boolean isFileContainsValue(String value) {
		for (CSVRecord csvRecord : csvRecords) {
			for (int i = 0; i < csvRecord.size(); i++) {
				String cellContent = csvRecord.get(i);
				if (cellContent.contains(value)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @return True if headers and all values in file match the pattern
	 * 
	 * @author
	 */
	public boolean isEveryCellFormatValid(String pattern) {
		// check headers
		for (String header : headerMap.keySet()) {
			if (!header.matches(pattern)) {
				return false;
			}
		}

		// check row values
		for (CSVRecord csvRecord : csvRecords) {
			for (int i = 0; i < csvRecord.size(); i++) {
				String value = csvRecord.get(i);
				if (!value.matches(pattern)) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Method to get first not empty row in csv File.
	 *
	 * @throws IOException
	 *             If an I/O error occurs
	 */
	public int csvStartRecord() {
		List<CSVRecord> csvRecordsList = getRecords();
		int csvStart = 0;
		for (int i = 0; i < csvRecordsList.size(); i++) {
			if (csvRecordsList.get(i).get(0).equals("")) {
				csvStart++;
			} else {
				break;
			}
		}
		return csvStart;
	}

	/**
	 * Closes resources.
	 *
	 * @throws IOException
	 *             If an I/O error occurs
	 */
	public void closeFile() {
		if (!this.csvParser.isClosed()) {
			try {
				this.csvParser.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
