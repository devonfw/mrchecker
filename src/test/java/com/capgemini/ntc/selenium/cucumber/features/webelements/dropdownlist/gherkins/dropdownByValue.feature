Feature: Web Element - Dropdown List By value

  Scenario Outline: select Dropdown List by value
    Given I'm on Registration page as unlogged user
    When I select country dropdown list element with value <value>
    Then I should see selected country dropdown list element with value <value>

    Examples:
      | value   |
      | Vietnam |