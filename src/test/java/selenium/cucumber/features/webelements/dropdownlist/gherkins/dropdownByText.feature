Feature: Web Element - Dropdown List By text

  Scenario Outline: select Dropdown List by text
    Given I'm on Registration page as unlogged user
    When I select country dropdown list element with text <text>
    Then I should see selected country dropdown list element with text <text>

    Examples:
      | text    |
      | Vietnam |