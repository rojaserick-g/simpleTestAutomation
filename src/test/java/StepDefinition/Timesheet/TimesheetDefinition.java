package StepDefinition.Timesheet;

import ObjectPage.Timesheet.TimesheetPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TimesheetDefinition {
    private final TimesheetPage timesheetPage = new TimesheetPage();

    @And("the user navigates to Employee Timesheets page")
    public void theUserNavigatesToEmployeeTimesheetsPage() {
        timesheetPage.clickTimeMenu();
    }

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

}
