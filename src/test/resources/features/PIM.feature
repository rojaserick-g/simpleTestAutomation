@PIM
Feature: Employee List Search and Filters

  Background:
    Given abrir el navegador en la url "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    And ingresar el usuario "Admin"
    And ingresar la pass "admin123"
    When presiono el boton Login
    And el usuario ingresa al modulo PIM

  @TC-001
  Scenario Outline: Search employee by name
    When the user enters "<employeeName>" in Employee Name field
    And clicks Search
    Then search results should be displayed
    And every result should contain "<expectedName>"

    Examples:
      | employeeName | expectedName |
      | Amelia       | Amelia       |

  @TC-002
  Scenario Outline: Search employee name ignoring case sensitivity
    When the user searches employee "<employeeName>"
    Then the results should be identical for the same employee "<expectedName>"

    Examples:
      | employeeName | expectedName |
      | Amelia       | Amelia       |
      | AMELIA       | Amelia       |
      | aMeLiA       | Amelia       |
  @TC-003
 Scenario Outline: Search employee by employee id
    When the user enters employee id "<employeeId>"
    And clicks Search
    Then the system should return "<expectedRecords>" record(s)
    Examples:
      | employeeId | expectedRecords |
      | 0001 | 1 |
      | 999999 | 0 |

  @PIM @TC-004
  Scenario Outline: Search employees by employment status
    When the user selects employment status "<status>"
    And clicks Search
    Then every result should have employment status "<status>"

    Examples:
      | status                |
      | Freelance             |
      | Full-Time Contract    |
      | Full-Time Permanent   |
      | Full-Time Probation   |
      | Part-Time Contract    |

  @PIM @TC-005
  Scenario Outline: Search employees using include filter
    When the user selects include option "<includeOption>"
    And clicks Search
    Then results should belong to "<includeOption>"

    Examples:
      | includeOption              |
      | Current Employees Only     |
      | Current and Past Employees |
      | Past Employees Only        |

  @PIM @TC-006
  Scenario Outline: Search employees by job title
    When the user selects job title "<jobTitle>"
    And clicks Search
    Then every result should have job title "<jobTitle>"

    Examples:
      | jobTitle                 |
      | Account Assistant        |
      | Automation Engineer L000 |
      | Automation Tester        |