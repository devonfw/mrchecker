![License](https://img.shields.io/github/license/devonfw/mrchecker)
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2Fdevonfw%2Fdevonfw-testing.svg?type=shield)](https://app.fossa.io/projects/git%2Bgithub.com%2Fdevonfw%2Fdevonfw-testing?ref=badge_shield) 
[![Maven Central](https://img.shields.io/maven-central/v/com.capgemini.mrchecker/mrchecker-core-module)](https://mvnrepository.com/artifact/com.capgemini.mrchecker)

Mr. Checker methodology examples projects
-------------------

This repository includes:
* Playwright based test automation framework created according to Mr. Checker methodology
* Examples to prepare tests using Mr. Checker (modules version)

## The evolution of Mr. Checker from project-specific to a broader vision

* **Born as project-specific framework** 

  Originally designed for a single project, Mr. Checker's modular architecture enabled adaptation and successful implementation across diverse project contexts and technologies.

* **Identifying Repeatable Patterns**

  Through practical use, consistent and effective patterns emerged, paving the way for a more standardized approach.

* **Foundation for a Methodology**

  Leveraging the identified patterns, robust and consistent methods and procedures were established, forming the core of the Mr. Checker Methodology that guides our automation practices.


## Mr. Checker: Game-Changing Method

* **Faster time to market**

  Upfront feasibility validation, efficient project execution, high quality feedback

* **Proven in Long-Term Projects**

  Extensive use across multiple projects, successfully developed dozens of professionals, adaptable to diverse technologies

* **Growing Consistent Automation Capacity**

  Easy adoption, ready-to-use assets, skilled workforce, years of collective experience, skilled architects


## Mr. Checker Test Containers support
***Docker environment is needed to run TestContainers***
* Running tests with Test Containers requires:
  * Adding container to resources in **MyTestResources** class
  * Extending test with the **BaseContainersTest**
* Using Mock server
  * Mock server starts as part of TestContainers tests 
  * Mocking server rules can be set in the **MyMockServerClient** class
    * Examples included in the **MyMockServerClient** class





