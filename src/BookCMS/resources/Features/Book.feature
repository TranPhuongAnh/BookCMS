# Author: Phuong Anh
# Date: 01/10/2024
# Description: Book screen testing scenario

@SmokeScenario
Feature: Book manager page

# Header
  @Book @HeaderUI
  Scenario: Check header UI
    Given Book: Open book screen
    Then Book: Header UI
    When Book: Click user
    Then Book: Popup person

# Filter
  @BookFilter @DefaultUI
  Scenario: Check filter default UI
    Given Book: Open book screen
    Then Book filter: Default UI
    Then Book filter: Default detail UI

  @BookFilter @AdvanceFilterUI
  Scenario: Check advance filter UI
    Given Book: Open book screen
    And Book filter: Click advance filter
    Then Book filter: Advanced filter UI
    Then Book filter: Advanced filter detail UI

  @BookFilter @FocusInput
  Scenario: Check focus input filter
    Given Book: Open book screen
    When Book filter: Focus input search
    Then Book filter: Check focus input search
    When Book filter: Click advance filter
    And Book filter: Focus combobox type
    Then Book filter: Check focus combobox type
    When Book filter: Focus combobox status
    Then Book filter: Check focus combobox status
    When Book filter: Focus combobox author
    Then Book filter: Check focus combobox author
    When Book filter: Focus combobox sort by
    Then Book filter: Check focus combobox sort by

  @BookFilter @EnterType
  Scenario: Book filter: Enter input Type
    Given Book: Open book screen
    And Book filter: Click advance filter

  @BookAPI @ValueToken
  Scenario: API login: Check value token
    When Book API: Get token login

  @BookAPI @ValueCategory
  Scenario: API login: Check value category
    When Book API Result: Check data category field

# Button Add book
  @Book @ButtonAddBookUI
  Scenario: Check button Add book UI
    Given Book: Open book screen
    Then Book: Display button Add book
    Then Book: Button Add book UI

  @Book @ClickButtonAddBook
  Scenario: Check click button Add book
    Given Book: Open book screen
    When Book: Click button Add book
    Then Book: Check open Add book screen

# Table header
  @Book @TableHeader
  Scenario: Check table header ui
    Given Book: Open book screen
    Then Book: Table header display
    Then Book: Table header ui

