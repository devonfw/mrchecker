package com.capgemini.mrchecker.webapi.example.config;

// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import org.junit.platform.engine.ConfigurationParameters;
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfiguration;
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfigurationStrategy;

public class CustomStrategy implements ParallelExecutionConfiguration, ParallelExecutionConfigurationStrategy {
	private int threadCount = Integer.parseInt(System.getProperty("thread.count", "1"));
	
	public CustomStrategy() {
	}
	
	public int getParallelism() {
		return this.threadCount;
	}
	
	public int getMinimumRunnable() {
		return 0;
	}
	
	public int getMaxPoolSize() {
		return this.threadCount;
	}
	
	public int getCorePoolSize() {
		return this.threadCount;
	}
	
	public int getKeepAliveSeconds() {
		return this.threadCount;
	}
	
	public ParallelExecutionConfiguration createConfiguration(ConfigurationParameters configurationParameters) {
		return this;
	}
}
