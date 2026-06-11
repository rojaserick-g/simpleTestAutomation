@login @pruebasNegativas
Feature: funcionalidad de login - escenarios negativos

  @testCase_2 @rutaCritica
  Scenario: Validar login con credenciales invalidas
    Given abrir el navegador en la url "https://practicetestautomation.com/practice-test-login/"
    And ingresar el usuario "user_invalido"
    And ingresar la pass "pass_invalida"
    When presiono el boton Submit
    Then se valida el mensaje usuario invalido
