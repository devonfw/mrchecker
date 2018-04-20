package com.capgemini.ntc.database.core.base.runtime;

import com.capgemini.ntc.test.core.base.runtime.RuntimeParametersI;
import com.capgemini.ntc.test.core.logger.BFLogger;

public enum RuntimeParameters implements RuntimeParametersI {

	;

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
