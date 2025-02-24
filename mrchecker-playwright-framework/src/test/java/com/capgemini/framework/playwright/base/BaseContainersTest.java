package com.capgemini.framework.playwright.base;

import com.capgemini.framework.playwright.BaseTest;
import com.capgemini.framework.playwright.infrastructure.resources.MyTestResources;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@QuarkusTest
@QuarkusTestResource(value = MyTestResources.class, parallel = false)
public abstract class BaseContainersTest extends BaseTest {

}

