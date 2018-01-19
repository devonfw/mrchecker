package com.capgemini.ntc.security;

import com.capgemini.ntc.security.core.BasePage;
import com.capgemini.ntc.security.session.ISessionManager;
import com.capgemini.ntc.security.session.SessionManagerModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * The class offers all security relevant tests common functionality that
 * can be shared among them like e.g. session management.
 *
 * @author Marek Puchalski, Capgemini
 */
public abstract class SecurityPage extends BasePage {
	
	private ISessionManager sessionManager;
	
	public SecurityPage() {
		super();
		Injector injector = Guice.createInjector(new SessionManagerModule());
		sessionManager = injector.getInstance(ISessionManager.class);
	}
	
	public ISessionManager getSessionManager() {
		return sessionManager;
	}
}
