package com.capgemini.mrchecker.selenium.example.test.hooks;


import com.capgemini.mrchecker.test.core.TestExecutionObserver;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseFinished;
import io.cucumber.plugin.event.TestRunFinished;

public class TestExecutionObserverAfterAllCallerMultithreadedPlugin implements ConcurrentEventListener {

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestCaseFinished.class, teardown);
        eventPublisher.registerHandlerFor(TestRunFinished.class, teardownAll);
    }

    private final EventHandler<TestCaseFinished> teardown = event -> {
        TestExecutionObserver.getInstance().afterTestExecution(null);
    };

    private final EventHandler<TestRunFinished> teardownAll = event -> {
        TestExecutionObserver.getInstance().afterAll(null);
    };
}