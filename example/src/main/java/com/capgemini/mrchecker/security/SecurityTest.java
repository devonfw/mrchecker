package com.capgemini.mrchecker.security;

import com.capgemini.mrchecker.security.session.ISessionManager;
import com.capgemini.mrchecker.security.session.SessionManagerModule;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.Page;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * The class offers security relevant functionality to be shared among test cases e.g. session management.
 *
 * @author Marek Puchalski, Capgemini
 */
public abstract class SecurityTest extends BaseTest {
	
	private static final Injector	injector	= Guice.createInjector(new SessionManagerModule());
	private final ISessionManager	sessionManager;
	
	public SecurityTest() {
		super();
		sessionManager = injector.getInstance(ISessionManager.class);
		((Page) sessionManager).addToTestExecutionObserver();
	}
	
	public ISessionManager getSessionManager() {
		return sessionManager;
	}
}
