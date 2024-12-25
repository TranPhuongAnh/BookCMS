# Author: Phuong Anh
# Date: 26/08/2024
# Description: Forgot Password screen testing scenario

@SmokeScenario
Feature: Forgot Password page

  @ForgotPass @UI
  Scenario: Case Forgot password page UI
    Given Open forgot password page
    And Forgot pass: Click background screen
    When Forgot password page title
    When Forgot password page UI
    When Forgot password logo
    When Forgot password content
    When Forgot pass: Default email input
    When Default button Send link
    When Forgot pass: Default login back

  @ForgotPass @RequireInputEmail
  Scenario: Case Check requite email input
    Given Open forgot password page
    And Forgot pass: Click background screen
    When Forgot pass: Default email input
    And Click button Send link
    And Forgot pass: Click background screen
    Then Forgot pass: Required error message under email input

  @ForgotPass @ValidEmailInput
  Scenario Outline: Case Check valid email input
    Given Open forgot password page
    When Forgot pass: Enter email input value "<email>"
    And Forgot pass: Clear value email input
    And Forgot pass: Click background screen
    Then Forgot pass: Email format error message
    Examples:
      | email |
      | abcde |

  @ForgotPass @ClickLinkTextLoginBack
  Scenario: Case Check click link text Login back
    Given Open forgot password page
    When Forgot pass: Default login back
    When Forgot pass: Click login back
    Then Forgot pass: Redirect to Login page

  #Chức năng Gửi liên kết
  @ForgotPass @EmailMustBeAnEmail
  Scenario Outline: Case Enter email must be an email
    Given Open forgot password page
    When Forgot pass: Enter email input value "<e>"
    And Click button Send link
    Then Forgot pass: Message Email must be an email

    Examples:
      | e |
      | anh@h |

  @ForgotPass @AccountDoesNotExist
  Scenario Outline: Case Account does not exist
    Given Open forgot password page
    When Forgot pass: Enter email input value "<e>"
    And Click button Send link
    Then Forgot pass: Message Account does not exist

    Examples:
      | e |
      | anh@hd.vn |

  @ForgotPass @SendLinkSuccessful
  Scenario Outline: Case Send link successful
    Given Open forgot password page
    And Forgot pass: Click background screen
    When Forgot pass: Default email input
    When Forgot pass: Enter email input value "<e>"
    And Click button Send link
    Then Send link successful
    And Forgot pass: Close browser

    Examples:
      | e |
      | phuonganh.tran@vmgmedia.vn |