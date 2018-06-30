package com.capgemini.mrchecker.database.core.base.runtime;

import com.capgemini.mrchecker.test.core.base.runtime.RuntimeParametersI;

public enum RuntimeParameters implements RuntimeParametersI {

	DATABASE_TYPE("databaseType", ""),
	JPA_PROVIDER("provider", "hibernate"),
	DATABASE_USERNAME("databaseUsername", "");

	private String paramName;
	private String paramValue;
	private String defaultValue;

	private RuntimeParameters(String paramName, String defaultValue) {
		this.paramName = paramName;
		this.defaultValue = defaultValue;
		setValue();
	}

	@Override
	public String getValue() {
		return this.paramValue;
	}

	@Override
	public String toString() {
		return paramName + "=" + this.getValue();
	}

	@Override
	public void refreshParameterValue() {
		setValue();
	}

	private void setValue() {

		String paramValue = System.getProperty(this.paramName);
		paramValue = isSystemParameterEmpty(paramValue) ? this.defaultValue : paramValue.toLowerCase();

		switch (this.name()) {
		}

		this.paramValue = paramValue;
	}

	private boolean isSystemParameterEmpty(String systemParameterValue) {
		return (null == systemParameterValue || "".equals(systemParameterValue) || "null".equals(systemParameterValue));
	}

}
