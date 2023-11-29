@FeatureLevelTag
@Links
Feature: Links page example from Demo QA

  @ID003
  @Done
  @ScenarioLevelTag
  Scenario: Use Home link
    Given Demo QA Links page is opened
    Then Verify that only one tab is opened
    When Use Home link
    Then Verify that two tabs are opened
    Then Verify if Home page is opened on active tab
    When Close current tab
    Then Verify that only one tab is opened
    Then Verify Links page is opened

  @ID004
  @Done
  @ScenarioLevelTag
  Scenario: Verify visibility of elements
    Given Demo QA Links page is opened
    Then Verify Home link is visible