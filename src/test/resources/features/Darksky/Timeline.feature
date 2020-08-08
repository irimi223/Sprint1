Feature: Timeline increment

  @Sprint1
  Scenario: Verify timeLine is displayed in correct format
    Given I am on darksky home page
    Then I verify timeline is displayed with 2 hours increment with 12 data points
