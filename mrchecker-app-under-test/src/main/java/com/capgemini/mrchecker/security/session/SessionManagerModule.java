package com.capgemini.mrchecker.security.session;

import com.google.inject.AbstractModule;

/**
 * Session manager configuration.
 *
 * @author Marek Puchalski, Capgemini
 */
public class SessionManagerModule extends AbstractModule {
	
	@Override
	protected void configure() {
		bind(ISessionManager.class).to(SessionManager.class);
	}
	
}
