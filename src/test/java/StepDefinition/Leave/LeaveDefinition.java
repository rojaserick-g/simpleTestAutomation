package StepDefinition.Leave;

import ObjectPage.Leave.LeavePage;
import ObjectPage.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import static ObjectPage.LoginPage.driver;

public class LeaveDefinition {

    private final LoginPage loginPage = new LoginPage();
    private final LeavePage leavePage = new LeavePage();


    @Given("the user is logged into OrangeHRM with user {string} and password {string}")
    public void theUserIsLoggedIntoOrangeHRM(String usuario, String pass) {

        loginPage.escribirUsersname(usuario);
        loginPage.escribirPassword(pass);
        loginPage.clickBtnLogin();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @And("user navigates to Leave List page")
    public void userNavigatesToLeaveListPage() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        leavePage.navigateToLeaveList();
    }

    @When("the user enters from date {string}")
    public void theUserEntersFromDate(String fechaDesde) {

        WebDriver d = leavePage.getDriver();
        WebDriverWait wait =
                new WebDriverWait(d, Duration.ofSeconds(10));
        WebElement txtFechaDesde =
                leavePage.txtFechaDesde;
        wait.until(
                ExpectedConditions.visibilityOf(txtFechaDesde)
        );
        txtFechaDesde.clear();
        txtFechaDesde.sendKeys(fechaDesde);
    }

    @And("the user enters to date {string}")
    public void theUserEntersToDate(String toDate) {

        WebDriver d = leavePage.getDriver();
        WebElement txtFechaHasta =
                leavePage.txtFechaHasta;
        WebDriverWait wait =
                new WebDriverWait(d, Duration.ofSeconds(10));
        wait.until(
                ExpectedConditions.visibilityOf(txtFechaHasta)
        );
        txtFechaHasta.clear();
        txtFechaHasta.sendKeys(toDate);
    }



    @And("the user enters Leave employee name {string}")
    public void theUserEntersEmployeeName2(String employeeName) {
        leavePage.txtEmployeeName.clear();
        leavePage.txtEmployeeName.sendKeys(employeeName);
    }


    @Then("the system should display records for {string}")
    public void theSystemShouldDisplayRecordsFor(String employeeName) {
        System.out.println(
                "Validating records for employee: "
                        + employeeName
        );
    }
    //test2
    @When("the user types {string} in Leave Employee Name field")
    public void theUserTypesInEmployeeNameField(String partialName) {
        leavePage.getTxtEmployeeName().clear();
        leavePage.getTxtEmployeeName().sendKeys(partialName);
    }

    @Then("employee suggestions should be displayed")
    public void employeeSuggestionsShouldBeDisplayed() {
        WebDriver driver = leavePage.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(leavePage.getAutocompleteDropdown()));
        List<WebElement> suggestions = leavePage.getAutocompleteDropdown().findElements(By.tagName("li"));
        if (suggestions.isEmpty()) {
            throw new AssertionError("No employee suggestions displayed.");
        } else {
            System.out.println("Employee suggestions displayed: " + suggestions.size());
        }
    }
        //test 3
// =====================================================
// TEST 3 - SEARCH BY SUB UNIT
// =====================================================

    @When("the user selects Leave sub unit {string}")
    public void theUserSelectsSubUnit2(String subUnit) {

        WebDriver d = leavePage.getDriver();
        WebDriverWait wait =
                new WebDriverWait(d, Duration.ofSeconds(10));
        wait.until(
                ExpectedConditions.visibilityOf(
                        leavePage.cbxSubUnit
                )
        );
        leavePage.selectDropdownOption(
                d,
                leavePage.cbxSubUnit,
                subUnit
        );
    }

    @Then("all returned requests should belong to {string}")
    public void allReturnedRequestsShouldBelongTo(String subUnitEsperada) {
        List<WebElement> celdas =
                leavePage.obtenerCeldasSubUnit();
        if (celdas.isEmpty()) {
            System.out.println(
                    "No records found for sub unit: "
                            + subUnitEsperada
            );
            return;
        }
        for (WebElement celda : celdas) {

            String textoCelda =
                    celda.getText().trim();
            assert textoCelda.equals(subUnitEsperada)
                    : "Expected sub unit: "
                    + subUnitEsperada
                    + " but found: "
                    + textoCelda;
        }
        System.out.println(
                "All returned records belong to: "
                        + subUnitEsperada
        );
    }


// =====================================================
// TEST 4 - SEARCH BY LEAVE TYPE
// =====================================================
@When("the user selects leave type {string}")
public void theUserSelectsLeaveType(String leaveType) {
    WebDriver d = leavePage.getDriver();
    WebDriverWait wait =
            new WebDriverWait(
                    d,
                    Duration.ofSeconds(10));
    wait.until(
            ExpectedConditions.visibilityOf(
                    leavePage.cbxLeaveType
            )
    );
    leavePage.selectDropdownOption(
            d,
            leavePage.cbxLeaveType,
            leaveType
    );
}

    @Then("all leave records should have leave type {string}")
    public void allLeaveRecordsShouldHaveLeaveType(
            String leaveTypeEsperado) {
        List<WebElement> celdas =
                leavePage.obtenerCeldasLeaveType();
        if (celdas.isEmpty()) {

            System.out.println(
                    "No records found for leave type: "
                            + leaveTypeEsperado
            );
            return;
        }
        for (WebElement celda : celdas) {

            String textoCelda =
                    celda.getText().trim();
            assert textoCelda.contains(leaveTypeEsperado)
                    : "Expected leave type: "
                    + leaveTypeEsperado
                    + " but found: "
                    + textoCelda;
        }
        System.out.println(
                "All leave records match leave type: "
                        + leaveTypeEsperado
        );
    }


// =====================================================
// TEST 5 - SEARCH BY LEAVE STATUS
// =====================================================

    @When("the user selects leave status {string}")
    public void theUserSelectsLeaveStatus(String status) {

        WebDriver d = leavePage.getDriver();

        WebDriverWait wait =
                new WebDriverWait(d, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.visibilityOf(
                        leavePage.cbxShowLeaveStatus
                )
        );

        leavePage.selectDropdownOption(
                d,
                leavePage.cbxShowLeaveStatus,
                status
        );
    }

    @Then("all returned requests should have status {string}")
    public void allReturnedRequestsShouldHaveStatus(
            String statusEsperado) {

        List<WebElement> celdas =
                leavePage.obtenerCeldasLeaveStatus();

        if (celdas.isEmpty()) {

            System.out.println(
                    "No records found for status: "
                            + statusEsperado
            );

            return;
        }

        for (WebElement celda : celdas) {

            String textoCelda =
                    celda.getText().trim();

            assert textoCelda.contains(statusEsperado)
                    : "Expected status: "
                    + statusEsperado
                    + " but found: "
                    + textoCelda;
        }

        System.out.println(
                "All returned requests have status: "
                        + statusEsperado
        );
    }


// =====================================================
// COMMON SEARCH BUTTON
// =====================================================

    @And("the user clicks the Search button")
    public void theUserClicksTheSearchButton() {

        WebDriverWait wait =
                new WebDriverWait(
                        leavePage.getDriver(),
                        Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        leavePage.btnSearch
                )
        );

        leavePage.btnSearch.click();
    }



}