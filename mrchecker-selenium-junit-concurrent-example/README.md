MrChecker Selenium Junit concurrent
-------------------

This project is an example of usage MrChecker Selenium module with concurrent Junit.

## How to use

You can use this project to start your own:

* Copy example project to your own location

* Rename project (check also pom.xml values)

* Replace example classes and test data

* Run tests with Maven using following command: clean test -Dthread.count=6

## Project settings

| Setting    | Value    |
|------------|----------|
| Module     | Selenium |
| Runner     | JUnit    |
| Parallel   | Tests    |
| Encryption | Yes      |

#### Selenium module specific settings

| Setting             | Value       |  
|---------------------|-------------|
| Page init           | PageFactory | 
| Use page load()     | false       |  
| Use page isLoaded() | false       | 
| Element types       | Yes         |
| Driver close level  | class       |

#### Remarks

Please get familiar with the content of below-mentioned files:

* *resources/settings.properties:* List of properties for each test module.

* *resources/secretData:* File used to encrypt/decrypt sensitive data used during test execution. \
For safety please do not keep this file in repository.

* *resources/environments/environments.csv:* List of variables with different values based of used test environment. \
Some of them might be encrypted with password from secretData file.

* *test/resources/allure.properties:* Allure report properties

* *test/resources/junit-platform.properties:* JUnit properties

* *pom.xml:* Maven project configuration