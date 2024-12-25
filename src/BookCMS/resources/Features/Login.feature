# Author: Phuong Anh
# Date: 20/08/2024
# Description: Login screen testing scenario

@SmokeScenario
@LoginPage
Feature: Login page

  @Login @UICase
  Scenario: Case Login page UI
    Given Open Chrome and login page
    When Login page title
    When Login form UI
    When Login logo
    When Login content text
    When Login: Default email input
    When Login: Default password input
    When Default forgot password
    When Default button Login

  @Login @RequireInputEmail
  Scenario: Case Check requite email input
    Given Open Chrome and login page
    When Login: Default email input
    And Click button Login
    And Login: Click background screen
    Then Login: Required error message under email input

  @Login @ValidEmailInput
    Scenario Outline: Case Check valid email input
    Given Open Chrome and login page
    When Login: Enter email input value "<email>"
    And Login: Clear value email input
    And Login: Click background screen
    Then Login: Email format error message

    Examples:
      | email |
      | admin |

  @Login @RequireInputPassword
  Scenario: Case Check requite password input
    Given Open Chrome and login page
    When Login: Default password input
    And Click button Login
    And Login: Click background screen
    Then Login: Required error message under password input

  @Login @ClickLinkTextForgotPass
  Scenario: Case Check click link text Forgot password
    Given Open Chrome and login page
    When Default forgot password
    When Click forgot password
    Then Redirect to Forgot password page

# Chức năng login
  @Login @EmailMustBeAnEmail
  Scenario Outline: Case Enter email must be an email
    Given Open Chrome and login page
    When Login: Enter email input value "<e>"
    When Login: Enter password input value "<p>"
    And Click button Login
    Then Login: Message Email must be an email

    Examples:
    | e | p |
    | anh@h | 1234567 |

  @Login @AccountDoesNotExist
  Scenario Outline: Case Account does not exist
    Given Open Chrome and login page
    When Login: Enter email input value "<e>"
    When Login: Enter password input value "<p>"
    And Click button Login
    Then Login: Message Account does not exist

    Examples:
      | e | p |
      | anh@hd.vn | 1234567 |

  @Login @EnterWrongPassword
  Scenario Outline: Case Enter wrong password
    Given Open Chrome and login page
    When Login: Enter email input value "<e>"
    When Login: Enter password input value "<p>"
    And Click button Login
    Then Login: Message Wrong password

    Examples:
      | e | p |
      | admin@gmail.com | 1234567 |


  @LoginSuccessful
  Scenario Outline: Case Login successful
    Given Open Chrome and login page
    When Login: Default email input
    When Login: Enter email input value "<e>"
    When Login: Default password input
    When Login: Enter password input value "<p>"
    And Click button Login
    Then Login successful
    And Login: Close browser

    Examples:
      | e | p |
      | admin@gmail.com | 123456 |