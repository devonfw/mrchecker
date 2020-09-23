@all
@Test
Feature: Checkboxes manipulation

  Scenario Outline: Successful checkbox <checkboxAction>
    Given CheckboxesPage is opened
    And The "1" checkbox initial state is "<initialState>"
    When I click on the "1" checkbox
    Then The "1" checkbox is in "<finalState>"

    Examples:

      | checkboxAction | initialState | finalState |
      | check          | unchecked    | checked    |
      | uncheck        | checked      | unchecked  |
