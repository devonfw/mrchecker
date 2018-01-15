package com.capgemini.ntc.test.core.base.environment.providers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.PropertyValueEncryptionUtils;

import com.capgemini.ntc.test.core.base.environment.IEnvironmentService;
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
public class SpreadsheetEnvironmentService implements IEnvironmentService {
	
	private static IEnvironmentService instance;
	
	private List<CSVRecord>		records;
	private Map<String, String>	services;
	
	private String						path;
	private StandardPBEStringEncryptor	encryptor;
	
	private SpreadsheetEnvironmentService(String path, String environmentName, String secretPath) {
		this.path = path;
		initEncryptor(secretPath);
		fetchEnvData(path);
		setEnvironment(environmentName);
		BFLogger.logDebug("Reading environment from: " + path);
	}
	
	private void initEncryptor(String secretPath) {
		if (secretPath == null) {
			return;
		}
		
		try (BufferedReader br = new BufferedReader(new FileReader(secretPath))) {
			String secret = br.readLine();
			encryptor = new StandardPBEStringEncryptor();
			encryptor.setPassword(secret);
		} catch (Exception e) {
			e.printStackTrace();
			BFLogger.logError(e.getLocalizedMessage());
			throw new RuntimeException(e);
		}
	}
	
	public static IEnvironmentService init() {
		String path = SpreadsheetEnvironmentService.class.getResource("")
						.getPath() + "/environments/environments.csv";
		String secretPath = SpreadsheetEnvironmentService.class.getResource("")
						.getPath() + "/environments/secret.txt";
		String environment = "DEV";
		return init(path, environment, secretPath);
	}
	
	public static IEnvironmentService init(String path, String environment, String secretPath) {
		if (instance == null) {
			synchronized (SpreadsheetEnvironmentService.class) {
				if (instance == null) {
					instance = new SpreadsheetEnvironmentService(path, environment, secretPath);
				}
			}
		}
		return instance;
	}
	
	public static IEnvironmentService getInstance() {
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
	public void setEnvironment(String environmentName) {
		updateServicesMapBasedOn(environmentName);
	}
	
	/**
	 * @param serviceName
	 * @return address of service for current environment
	 */
	public String getServiceAddress(String serviceName) {
		String serviceAddress = services.get(serviceName);
		if (serviceAddress == null) {
			throw new BFInputDataException("service " + serviceName + " " + "retrieve address of" + " " + "not found in available services table");
		}
		return serviceAddress;
	}
	
	private void fetchEnvData(String path) throws BFInputDataException {
		File csvData = new File(path);
		try {
			CSVParser parser = CSVParser.parse(csvData, Charset.defaultCharset(), CSVFormat.RFC4180);
			records = parser.getRecords();
		} catch (IOException e) {
			throw new BFInputDataException("Unable to parse CSV: " + path);
		}
	}
	
	private void updateServicesMapBasedOn(String environmentName) {
		services = new HashMap<String, String>();
		
		int environmentNumber = getEnvironmentNumber(environmentName);
		
		Iterator<CSVRecord> it = records.iterator();
		it.next(); // first row contains table headers, so skip it
		while (it.hasNext()) {
			CSVRecord record = it.next();
			String key = record.get(0);
			String value = record.get(environmentNumber)
							.trim();
			value = decrypt(value);
			value = formatAddress(value);
			services.put(key, value);
		}
	}
	
	private String decrypt(String value) {
		if (encryptor != null && PropertyValueEncryptionUtils.isEncryptedValue(value)) {
			return PropertyValueEncryptionUtils.decrypt(value, encryptor);
		}
		return value;
	}
	
	private String formatAddress(String address) {
		address = address.replaceAll("\\\\", "/");
		return address;
	}
	
	private int getEnvironmentNumber(String environmentName) throws BFInputDataException {
		CSVRecord header = records.get(0);
		for (int environmentNumber = 0; environmentNumber < header.size(); environmentNumber++) {
			String environment = header.get(environmentNumber);
			if (environment.equals(environmentName)) {
				BFLogger.logInfo("Selected Environment: " + environmentName);
				return environmentNumber;
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
		return this.path;
	}
	
}
