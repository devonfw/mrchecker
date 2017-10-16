Feature: Web Element - InputText

  Scenario Outline: Check inputtext's funcionalities
    Given I'm on Registration page as unlogged user
    And I should see first name InputText
    When I put <text> into first name InputText
    Then I should see first name InputText equal <text>

    Examples:
      | text  |
      | Wanda |