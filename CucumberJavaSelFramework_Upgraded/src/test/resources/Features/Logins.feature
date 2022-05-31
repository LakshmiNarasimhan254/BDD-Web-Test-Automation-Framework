Feature: feature to login scenario

  @functional
  Scenario: To check if login is fail with incorrect cred
Given The user is in MyStore Page
    When Clicks on <Sign in> in MyStore Page
    Then the user should be navigated to <Login_MyStorePage>
    And User is in loginpage
    When User enters username and password
      | username          | password     |
      | lakme88@gmail.com | Password@123 |
    And clicks on login
    Then user should be navigated to home page
