Feature: Inventory Management
  Background: 
    Given User should be navigated into the Fleet Management system Dashboard with valid credentials
    
  Scenario: Create a new part in Inventory Management
    When User navigates to the Inventory Management section
    When User clicks on the Parts module in the navbar
    And User clicks on the Create Part button
    And User fills in the following data:
      | Field Name        | Value          |
      | Part Name         | New Part       |
      | Part Number       | NP12345        |
      | Description       | Sample Part    |
      | Quantity          | 100            |
      | Unit Price        | 10.00          |
    And User clicks on the Submit button
    Then User should see the New Part displayed in the parts table
