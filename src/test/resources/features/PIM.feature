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

  @PIM @TC-007
  Scenario Outline: Search employees by sub unit
    When the user selects sub unit "<subUnit>"
    And clicks Search
    Then every result should belong to sub unit "<subUnit>"
    Examples:
      | subUnit           |
      | OrangeHRM Inc.    |
      | Administration    |
      | Engineering       |
      | Development       |
      | Quality Assurance |


  @PIM @TC-008
  Scenario Outline: Search employees by supervisor
    When the user selects supervisor "<supervisor>"
    And clicks Search
    Then every result should report to supervisor "<supervisor>"
    Examples:
      | supervisor       |
      | John Smith       |
      | Peter Anderson   |
      | Linda Brown      |


  @PIM @TC-009
  Scenario Outline: Search employee with multiple filters
    When the user enters employee name "<employeeName>"
    And selects employment status "<status>"
    And selects job title "<jobTitle>"
    And selects sub unit "<subUnit>"
    And clicks Search
    Then every result should match all selected criteria
    Examples:
      | employeeName | status | jobTitle | subUnit |
      | James        |        |          |          |

  @PIM @TC-010
  Scenario Outline: Reset search filters
    Given the user performs a search using:
      | employeeName | status |
      | <employeeName> | <status> |
    When the user clicks Reset
    Then all search fields should be cleared
    And default values should be restored
    Examples:
      | employeeName | status              |
      | James        | Freelance           |
      | Amelia       | Full-Time Contract  |

  #@PIM @TC-011
  #Scenario: Validate created employee information
    #Given a new employee is created with:
      #| firstName | lastName |
      #| Erick     | Rojas    |
    #When the user opens the created employee
    #Then employee detail page should display

  @PIM @TC-012
  Scenario Outline: Navigate through employee list pages
    When the user navigates to page "<pageNumber>"
    Then page "<pageNumber>" should be displayed
    And employee records should be loaded
    Examples:
      | pageNumber |
      | 1 |
      | 2 |
      | 3 |

  @PIM @TC-013
  Scenario Outline: Validate XSS protection in employee name search
    When the user enters "<maliciousInput>" in Employee Name field
    And clicks Search
    Then no script should be executed
    And the application should remain stable
    Examples:
      | maliciousInput               |
      | <script>alert('xss')</script> |
      | x                            |

  @PIM @TC-014
  Scenario Outline: Validate SQL Injection protection
    When the user enters "<maliciousInput>" into Employee Name
    And clicks Search
    Then unauthorized records should not be returned
    And the application should remain stable
    Examples:
      | maliciousInput              |
      | ' OR 1=1 --                 |
      | admin' --                   |
      | ' UNION SELECT * FROM users -- |

  @PIM @TC-015
  Scenario Outline: Create and find employee
    Given a new employee is created with:
      | firstName | lastName |
      | <firstName> | <lastName> |
    When the user searches using generated employee id
    Then the created employee should be displayed
    Examples:
      | firstName | lastName |
      | Leslie    | Prueba   |

  @PIM @TC-016
  Scenario Outline: Delete employee and validate removal
    Given employee "<employeeId>" exists
    When the employee is deleted
    And searches by employee id "<employeeId>"
    Then no records should be found
    Examples:
      | employeeId |
      | 0460 |

  @PIM @TC-017
  Scenario: Delete employee and validate removal
    Given a new employee is created with:
      | firstName | lastName |
      | Erick     | Rojas    |
    When the employee is deleted
    And the user searches using generated employee id
    Then no records should be found
