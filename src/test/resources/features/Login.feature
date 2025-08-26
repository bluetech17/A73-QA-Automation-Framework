Feature: Login Feature

  Scenario: Positive login Scenario
    Given I open the browser
    When I enter my email "arianna.gunn@testpro.io"
    And I enter my password "Seventeen17"
    And I click submit
    Then I am logged in