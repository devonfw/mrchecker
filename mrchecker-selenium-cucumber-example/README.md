MrChecker Selenium Cucumber (defaults)
-------------------

This project is an example of usage MrChecker Selenium module with Cucumber runner with all default settings.

## How to use

You can use this project to start your own:

* Copy example project to your own location

* Rename project (check also pom.xml values)

* Replace example classes and test data

## Project settings

| Setting    | Value     |
|------------|-----------|
| Module     | Selenium  |
| Runner     | Cucumber  |
| Parallel   | Scenarios |
| Encryption | Yes       |

#### Selenium module specific settings

| Setting             | Value       |  
|---------------------|-------------|
| Page init           | PageFactory | 
| Use page load()     | false       |  
| Use page isLoaded() | false       | 
| Element types       | Yes         |
| Driver close level  | &ast; test  |

&ast; MrChecker will close WebDriver after each scenario even if they are in the same feature file. \
  It is related with Cucumber approach to separate each scenario and miss of feature level events. \
  More details [here](https://github.com/cucumber/cucumber-jvm/issues/2702)

#### Remarks

Please get familiar with the content of below-mentioned files:

* *resources/settings.properties:* List of properties for each test module.

* *resources/secretData:* File used to encrypt/decrypt sensitive data used during test execution. \
  For safety please do not keep this file in repository.

* *resources/environments/environments.csv:* List of variables with different values based of used test environment. \
  Some of them might be encrypted with password from secretData file.

* *test/resources/allure.properties:* Allure report properties

* *test/resources/junit-platform.properties:* JUnit and Cucumber properties

* *pom.xml:* Maven project configuration