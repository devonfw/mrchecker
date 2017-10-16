Feature: Web Element - Checkbox by text

  Scenario Outline: test set Checkbox by text
    Given I'm as unlogged user on Registraion page
    When I set hobby checkbox with text <text>
    Then I should see set hobby checkbox with text <text>

    Examples:
      | text    |
      | Cricket |

  Scenario Outline: test unset Checkbox by text
    Given I'm as unlogged user on Registraion page
    When I unset hobby checkbox with text <text>
    Then I should see unset hobby checkbox with text <text>

    Examples:
      | text    |
      | Cricket |

