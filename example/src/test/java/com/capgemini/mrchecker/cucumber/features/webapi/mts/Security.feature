@api @waiter
Feature: Api account security
  As a user I want to check authentication state to keep the transaction secure and consistent

  Background:
    When I login with "waiter" and "waiter"

  Scenario: Check current user
    Given The user is logged-in
    When I check for currently logged-in user
    Then The response code is "200"
    And The body item "name" is "waiter"
    And The body item "role" is "WAITER"