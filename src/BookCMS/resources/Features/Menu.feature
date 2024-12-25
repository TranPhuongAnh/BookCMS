# Author: Phuong Anh
# Date: 16/09/2024
# Description: Menu testing scenario

@SmokeScenario
Feature: Menu UI and action

  @Menu @DefaultUI
  Scenario: Check menu default UI
    Given Menu: Open Chrome and login page
    When Menu default UI
    When Menu display circle dot
    When Menu default width size
    When Logo menu default
    When Menu nav item default

  @Menu @MiniUI
  Scenario: Check collapse menu UI
    Given Menu: Open Chrome and login page
    When Click circle dot
    And Menu: Reload page
    Then Menu mini UI
    Then Menu mini width size

  @Menu @MiniHover
  Scenario: Check hover into collapsed menu
    Given Menu: Open Chrome and login page
    And Hover menu
    Then Menu default UI

  @MenuClick @CircleDot
  Scenario: Check menu when circle dot enable
    Given Menu: Open Chrome and login page
    When Hover menu
    And Click circle dot
    And Menu: Reload page
    Then Menu default UI

  @MenuClick @Logo
  Scenario: Check click menu logo
    Given Menu: Open Chrome and login page
    And Tap the menu logo
    Then Menu result: open overview screen

  @MenuClick @ItemTongQuan
  Scenario: Check click on menu item Tong quan
    Given Menu: Open Chrome and login page
    And Click on menu item Tong quan
    Then Menu result: open overview screen

  @MenuClick @ItemDMS
  Scenario: Check click on menu item DMS
    Given Menu: Open Chrome and login page
    And Click on menu item Danh muc sach
    Then Menu result: open book list screen

  @MenuClick @ItemTacGia
  Scenario: Check click on menu item Tac gia
    Given Menu: Open Chrome and login page
    And Click on menu item Tac gia
    Then Menu result: open author screen

  @MenuClick @ItemSach
  Scenario: Check click on menu item Sach
    Given Menu: Open Chrome and login page
    And Click on menu item Sach
    Then Menu result: open book screen

  @MenuClick @ItemBinhLuanDanhGia
  Scenario: Check click on menu item Binh luan/Danh gia
    Given Menu: Open Chrome and login page
    And Click on menu item Binh luan Danh gia
    Then Menu result: open comment reviews screen

  @MenuClick @ItemGiaoDich
  Scenario: Check click on menu item Giao dich
    Given Menu: Open Chrome and login page
    And Click on menu item Giao dich
    And Click on submenu item Mua sach
    Then Menu result: show transaction submenu

  @SubmenuClick @ItemMuaSach
  Scenario: Check click on submenu item Mua sach
    Given Menu: Open Chrome and login page
    And Click on menu item Giao dich
    And Click on submenu item Mua sach
    Then Menu result: open purchases book screen

  @SubmenuClick @ItemMuaXu
  Scenario: Check click on submenu item Mua xu
    Given Menu: Open Chrome and login page
    And Click on menu item Giao dich
    And Click on submenu item Mua xu
    Then Menu result: open purchases coin screen

  @MenuClick @ItemTaiKhoanKH
  Scenario: Check click on menu item Tai khoan khach hang
    Given Menu: Open Chrome and login page
    And Click on menu item Tai khoan khach hang
    Then Menu result: open customer account screen

  @MenuClick @ItemQTV
  Scenario: Check click on menu item Quan tri vien
    Given Menu: Open Chrome and login page
    And Click on menu item Quan tri vien
    And Click on submenu item Tai khoan quan tri vien
    Then Menu result: show administrator submenu

  @SubmenuClick @ItemTaiKhoanQTV
  Scenario: Check click on submenu item Tai khoan quan tri vien
    Given Menu: Open Chrome and login page
    And Click on menu item Quan tri vien
    And Click on submenu item Tai khoan quan tri vien
    Then Menu result: open admin account screen

  @SubmenuClick @ItemPhanQuyen
  Scenario: Check click on submenu item Phan quyen
    Given Menu: Open Chrome and login page
    And Click on menu item Quan tri vien
    And Click on submenu item Phan quyen
    Then Menu result: open permission screen

  @SubmenuClick @ItemVaiTro
  Scenario: Check click on submenu item Vai tro
    Given Menu: Open Chrome and login page
    And Click on menu item Quan tri vien
    And Click on submenu item Vai tro
    Then Menu result: open role screen

  @MenuClick @ItemThongBao
  Scenario: Check click on menu item Thong bao
    Given Menu: Open Chrome and login page
    And Click on menu item Thong bao
    Then Menu result: open notify screen
    And Menu: Close browser