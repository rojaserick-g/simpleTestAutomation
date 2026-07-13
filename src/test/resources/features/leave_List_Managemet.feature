Feature: Leave List Management

  As an HR administrator
  I want to search and manage leave requests
  So that I can monitor employee leave activities effectively

  Background:
    Given abrir el navegador en la url "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    And the user is logged into OrangeHRM with user "Admin" and password "admin123"
    And user navigates to Leave List page

  @search_leave_request @test-01
  Scenario Outline: Search leave request with multiple filters
    When ingresa la fecha desde "<from_date>"
    And ingresa la fecha hasta "<to_date>"
    And selecciona el estado "<status>"
    And selecciona el tipo de licencia "<leave_type>"
    And ingresa el nombre del empleado "<employee_name>"
    And selecciona la sub unidad "<sub_unit>"
    And hace clic en el boton buscar
    Then el sistema deberia mostrar los registros de "<employee_name>"

    Examples:
      | from_date  | to_date    | status           | leave_type        | employee_name | sub_unit          |
      | 2026-01-01   | 2026-12-31   | Pending Approval   | US - Bereavement   | Linda Anderson  | Administration      |
      | 2026-02-15   | 2026-11-30   | Scheduled          | US - FMLA          | Paul Collings   | Technical Support   |
      | 2026-03-01   | 2026-09-15   | Taken              | CAN - Personal     | John Smith      | Quality Assurance   |


  @validate_autocomplete @test-02
  Scenario Outline: Validate employee autocomplete suggestions
    When the user types "<partialName>" in Employee Name field
    Then employee suggestions should be displayed

    Examples:
      | partialName |
      | Li          |
      | Jo          |
      | Pa          |