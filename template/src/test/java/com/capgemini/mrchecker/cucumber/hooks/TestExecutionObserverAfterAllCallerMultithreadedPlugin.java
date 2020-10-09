package com.capgemini.mrchecker.cucumber.hooks;

import com.capgemini.mrchecker.test.core.TestExecutionObserver;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseFinished;

public class TestExecutionObserverAfterAllCallerMultithreadedPlugin implements ConcurrentEventListener {
	@Override
	public void setEventPublisher(EventPublisher eventPublisher) {
		eventPublisher.registerHandlerFor(TestCaseFinished.class, teardown);
	}
	
	private final EventHandler<TestCaseFinished> teardown = event -> {
		TestExecutionObserver.getInstance()
				.afterAll(null);
	};
}