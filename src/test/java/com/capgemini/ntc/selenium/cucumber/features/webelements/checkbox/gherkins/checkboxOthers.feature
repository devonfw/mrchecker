Feature: Web Element - Checkbox's other funcionalities

  Scenario: test set all Checkboxes
    Given I'm as unlogged user on Registraion page
    When I set all hobby checkboxes
    Then I should see set all hobby checkboxes

  Scenario: test unset all Checkboxes
    Given I'm as unlogged user on Registraion page
    When I unset all hobby checkboxes
    Then I should see unset all hobby checkboxes

    Scenario: test number of checkboxes
      Given I'm as unlogged user on Registraion page
      Then I should see 3 hobby checkboxes

