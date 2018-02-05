package com.capgemini.ntc.security;

import com.capgemini.ntc.security.core.BasePage;
import com.capgemini.ntc.security.session.ISessionManager;
import com.capgemini.ntc.security.session.SessionManagerModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * The class offers security relevant functionality to be shared among test cases e.g. session management.
 *
 * @author Marek Puchalski, Capgemini
 */
public abstract class SecurityPage extends BasePage {
	
	private static final Injector	injector	= Guice.createInjector(new SessionManagerModule());
	private ISessionManager				sessionManager;
	
	public SecurityPage() {
		super();
		sessionManager = injector.getInstance(ISessionManager.class);
	}
	
	public ISessionManager getSessionManager() {
		return sessionManager;
	}
}
