package com.capgemini.mrchecker.selenium.example.base;

import com.capgemini.mrchecker.test.core.BaseTest;
import org.opentest4j.TestAbortedException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public abstract class BaseTestGUI extends BaseTest {
    private static final Lock setupLock = new ReentrantLock();
    private static final Lock teardownLock = new ReentrantLock();

    // Each test tear down (post) method
    public abstract void tearDownTest();

    // Each test setup (pre) method
    public abstract void setUpTest();

    @Override
    // General setup (pre) method used in all tests
    // Helpful if there is a need to prepare common behavior - for example exceptions handling
    public final synchronized void setUp() {
        try {
            setupLock.lock();try {
            setUpTest();
        } catch (Throwable throwable) {
            throw new TestAbortedException("[PRE] " + throwable.getMessage(), throwable);
        } finally {
            setupLock.unlock();
        }
    }

    @Override
    // General tear down (post) method used in all tests
    // Helpful if there is a need to prepare common behavior - for example exceptions handling
    public final synchronized void tearDown() {
        try {
            teardownLock.lock();try {
            tearDownTest();
        } catch (Throwable throwable) {
            throw new TestAbortedException("[POST] " + throwable.getMessage(), throwable);
        } finally {
            teardownLock.unlock();
        }
    }
}
