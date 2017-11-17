package com.capgemini.ntc.test.core.base.environment.providers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.capgemini.ntc.test.core.base.environment.EnvironmentService;
import com.capgemini.ntc.test.core.exceptions.BFInputDataException;
import com.capgemini.ntc.test.core.logger.BFLogger;
import com.google.inject.Singleton;

/**
 * This class is responsible for handling addresses of services. Addresses depends on currently set Environment. It is
 * possible to change environment before run adding -DenvURL parameter. Default Environment is set to DEV1
 * 
 * @author
 */

@Singleton
public class SpreadsheetEnvironmentService implements EnvironmentService {
	
	private static EnvironmentService instance;
	
	private String path;
	private int environmentNumber;
	private List<CSVRecord> records;
	private Map<String, String> services;
	
	private SpreadsheetEnvironmentService(String path) {
		this.path = path;
		this.environmentNumber = 1; // column number taken as a default
		fetchEnvData();
		updateServicesMap();
	}
	
	public static EnvironmentService init() {
		String path = SpreadsheetEnvironmentService.class.getResource("")
				.getPath() + "/environments/environments.csv";
		return init(path);
	}
	
	public static EnvironmentService init(String path) {
		if (instance == null) {
			synchronized (SpreadsheetEnvironmentService.class) {
				if (instance == null) {
					instance = new SpreadsheetEnvironmentService(path);
				}
			}
		}
		return instance;
	}
	
	public static EnvironmentService getInstance() {
		return SpreadsheetEnvironmentService.instance;
	}
	
	public static void delInstance() {
		SpreadsheetEnvironmentService.instance = null;
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
	
	/**
	 * @param serviceName
	 * @return address of service for current environment
	 */
	public String getServiceAddress(String serviceName) {
		String serviceAddress = services.get(serviceName);
		if (serviceAddress == null) {
			throw new BFInputDataException(
					"service " + serviceName + " " +
							"retrieve address of" + " " +
							"not found in available services table");
		}
		return serviceAddress;
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
			String value = record.get(environmentNumber)
					.trim();
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
	
	/*
	 * (non-Javadoc)
	 * @see com.capgemini.ntc.test.core.base.environments.EnvironmentService#dataSource()
	 */
	@Override
	public String dataSource() {
		return path;
	}
	
}
