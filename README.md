MrChecker examples projects
-------------------

This repository includes examples to prepare tests using MrChecker.

## How to use

To use an example the following steps have to be followed:

* Copy example project to your own location

* Rename project (check also pom.xml values)

* Replace example classes and test data

## How to run

Command:\
`mvn clean test -Dthread.count=1 -Denv=ENV1 allure:report`

Additional properties:
- -Dthread.count: number of threads used for test run (1 by default)
- -Denv: environment that you want to use (see environments.csv)
- -Dbrowser: browser that you want to use (in UI based tests)
- -Dgroups: gruops of test to run mark by the same tag
- -Dtest: test class name that you want to run
- allure:report: generate report (allure:serve - generate and open) 


## What is included?

#### Commons


* *resources/settings.properties:* List of properties for each test module.

* *resources/secretData:* File used to encrypt/decrypt sensitive data used during test execution. For safety please do not keep this file in repository, read more [How to keep sensitive data](https://github.com/devonfw/mrchecker/wiki/Core-Test-Module-Different-Environments#encrypting-sensitive-data) .\
  For safety please do not keep this file in repository.

* *resources/environments/environments.csv:* List of variables with different values based of used test environment. Read more [How to use test environments](https://github.com/devonfw/mrchecker/wiki/Core-Test-Module-Different-Environments#system-under-test-environments). \
Some of them might be encrypted with password from secretData file.
* *test/resources/allure.properties: Allure report properties

* *test/resources/junit-platform.properties: JUnit properties

* *pom.xml: Maven project configuration


#### Classic Junit structure

* *test/java/com.capgemini.mrchecker/<Module_name>:* Folder which includes java files with methods recommended to build a basic test.

* *main/java/com.capgemini.mrchecker/<Module_name>/:* Contains templates with methods necessary to build a basic page object.

#### Cucumber structure

* *test/java/com/capgemini/mrchecker/<Module_name>/example/test:* Folder to store the cucumber tests.

* *test/resources/features:* Directory to keep cucumber files with features for each test module.

* *test/java/com/capgemini/mrchecker/<Module_name>/example/test/stepdefs:* Directory to keep cucumber files with steps definition for each test module.







#### All examples are written using JUNIT5. Examples for JUNIT4 are available [here](https://github.com/devonfw/mrchecker/tree/junit4)
