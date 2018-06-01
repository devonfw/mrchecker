package com.capgemini.mrchecker.test.core;

public interface ITestObserver {
	public void onTestSuccess();
	
	public void onTestFailure();
	
	public void onTestFinish();
	
	public void onTestClassFinish();
	
	public void addObserver();
	
	public ModuleType getModuleType();
}
