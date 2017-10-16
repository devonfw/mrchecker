Feature: Web Element - Checkbox by value

  Scenario Outline: test set Checkbox by value
    Given I'm as unlogged user on Registraion page
    When I set hobby checkbox with value <value>
    Then I should see set hobby checkbox with value <value>

    Examples:
      | value   |
      | reading |

  Scenario Outline: test unset Checkbox by value
    Given I'm as unlogged user on Registraion page
    When I unset hobby checkbox with value <value>
    Then I should see unset hobby checkbox with value <value>

    Examples:
      | value   |
      | reading |