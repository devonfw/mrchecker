package com.capgemini.ntc.test.core.base.runtime;

import com.capgemini.ntc.test.core.base.runtime.provider.SystemRuntimeParameters;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class RuntimeParametersModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(RuntimeParameters.class).to(SystemRuntimeParameters.class).in(Scopes.SINGLETON);
	}
}
