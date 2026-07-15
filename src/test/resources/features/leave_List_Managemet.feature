@Leave
Feature: Leave List Management

  As an HR administrator
  I want to search and manage leave requests
  So that I can monitor employee leave activities effectively

  Background:
    Given open the browser and navigate to "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    And the user is logged into OrangeHRM with user "Admin" and password "admin123"
    And user navigates to Leave List page

  @search_leave_request @test-01
  Scenario Outline: Search leave request with multiple filters
    When the user enters from date "<from_date>"
    And the user enters to date "<to_date>"
    And the user selects leave status "<status>"
    And the user selects leave type "<leave_type>"
    And the user enters Leave employee name "<employee_name>"
    And the user selects Leave sub unit "<sub_unit>"
    And the user clicks the Search button
    Then the system should display records for "<employee_name>"

    Examples:
      | from_date | to_date | status | leave_type | employee_name | sub_unit |
      | 2026-01-01 | 2026-12-31 | Pending Approval | US - Bereavement | Linda Anderson | Administration |
      | 2026-02-15 | 2026-11-30 | Scheduled | US - FMLA | Paul Collings | Technical Support |
      | 2026-03-01 | 2026-09-15 | Taken | CAN - Personal | John Smith | Quality Assurance |

  @validate_autocomplete @test-02
  Scenario Outline: Validate employee autocomplete suggestions
    When the user types "<partialName>" in Leave Employee Name field
    Then employee suggestions should be displayed

    Examples:
      | partialName |
      | Li |
      | Jo |
      | Pa |

  @search_by_subunit @test-03
  Scenario Outline: Search leave requests by sub unit "<subUnit>"
    When the user selects sub unit "<subUnit>"
    And the user clicks the Search button
    Then all returned requests should belong to "<subUnit>"

    Examples:
      | subUnit |
      | Administration |
      | Engineering |
      | Human Resources |

  @search_by_leave_type @test-04
  Scenario Outline: Search leave requests by leave type "<leaveType>"
    When the user selects leave type "<leaveType>"
    And the user clicks the Search button
    Then all leave records should have leave type "<leaveType>"

    Examples:
      | leaveType |
      | CAN - Vacation |
      | US - Personal |
      | CAN - Bereavement |



  @search_by_leave_status @test-05
  Scenario Outline: Search leave requests by leave status "<status>"
    When the user selects leave status "<status>"
    And the user clicks the Search button
    Then all returned requests should have status "<status>"

    Examples:
      | status |
      | Rejected |
      | Cancelled |
      | Pending Approval |
      | Scheduled |
      | Taken |

  @search_by_multiple_statuses @test-06 # leave type se utilza my leave
  Scenario Outline: Search leaves using multiple statuses
    When the user selects the following leave statuses
      | status1 |
      | status2 |
    And clicks Search
    Then returned records should have either "<status1>" or "<status2>"

    Examples:
      | status1          | status2 |
      | Approved         | Scheduled |
      | Pending Approval | Rejected |
      | Taken            | Cancelled |