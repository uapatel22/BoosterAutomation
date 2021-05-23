Feature: Pledges
  @Pledges
  Scenario: Validate the Business Leaderboard is sorted highest to lowest for the business pledges
    Given I open the browser then navigate to the website
    When I validate there are '5' pledges displayed under Our Business Leaderboard
    And I validate the pledges are displayed in highest to lowest dollar amount
    And I validate if decimals are displayed or not for the pledges below:
      | Pearl            | notDisplayed |
      | businessnotetest | notDisplayed |
      | Businessbetatest | notDisplayed |
      | businessleader30 | isDisplayed  |
      | testbusiness     | notDisplayed |
    Then I validate '1' pledge is displayed after clicking on Show more