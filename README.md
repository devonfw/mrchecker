MrChecker template
-------------------

This repository includes a template to make test using MrChecker and an example.

## How to use

To use this template the following steps have to be followed:

* Download the template.

* Import it as Maven project using and IDE like Eclipse or InteliJ.

* Write the code to test an application.

## What is included?

#### Commons

* *resources/settings.properties:* List of properties for each test module.

* *resources/secretData:* File used to encrypt/decrypt sensitive data used during test execution. For safety please do not keep this file in repository, read more [How to keep sensitive data](https://github.com/devonfw/devonfw-testing/wiki/Different-environments#encrypting-sensitive-data) .

* *resources/environments/environments.csv:* List of variables with different values based of used test environment. Read more [How to use test environments](https://github.com/devonfw/devonfw-testing/wiki/Different-environments#system-under-test-environments).


#### Classic Junit structure

* *test/java/com.capgemini.mrchecker/<Module_name>/junit:* Folder which includes a java file (MainTest.java) with methods recommended to build a basic test.

* *main/java/com.capgemini.mrchecker/<Module_name>/:* Contains a template (MainPage.java) with methods necessary to build a basic page object.

#### Cucumber structure

* *test/java/com.capgemini.mrchecker/cucumber:* Folder to store the cucumber tests (MainCucumberTest.java).

* *test/java/com.capgemini.mrchecker/cucumber/features:* Directory to keep cucumber files with features for each test module (basicScenario.features).

* *test/java/com.capgemini.mrchecker/cucumber/stepdefs:* Directory to keep cucumber files with steps definition for each test module.

* *test/java/com.capgemini.mrchecker/cucumber/shared:* Directory to keep cucumber files with shared data tables for each test module.





The example folder also has a the following tests: 

* *Test Data Driven (TDD)* Inside test/java/com.capgemini.mrchecker/core/datadriven.

* *Behaviour Driven Development (BDD):* Situated on test/java/com.capgemini.mrchecker/cucumber.
