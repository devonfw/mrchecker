package com.example.core.base.environments;


import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.example.core.exceptions.BFInputDataException;
import com.example.core.logger.BFLogger;
import com.example.selenium.core.exceptions.BFComponentStateException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * This class is responsible for handling addresses of services. Addresses depends on currently set Environment. It is
 * possible to change environment before run adding -DenvURL parameter. Default Environment is set to DEV1
 * 
 * @author
 *
 */
public class EnvironmentServices {

	private String testResourcePath = getClass().getClassLoader().getResource("").getPath();

	private final String path = testResourcePath + "/enviroments/environments.csv";
	private int environmentNumber = 1; // = number of column in CSV file
	private List<CSVRecord> records;
	private Map<String, String> services;

	public EnvironmentServices() {
		fetchEnvData();
		updateServicesMap();
	}

	private void fetchEnvData() throws BFInputDataException {
		File csvData = new File(path);
		try {
			CSVParser parser = CSVParser.parse(csvData, Charset.defaultCharset(), CSVFormat.RFC4180);
			records = parser.getRecords();
		} catch (IOException e) {
			throw new BFInputDataException("Unable to parse" + path + " CSV.");
		}
	}

	private void updateServicesMap() {
		services = new HashMap<String, String>();
		Iterator<CSVRecord> it = records.iterator();
		it.next(); // first row contains table headers, so skip it
		while (it.hasNext()) {
			CSVRecord record = it.next();
			String key = record.get(0);
			String value = record.get(environmentNumber).trim();
			value = formatAddress(value);
			services.put(key, value);
		}
	}

	private String formatAddress(String address) {
		address = address.replaceAll("\\\\", "/");
		if (!address.endsWith("/")) {
			address = address + "/";
		}
		return address;
	}

	/**
	 * Sets environment (e.g. "QC1")
	 * 
	 * @param environmentName
	 */
	public void set(String environmentName) {
		setEnvironmentNumber(environmentName);
		updateServicesMap();
	}

	private void setEnvironmentNumber(String environmentName) {
		CSVRecord header = records.get(0);
		for (int i = 0; i < header.size(); i++) {
			String environment = header.get(i);
			if (environment.equals(environmentName)) {
				environmentNumber = i;
				BFLogger.logInfo("Selected Environment: " + environmentName);
				return;
			}
		}
		throw new BFInputDataException("There is no Environment with name '" + environmentName + "' available");
	}

	/**
	 * @param serviceName
	 * @return address of service for current environment
	 */
	public String getServiceAddress(String serviceName) {
		String serviceAddress = services.get(serviceName);
		if (serviceAddress == null) {
			throw new BFComponentStateException(
					"service " + serviceName,
					"retrieve address of",
					"not found in available services table");
		}
		return serviceAddress;
	}
}
