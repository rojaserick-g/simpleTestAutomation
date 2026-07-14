package StepDefinition.PIM;

import ObjectPage.PimPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PimDefinition {

    private final PimPage pimPage = new PimPage();

    @And("el usuario ingresa al modulo PIM")
    public void elUsuarioIngresaAlModuloPIM() {
        pimPage.ingresarModuloPim();
    }

    @When("the user enters {string} in Employee Name field")
    public void theUserEntersInEmployeeNameField(String nombre) {
        pimPage.escribirNombreEmpleado(nombre);
    }

    @When("the user enters employee id {string}")
    public void theUserEntersEmployeeId(String id) {
        pimPage.escribirEmployeeId(id);
    }

    @When("clicks Search")
    public void clicksSearch() {
        pimPage.clickSearch();
    }


    @Then("search results should be displayed")
    public void searchResultsShouldBeDisplayed() {
        pimPage.validarResultadosVisibles();
    }

    @Then("every result should contain {string}")
    public void everyResultShouldContain(String nombre) {
        pimPage.validarNombreEmpleado(nombre);
    }

    @Then("the system should return {string} record\\(s)")
    public void theSystemShouldReturnRecordS(String cantidad) {
        pimPage.validarCantidadRegistros(cantidad);
    }
    @When("the user searches employee {string}")
    public void theUserSearchesEmployee(String nombre) {
        pimPage.escribirNombreEmpleado(nombre);
        pimPage.clickSearch();
    }

    @Then("the results should be identical for the same employee {string}")
    public void theResultsShouldBeIdenticalForTheSameEmployee(String nombreEsperado) {
        pimPage.validarResultadosVisibles();
        pimPage.validarNombreEmpleado(nombreEsperado);
    }
    @When("the user selects employment status {string}")
    public void theUserSelectsEmploymentStatus(String estado) {

        pimPage.seleccionarEmploymentStatus(estado);

    }
    @Then("every result should have employment status {string}")
    public void everyResultShouldHaveEmploymentStatus(String estado) {

        pimPage.validarResultadosVisibles();

    }
    @When("the user selects include option {string}")
    public void theUserSelectsIncludeOption(String opcion) {

        pimPage.seleccionarInclude(opcion);

    }

    @Then("results should belong to {string}")
    public void resultsShouldBelongTo(String opcion) {

        pimPage.validarResultadosVisibles();

    }
    @When("the user selects job title {string}")
    public void theUserSelectsJobTitle(String cargo) {

        pimPage.seleccionarJobTitle(cargo);

    }

    @Then("every result should have job title {string}")
    public void everyResultShouldHaveJobTitle(String cargo) {

        pimPage.validarResultadosVisibles();

    }
    @When("the user selects sub unit {string}")
    public void theUserSelectsSubUnit(String subUnit) {

        pimPage.seleccionarSubUnit(subUnit);

    }

    @Then("every result should belong to sub unit {string}")
    public void everyResultShouldBelongToSubUnit(String subUnit) {

        pimPage.validarResultadosVisibles();

    }
}