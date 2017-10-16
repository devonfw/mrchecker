Feature: Web Element - Label

  Scenario: Check label's functionalities
    Given I'm on AUTOMATION PRACTICE FORM site as unlogged user
    And I should see text1 Label
    And I check text1 Label type
    Then I should see text1 Label with text: Text1