@MobileLogin
Feature: Login functionality on Mobile App

  Scenario Outline: User logs in
    Given User launches the Mobile App
    When User taps the sidebar menu
    And User taps Log In
    And User enters username <username> and password <password>
    And User taps Login button
    Then User should see the <expected_message> is displayed

    Examples: 
      | username          | password | expected_message                     |
      | alice@example.com | 10203040 | Sorry this user has been locked out. |
