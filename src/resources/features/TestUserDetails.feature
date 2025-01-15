Feature: Test User Details

  Background:
    Given The user is successfully logged in to the web page
    And The user navigates to Account Details

  Scenario: The user would like to navigate to the "Account Details" and verify the correct email was used.
    When User is under Account Details tab
    Then The email address displayed under the Email Address textbox matches the expected email


  Scenario: The user would like to enter first and last names, make sure they are correct in the input boxes, and save.
    And The user enters valid first name in the 'First name' textbox
    And The user enters valid last name in the 'Last name' textbox
    Then The first name displayed under the "First name" textbox matches the entered name
    And The last name displayed under the "Last name" textbox matches the entered name
    When The user clicks on the "Save changes" button
    Then A success message is displayed confirming the changes were saved


  Scenario: User logs out successfully
    And Clicks on the Logout button
    Then The user is redirected to the login page
    And A logout confirmation message is displayed