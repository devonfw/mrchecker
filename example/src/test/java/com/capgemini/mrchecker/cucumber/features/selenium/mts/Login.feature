@all @mts @ui @waiter
Feature: Login as waiter
  As a waiter I want to login in order to see a list of reservations

  Scenario: Login as Willy Waiter
    Given The My Thai start page has been opened
    When I login as Willy Waiter
    Then My login name is shown
