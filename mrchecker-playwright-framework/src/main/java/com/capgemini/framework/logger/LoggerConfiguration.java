package com.capgemini.framework.logger;

import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.xml.XmlConfiguration;

public class LoggerConfiguration extends XmlConfiguration {
    public LoggerConfiguration(LoggerContext context, ConfigurationSource configSource) {
        super(context, configSource);
    }
}
