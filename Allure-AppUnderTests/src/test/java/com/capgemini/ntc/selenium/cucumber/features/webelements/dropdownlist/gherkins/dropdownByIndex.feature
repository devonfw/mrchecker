Feature: Web Element - Dropdown List By index

  Scenario Outline: select Dropdown List by index
    Given I'm on Registration page as unlogged user
    When I select country dropdown list element with index <index>
    Then I should see selected country dropdown list element with index <index>

    Examples:
      | index |
      | 0     |
      | 1     |