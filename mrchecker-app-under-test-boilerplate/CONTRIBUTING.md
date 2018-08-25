# Pull request general verifications

## Jenkins job link

Example Jenkins job link   http://10.40.232.131:8081/job/Training/job/selenium_workshop/197/allure/

## Branch naming convention

Example:  *feature/LUSTEF-Zad12*


# Every file

## Remove commented out lines of code and empty lines

Remove ```// Commented lines``` and empty lines

## Enable file formatting 

By default file formatting should be already uploaded to Eclipse.

* Eclipse -> Preferences -> Java -> Code Style -> Formatter -> Import [eclipse_format_code_standards.xml](https://bitbucket.org/capntc/selenium_workshop/src/cc4f1840dd7b2831907526c47bdf247ab74903a2/README/eclipse_format_code_standards.xml?at=develop)

* How to use: Ctrl + Shift + F

## Name of java package. Always small letters

The prefix of a unique package name is always written in all-lowercase ASCII letters.

Example   com.example.selenium.cmu.cs.bovik.cheese

## Name of java class

Class names should be nouns, in mixed case with the first letter of each internal word capitalized. Try to keep your class names simple and descriptive. Use whole words-avoid acronyms and abbreviations (unless the abbreviation is much more widely used than the long form, such as URL or HTML).

Example:   

* class Raster;   

* class ImageSprite;

## Name of methods

Methods should be verbs, in mixed case with the first letter lowercase, with the first letter of each internal word capitalized.

Example: 

* run(); 

* runFast(); 

* getBackground();

# Inside Test file

List of rules during code review for file structure - Test class files

## Test business steps 

Validate all business test case steps

## No Selenium operations in Test Classes

Test Class file can only execute methods/actions from Page class. 

As the result of this, Test class can use only *asserts* against this Page methods

## Test method naming convention


# Inside Page file

List of rules during code review for file structure - Page class files

## No junit/test operation in file 

As Page class file mimics actions on web page, therefore no "test" operation can be done here. 

For any test action, Test class with test methods can be used. 

## Method/actions naming convention.

Method convention described in details, link: [Naming convention](https://bitbucket.org/capntc/selenium_workshop/src/e229eba16f7915c6bb52838c9a9b9923be46d2a3/README/namingConvention.docx?at=develop)

## Method/actions can exit when all tasks are finished

Some actions such as *click* might need to: 

* wait to load page```getDriver().waitForPageLoaded();```

* return new Page object.

## No explicitly *sleep* steps

It is not allowed to use Thread.sleep steps. 

Instead of this pleas use *waitUntil* 

List available *waitUntils*: 

* getDriver().waitForPageLoaded();

* getDriver().waitForElement(selector);

* getDriver().waitForElementVisible(selector);

* getDriver().waitUntilElementIsClickable(selector);

## Selector variable - CSS , id, class

Type of selector: every type ( `By.cssSelector` , `By.className` , ... )  *except* `By.xpath`

`private static final By selectorResultBanner = By.cssSelector("#hdtb-msb-vis");`

Link to css selector [tutorial](https://bitbucket.org/capntc/selenium_workshop/src/e229eba16f7915c6bb52838c9a9b9923be46d2a3/README/cssSelector.docx?at=develop)




## Selector variable type convention

Type of variable: *private static final*

`private static final By selectorResultBanner = By.cssSelector("#hdtb-msb-vis");`




## Selector variable naming convention

Naming: selector[ElementName]

`private static final By selectorResultBanner = By.cssSelector("#hdtb-msb-vis");`




