package com.capgemini.framework.environment.csvEnv;

import com.capgemini.framework.environment.encryption.IDataEncryptionService;
import com.capgemini.framework.environment.encryption.providers.DataEncryptionService;
import com.capgemini.framework.exceptions.InputDataException;
import com.capgemini.framework.logger.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class EnvironmentCsvReader {
	
	private static final Map<String, String>              csvEvnData           = new HashMap<>();
	private static final String                           ENVIRONMENT_FILENAME = "src/test/resources/environments/environments.csv";
	private static final String                           ENVIRONMENT_NAME     = System.getProperty("env", "ENV1");
	private static       Optional<IDataEncryptionService> encryptionService;
	private static       List<CSVRecord>                  records;
	private static       int                              envColumnNumber;
	
	static {
		Logger.logEnv("RUN PARAMETER: Selected Environment: " + ENVIRONMENT_NAME);
		DataEncryptionService.init();
		encryptionService = Optional.ofNullable(DataEncryptionService.getInstance());
		readDataFromEnvCsv();
		setEnvironment();
		updateServicesMapBasedOn();
	}
	
	private EnvironmentCsvReader() {
	}
	
	public static synchronized void readDataFromEnvCsv() {
		var csvEvnData = "";
		
		try {
			csvEvnData = new String(Files.readAllBytes(Paths.get(ENVIRONMENT_FILENAME)));
		} catch (IOException e) {
			throw new InputDataException("Environment file could not be processed: " + ENVIRONMENT_FILENAME);
		}
		
		var csvFormat = CSVFormat.RFC4180.builder()
				.setIgnoreSurroundingSpaces(true)
				.setIgnoreEmptyLines(true)
				.build();
		try (var parser = CSVParser.parse(csvEvnData, csvFormat)) {
			records = parser.getRecords();
		} catch (IOException e) {
			throw new InputDataException("Unable to parse CSV data: " + csvEvnData);
		}
	}
	
	/**
	 * @param variableName
	 * 		A name of an option to get
	 * @return value of service for current environment
	 */
	public static String getValue(String variableName) {
		var value = csvEvnData.get(variableName);
		if (csvEvnData.isEmpty()) {
			throw new InputDataException("CSV environment data are empty");
		}
		return value;
	}
	
	private static void updateServicesMapBasedOn() {
		var it = records.iterator();
		var columns = it.next().size();
		while (it.hasNext()) {
			var lineRecord = it.next();
			var value = "";

			if (lineRecord.size() != columns) {
				Logger.logError("Invalid number of columns [" + lineRecord.size() + ", expected " + columns + "] in the environment file row.\n" + lineRecord);
			} else {
				value = lineRecord.get(envColumnNumber)
						.trim();
			}
			csvEvnData.put(lineRecord.get(0), optionalDecrypt(value));
		}
	}
	
	private static String optionalDecrypt(String value) {
		if (encryptionService.isPresent() && encryptionService.get().isEncrypted(value)) {
			value = encryptionService.get().decrypt(value);
		}
		
		return value;
	}
	
	private static void setEnvironment() {
		envColumnNumber = getEnvironmentColumnNumber(ENVIRONMENT_NAME);
		updateServicesMapBasedOn();
	}
	
	private static int getEnvironmentColumnNumber(String environmentName) throws InputDataException {
		var header = records.get(0);
		for (var environmentNumber = 0; environmentNumber < header.size(); environmentNumber++) {
			if (header.get(environmentNumber).equals(environmentName)) {
				return environmentNumber;
			}
		}
		throw new InputDataException("There is no Environment with name '" + environmentName + "' available");
	}
}
