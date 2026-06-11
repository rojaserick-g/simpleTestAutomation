@login @pruebasRegresion
Feature: funcionalidad de login

  #Fecha creación: 12-12-2025
  @testCase_1 @rutaCritica
  Scenario: Validar login
    Given abrir el navegador en la url "https://practicetestautomation.com/practice-test-login/"
    And ingresar el usuario "student"
    And ingresar la pass "Password123"
    When presiono el boton Submit
    Then se valida el mensaje Logged In Successfully

  @testCase_2 @rutaCritica
  Scenario: Validar login con credenciales invalidas
    Given abrir el navegador en la url "https://practicetestautomation.com/practice-test-login/"
    And ingresar el usuario "user_invalido"
    And ingresar la pass "pass_invalida"
    When presiono el boton Submit
    Then se valida el mensaje usuario invalido



