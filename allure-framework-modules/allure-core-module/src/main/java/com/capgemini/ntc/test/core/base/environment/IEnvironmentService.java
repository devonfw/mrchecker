package com.capgemini.ntc.test.core.base.environment;

/**
 * @author LUSTEFAN
 */
public interface IEnvironmentService {
	
	// as the next step please define other Environment Services
	
	/**
	 * Sets environment (e.g. "QC1")
	 * 
	 * @param environmentName
	 */
	public void setEnvironment(String environmentName);
	
	/**
	 * Sets environment (e.g. "QC1")
	 * 
	 * @param environmentName
	 */
	public String getEnvironment();
	
	/**
	 * @param serviceName
	 * @return get value of service for current environment from data source
	 */
	public String getValue(String serviceName);
	
	/**
	 * @return source from Environment variables are taken. This could be file path
	 */
	public String dataSource();
	
}
