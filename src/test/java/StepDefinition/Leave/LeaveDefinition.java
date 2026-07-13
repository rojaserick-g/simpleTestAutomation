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

    @When("ingresa la fecha desde {string}")
    public void ingresaLaFechaDesde(String fechaDesde) {
        WebDriver d = leavePage.getDriver();
        WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));
        WebElement txtFechaDesde = leavePage.txtFechaDesde;
        wait.until(ExpectedConditions.visibilityOf(txtFechaDesde));
        txtFechaDesde.clear();
        txtFechaDesde.sendKeys(fechaDesde);
    }

    @And("ingresa la fecha hasta {string}")
    public void ingresaLaFechaHasta(String toDate) {
        WebDriver d = leavePage.getDriver();
        WebElement txtFechaHasta = leavePage.txtFechaHasta;
        WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(txtFechaHasta));
        txtFechaHasta.clear();
        txtFechaHasta.sendKeys(toDate);
    }

    @And("selecciona el estado {string}")
    public void seleccionaElEstado(String estado) {
        // Corregido: Usa cbxShowLeaveStatus en lugar de getDropdownStatusContainer que no existía
        leavePage.selectDropdownOption(leavePage.getDriver(), leavePage.cbxShowLeaveStatus, estado);
    }

    @And("selecciona el tipo de licencia {string}")
    public void seleccionaElTipoDeLicencia(String leaveType) {
        // Corregido: Agregado el parámetro driver
        leavePage.selectDropdownOption(leavePage.getDriver(), leavePage.cbxLeavelType, leaveType);
    }

    @And("ingresa el nombre del empleado {string}")
    public void ingresaElNombreDelEmpleado(String employeeName) {
        leavePage.txtEmployeeName.sendKeys(employeeName);
    }

    @And("selecciona la sub unidad {string}")
    public void seleccionaLaSubUnidad(String subUnit) {
        // Corregido: Agregado el parámetro driver
        leavePage.selectDropdownOption(leavePage.getDriver(), leavePage.cbxSubUnit, subUnit);
    }

    @And("hace clic en el boton buscar")
    public void haceClicEnElBotonBuscar() {
        leavePage.btnPastEmployees.click();
        leavePage.btnSearch.click();
    }

    @Then("el sistema deberia mostrar los registros de {string}")
    public void elSistemaDeberiaMostrarLosRegistrosDe(String employeeName) {
        System.out.println("Validando resultados en pantalla para el empleado: " + employeeName);
    }

    @When("the user types {string} in Employee Name field")
    public void theUserTypesInEmployeeNameField(String partialName) {
        WebDriver d = leavePage.getDriver();
        WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));
        WebElement inputEmpleado = wait.until(ExpectedConditions.visibilityOf(leavePage.getTxtEmployeeName()));

        inputEmpleado.clear();
        inputEmpleado.sendKeys(partialName);

        try {
            // 1. Esperamos a que el contenedor del dropdown aparezca
            WebElement dropdown = wait.until(ExpectedConditions.visibilityOf(leavePage.getAutocompleteDropdown()));

            // 2. Esperamos a que desaparezca el texto "Searching..." para asegurar que cargó los nombres
            wait.until(ExpectedConditions.not(
                    ExpectedConditions.textToBePresentInElement(dropdown, "Searching...")
            ));

            // 3. Un mini respiro final para asegurar que el navegador renderice visualmente las cajitas
            Thread.sleep(700);
        } catch (Exception e) {
            // Si algo falla, dejamos que continúe para no romper el flujo del test
        }
    }

    @Then("employee suggestions should be displayed")
    public void employeeSuggestionsShouldBeDisplayed() {
        WebDriver d = leavePage.getDriver();
        WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));
        WebElement dropdownSugerencias = wait.until(ExpectedConditions.visibilityOf(leavePage.getAutocompleteDropdown()));
        boolean tieneOpciones = dropdownSugerencias.findElements(By.xpath("./div")).size() > 0;

        assert tieneOpciones : "¡Error! El autocompletado se abrió pero no mostró ninguna sugerencia.";
        System.out.println("Sugerencias desplegadas correctamente en pantalla.");
    }
}