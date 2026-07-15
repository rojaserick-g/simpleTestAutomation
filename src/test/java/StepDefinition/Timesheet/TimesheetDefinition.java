package StepDefinition.Timesheet;

import Control.DriverContext;
import ObjectPage.Timesheet.TimesheetPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class TimesheetDefinition {
    private final TimesheetPage timesheetPage = new TimesheetPage();

//Background
    @And("the user navigates to Employee Timesheets page")
    public void theUserNavigatesToEmployeeTimesheetsPage() throws InterruptedException {
        Thread.sleep(2000); // Espera de 2 segundos antes de hacer clic en el menú de tiempo
        timesheetPage.clickTimeMenu();
    }

//Escenario 1
    @When("the user enters {string} in Employee Name field")
    public void theUserEntersInEmployeeNameField(String nombre) {
        timesheetPage.llenarNombre(nombre);
    }

    @And("clicks View")
    public void clicksView() {
        timesheetPage.clickSubmitButton();
    }

    @Then("the timesheet for {string} should be displayed")
    public void theTimesheetForShouldBeDisplayed(String nombre) {
        timesheetPage.llenarNombre(nombre);
    }

//Escenario 2
    @When("the user types {string} in Employee Name field")
    public void theUserTypesInEmployeeNameField(String nombre) {
        timesheetPage.llenarNombre(nombre);
    }

    @Then("employee suggestions containing {string} should be displayed")
    public void employee_suggestions_containing_should_be_displayed(String partialName) throws InterruptedException {
        Thread.sleep(5000);
        boolean seleccionado = timesheetPage.selectFirstSuggestion(DriverContext.getDriver());

        Assert.assertTrue(
                "No fue posible seleccionar una sugerencia.",
                seleccionado
        );
    }

//Escenario 3

    @When("the user searches employee {string}")
    public void theUserSearchesEmployee(String partialName) {
        timesheetPage.llenarNombre(partialName);
        timesheetPage.selectSuggestion(partialName, DriverContext.getDriver());
    }

    @And("the user clicks in view button")
    public void theUserClicksInViewButton() {
        timesheetPage.clickView();
    }

    @And("selects timesheet period {string}")
    public void selectsTimesheetPeriod(String period) {
        timesheetPage.selectPeriod(period);
    }

    @Then("the system should display the timesheet for {string}")
    public void theSystemShouldDisplayTheTimesheetFor(String period) throws InterruptedException {
        Thread.sleep(5000);
        timesheetPage.validarTimesheet(period);
    }
}
