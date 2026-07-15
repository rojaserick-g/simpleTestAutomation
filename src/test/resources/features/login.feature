@login @pruebasRegresion
Feature: funcionalidad de login

  #Fecha creación: 12-12-2025
  @testCase_1 @rutaCritica
  Scenario Outline: Validar login
    Given abrir el navegador en la url "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    And ingresar el usuario "<usuario>"
    And ingresar la pass "<pass>"
    When presiono el boton Login

    Examples:
      | usuario | pass |
      | Admin   | admin123 |


