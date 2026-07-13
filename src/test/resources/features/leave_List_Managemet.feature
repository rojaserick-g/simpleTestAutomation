Feature: Leave List Management

  As an HR administrator
  I want to search and manage leave requests
  So that I can monitor employee leave activities effectively

  Background:
    Given the user is logged into OrangeHRM
    And then user navigates to Leave List page

  @search_leave_request @test-01
  Scenario Outline: Search leave request by employee name
    When the user enters "<employee_name>" in the search field
    And clicks on the search button
    Then the system should display leave requests for "<employee_name>"

    Examples:
      | employee_name |
      | John Doe      |
      | Jane Smith    |
      | Michael Brown |