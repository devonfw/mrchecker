@FeatureLevelTag
@Login
Feature: Login page example from Demo QA

  @ID001
  @Done
  @ScenarioLevelTag
  Scenario: Use example login credentials
    Given Demo QA Login page is opened
    Then Verify that output label is empty
    When Enter example login credentials
    Then Verify that output label has proper text

  @ID002
  @Done
  @ScenarioLevelTag
  Scenario: Verify visibility of elements
    Given Demo QA Login page is opened
    Then Verify Username input is visible
    Then Verify Password input is visible
    Then Verify Login button is visible