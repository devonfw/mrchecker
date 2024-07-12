## Locators naming conventions
1. Every locator should start with prefix: locator
2. Every locator/selector should have suffix which indicate what kind of element it is:
    - Button
    - Input
    - Link
    - Label
    - Dropdown
    - Checkbox
    - Image
    - Icon
    - Tab
    - Table
    - etc...


Example:
`   locatorSearchButton, locatorWarningIcon



## Methods naming conventions:
Methods start with prefix
- clickOn - for clicking on elements, submiting
- fill - for filling input elements
- check - for asserting, ex. checkVisible, checkEnabled, checkSelected
- select - for selecting dropdown elements
- get - for getting the value of element
- is  - for getting the boolean information about element, ex. isVisible, isEnabled, isSelected

Methods should have suffix which indicates the type of element - the same as locators, example: clickOnSubmitButton, fillNameInput


## Page object conventions:
1. locators should be initialized in initLocators method
2. page should be initializes with PlaywrightFactory.getPage()


## Test conventions:
1. Test should inherit from BaseTest class
2. Test data should be put as a class variable in TestCases class
3. Test should use page object methods to interact with the page