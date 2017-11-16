package com.capgemini.ntc.test.core.base.environments;

/**
 * @author LUSTEFAN
 *
 */
public interface EnvironmentService {
	
	
	//as the next step please define other Environment Services
	
	
	/**
	 * Sets environment (e.g. "QC1")
	 * 
	 * @param environmentName
	 */
	public void set(String environmentName);
	
	/**
	 * @param serviceName
	 * @return address of service for current environment from data source
	 */
	public String getServiceAddress(String serviceName);

	
	/**
	 * @return source from Environment variables are taken. This could be file path 
	 */
	public String dataSource();
	
}
