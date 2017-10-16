Feature: Web Element - Checkbox By index

  Scenario Outline: test set Checkbox by index
    Given I'm as unlogged user on Registraion page
    When I set hobby checkbox with index <index>
    Then I should see set hobby checkbox with index <index>

    Examples:
      | index |
      | 0     |

  Scenario Outline: test unset Checkbox by index
    Given I'm as unlogged user on Registraion page
    When I unset hobby checkbox with index <index>
    Then I should see unset hobby checkbox with index <index>

    Examples:
      | index |
      | 1     |