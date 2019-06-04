@Test
Feature: My Thai Star book table page

  Background:
    Given I am on the book table page
    And I click on Date and time field

  Scenario Outline: Test book a table form with different data
    And I set <date> date and <hour> hour and <minutes> minutes
    When I complete action
    And I set name field with <name> value
    And I set email field with <email> value
    And I set table guests field with <guests> value
    And I check accept terms checkbox
    When I click book table button
    Then I should see <status> of reservation

    Examples:
      | date    | hour    | minutes | name  | email                | guests  | status  |
      | current | valid   | valid   | User1 | mythaitest@gmail.com | valid   | success |
      | future  | valid   | valid   | User1 | mythaitest@gmail.com | valid   | success |
      | current | invalid | valid   | User1 | mythaitest@gmail.com | valid   | failed  |
      | current | valid   | invalid | User1 | mythaitest@gmail.com | valid   | success |
      | current | valid   | valid   | User1 | mythaitest@gmail.com | invalid | failed  |
      | current | valid   | valid   | User1 | mythaitest@gmail.com | double  | success |
      | current | valid   | valid   | User1 | mythaitest@gmail.com | valid   | success |

  @tag
  Scenario: Test book a table form with empty name
    And I set actual date and time
    When I complete action
    And I set email field with 'mythaitest@gmail.com' value
    And I set table guests field with '5' value
    When I check accept terms checkbox
    Then I can not click book table button
    But I should see the underlined name field as required

  Scenario: Test book a table form with invalid email
    And I set actual date and time
    When I complete action
    And I set name field with 'User1' value
    And I set email field with 'test@' value
    And I check accept terms checkbox
    Then I can not click book table button
    But I should see the underlined email field as valid

  Scenario: Test book a table form without Accept terms
    And I set actual date and time
    When I complete action
    And I set name field with 'NewUser' value
    And I set email field with 'mythaitest@gmail.com' value
    And I set table guests field with '5' value
    Then I should see book table button as invisible

 