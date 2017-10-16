package com.capgemini.ntc.selenium.tests.cucumber;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;
import org.junit.runners.model.RunnerScheduler;

import com.googlecode.junittoolbox.WildcardPatternSuite;

public class ParallelSuite extends WildcardPatternSuite {

    public ParallelSuite(Class<?> klass, RunnerBuilder builder) throws InitializationError {

        super(klass, builder);

        setScheduler(new RunnerScheduler() {

            private final ExecutorService service = Executors.newCachedThreadPool();
            		
            public void schedule(Runnable childStatement) {
                service.submit(childStatement);
            }

            public void finished() {
                try {
                    service.shutdown();
                    service.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                } 
            }
        });
    }
}