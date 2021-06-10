<span style="color:DodgerBlue">MrChecker examples project</span>
-------------------

This repository contains examples demonstrating how to use MrChecker to test application.

## How to use

To use the examples the following steps have to be followed:

* Download the code (you can clone it via git or download a zip from [here](https://downgit.evecalm.com/#/home?url=https://github.com/devonfw/mrchecker/tree/develop/example)).

* Import it as Maven project using and IDE like Eclipse or IntelliJ.

* Run example tests.

## What is included?

#### Commad Line Interface runners

* *clirunners:* A ready to use test running and reporting bat files.

#### Commons

* *resources/settings.properties:* List of properties for every test module.

* *resources/secretData:* File used to encrypt/decrypt sensitive data used during test execution. <span style="color:FireBrick">**For safety reasons please do not keep this file in your repository**</span> , read more - [How to keep sensitive data](https://github.com/devonfw/mrchecker/blob/develop/documentation/Who-Is-MrChecker/Test-Framework-Modules/Core-Test-Module-Externalize-test-environment-DEV-QA-SIT-PROD.asciidoc#encrypting-sensitive-data).

* *resources/enviroments/environments.csv:* List of variables with different values based of used test environment. Read more - [How to use test environments](https://github.com/devonfw/mrchecker/blob/develop/documentation/Who-Is-MrChecker/Test-Framework-Modules/Core-Test-Module-Externalize-test-environment-DEV-QA-SIT-PROD.asciidoc#system-under-test-environments).

* *test/resources/junit-platform.properties:* A JUnit settings file for both native and Cucumber runners. 

* *test/resources/allure.properties:* An Allure settings file. 

#### Classic Junit structure

* *main/java/com.capgemini.mrchecker/<Module_name>:* Contains a page object template (MainPage.java) with the necessary methods.

* *test/java/com.capgemini.mrchecker/<Module_name>:* Contains a test template (MainPageTest.java) with methods recommended building a basic test.


#### Cucumber structure

* *test/java/com.capgemini.mrchecker/cucumber:*  A directory to keep Cucumber tests related files.

* *test/java/com.capgemini.mrchecker/cucumber/features:* A directory to keep Cucumber feature files for each test module and an example (basicScenario.features). It also stores Cucumber test runners e.g. RunGuiCucumberTests.java.

* *test/java/com.capgemini.mrchecker/cucumber/stepdefs:* A directory to keep Java files with Cucumber steps definition for each test module.

* *test/java/com.capgemini.mrchecker/cucumber/shared:* A directory to keep Cucumber files with shared data tables for each test module.

* *test/java/com.capgemini.mrchecker/cucumber/hooks:* A directory to keep Cucumber to JUnit5 glue classes. **DO NOT REMOVE**.


#### The transition to JUnit5 has been done recently. The examples for JUNIT4 are available [here](https://github.com/devonfw/mrchecker/tree/junit4)