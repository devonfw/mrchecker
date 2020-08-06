@all
@Test
Feature: Cash Withdrawal

  Scenario: Successful withdrawal from an account in credit
    Given I have deposited $1000 in my account
    When I request $500
    Then $500 should be in my account

  Scenario: One more successful withdrawal from an account in credit
    Given I have deposited $1000 in my account
    When I request $1000
    Then $0 should be in my account

  Scenario: Unsuccessful withdrawal from an account in credit
    Given I have deposited $1000 in my account
    When I request $2000
    Then $0 should be in my account
    