Feature: Web Element - Dropdown List's other funcionalities

  Scenario: Number of possible value in dropdown list
    Given I'm on Registration page as unlogged user
    And I should see country dropdown list
    Then I should see country dropdown list with 204 possible values

  Scenario: Test Number of selected values in time
    Given I'm on Registration page as unlogged user
    When I select country dropdown list element with index 5
    And I select country dropdown list element with index 24
    Then I should see 1 selected country dropdown list element


