package com.capgemini.ntc.test.core.base.environment.providers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.capgemini.ntc.test.core.base.encryption.IDataEncryptionService;
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
	
	private List<CSVRecord>										records;
	private Map<String, String>								services;
	private Optional<IDataEncryptionService>	encryptionService	= Optional.empty();
	private String														path;
	private String														environmentName;
	
	private SpreadsheetEnvironmentService(String path, String environmentName) {
		this.path = path;
		fetchEnvData(path);
		setEnvironment(environmentName);
		BFLogger.logDebug("Reading environment from: " + path);
	}
	
	public static IEnvironmentService init() {
		String path = SpreadsheetEnvironmentService.class.getResource("")
		        .getPath() + "/environments/environments.csv";
		String environment = "DEV";
		return init(path, environment);
	}
	
	public static IEnvironmentService init(String path, String environment) {
		if (instance == null) {
			synchronized (SpreadsheetEnvironmentService.class) {
				if (instance == null) {
					instance = new SpreadsheetEnvironmentService(path, environment);
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
		this.environmentName = environmentName;
		updateServicesMapBasedOn(environmentName);
	}
	
	@Override
	public String getEnvironment() {
		return this.environmentName;
	}
	
	/**
	 * @param serviceName
	 * @return value of service for current environment
	 */
	public String getValue(String serviceName) {
		String value = services.get(serviceName);
		if (value == null) {
			throw new BFInputDataException("service " + serviceName + " " + "retrieve address of" + " " + "not found in available services table");
		}
		return value;
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
			value = optionalDecrypt(value);
			services.put(key, value);
		}
	}
	
	private String optionalDecrypt(String value) {
		if (encryptionService.isPresent() && encryptionService.get()
		        .isEncrypted(value)) {
			return encryptionService.get()
			        .decrypt(value);
		} else {
			return value;
		}
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
	
	@Override
	public String dataSource() {
		return this.path;
	}
	
	@Override
	public void setDataEncryptionService(IDataEncryptionService service) {
		this.encryptionService = Optional.ofNullable(service);
		setEnvironment(getEnvironment()); // enforce value update
	}
	
}
