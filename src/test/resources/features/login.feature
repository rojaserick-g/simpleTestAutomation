@login @pruebasRegresion
Feature: funcionalidad de login

  #Fecha creación: 12-12-2025
  @testCase_1 @rutaCritica
  Scenario Outline: Validar login
    Given abrir el navegador en la url "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    And ingresar el usuario "<usuario>"
    And ingresar la pass "<pass>"
    When presiono el boton Login

    #Then se valida el mensaje de login invalido
    #Then se valida el mensaje de pass invalido


    Examples:
      | usuario | pass |
      | Admin   | admin123 |





  #@testCase_2 @rutaCritica
  #Scenario: Validar login con credenciales invalidas
   # Given abrir el navegador en la url "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    #And ingresar el usuario "<usuario_invalido>"
    #And ingresar la pass "<pass_invalida>"
    #When presiono el boton Submit
    #Then se valida el mensaje usuario invalido



