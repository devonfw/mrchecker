package com.capgemini.framework.logger;

import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Order;
import org.apache.logging.log4j.core.config.plugins.Plugin;

/**
 * https://logging.apache.org/log4j/log4j-2.4/manual/customconfig.html Combining Configuration File with Programmatic
 * Configuration
 */
@Plugin(name = "LoggerConfigurationFactory", category = "ConfigurationFactory")
@Order(10)
public class LoggerConfigurationFactory extends ConfigurationFactory {
    public static final String[] SUFFIXES = new String[]{".xml", "*"};

    @Override
    public String[] getSupportedTypes() {
        return SUFFIXES;
    }

    @Override
    public Configuration getConfiguration(LoggerContext loggerContext, ConfigurationSource source) {
        return new LoggerConfiguration(loggerContext, source);
    }
}
