Feature: Verify Fleet Management Dashboard Page

  Background: 
    Given User should be in Fleet Management Login Page

  Scenario: To Verify Fleet Management Login WebPage With Valid Credentials
    When User should Enter userName and password
    And User should Click Login button
    Then User should validate Login success message

