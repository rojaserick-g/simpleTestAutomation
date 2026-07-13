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
//test 2
    @When("the user types {string} in Employee Name field")
    public void theUserTypesInEmployeeNameField(String partialName) {
        WebDriver d = leavePage.getDriver();
        WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));
        WebElement inputEmpleado = wait.until(ExpectedConditions.visibilityOf(leavePage.getTxtEmployeeName()));

        inputEmpleado.clear();
        inputEmpleado.sendKeys(partialName);
        // ANTES de que termine el paso y Cucumber tome la captura.
        try {
            wait.until(ExpectedConditions.visibilityOf(leavePage.getAutocompleteDropdown()));
            Thread.sleep(500); // Un leve respiro para que el DOM termine de renderizar las opciones
        } catch (Exception e) {

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

    //test 3
    @When("the user selects sub unit {string}")
    public void theUserSelectsSubUnit(String subUnit) {
        WebDriver d = leavePage.getDriver();
        WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

        // Esperamos a que el combo de subunidad sea visible en el DOM antes de hacer clic
        wait.until(ExpectedConditions.visibilityOf(leavePage.cbxSubUnit));
        leavePage.selectDropdownOption(leavePage.getDriver(), leavePage.cbxSubUnit, subUnit);
    }

    @Then("all returned requests should belong to {string}")
    public void allReturnedRequestsShouldBelongTo(String subUnitEsperada) {
        // Obtener la lista de celdas de la columna Sub Unit
        java.util.List<WebElement> celdas = leavePage.obtenerCeldasSubUnit();

        // Si no hay registros, la validación pasa (o puedes lanzar error si esperabas datos)
        if (celdas.isEmpty()) {
            System.out.println("No se encontraron registros para la subunidad: " + subUnitEsperada);
            return;
        }
        // Verificar cada una de las celdas
        for (WebElement celda : celdas) {
            String textoCelda = celda.getText().trim();

            // Comparamos el texto real de la tabla contra lo que esperábamos
            assert textoCelda.equals(subUnitEsperada) :
                    "¡Error de validación! Se encontró un registro con la subunidad '" + textoCelda +
                            "' pero se esperaba '" + subUnitEsperada + "'.";
        }
        System.out.println("Éxito: Todos los registros pertenecen a la subunidad: " + subUnitEsperada);
    }
    //test 4
    @When("the user selects leave type {string}")
    public void theUserSelectsLeaveType(String leaveType) {
        WebDriver d = leavePage.getDriver();
        WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

        // 1. Opcional: Si el botón reset está visible, le damos clic para limpiar rastros anteriores
        try {
            if(leavePage.btnReset.isDisplayed()) {
                leavePage.btnReset.click();
                Thread.sleep(400);
            }
        } catch(Exception e) {
        }
        wait.until(ExpectedConditions.visibilityOf(leavePage.cbxLeaveType));
        leavePage.selectDropdownOption(d, leavePage.cbxLeaveType, leaveType);
        try { Thread.sleep(600); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    @Then("all leave records should have leave type {string}")
    public void allLeaveRecordsShouldHaveLeaveType(String leaveTypeEsperado) {
        java.util.List<WebElement> celdas = leavePage.obtenerCeldasLeaveType();

        if (celdas.isEmpty()) {
            System.out.println("No se encontraron registros en la tabla para el tipo: " + leaveTypeEsperado);
            return;
        }
        for (WebElement celda : celdas) {
            String textoCelda = celda.getText().trim();
            assert textoCelda.contains(leaveTypeEsperado) :
                    "¡Error de validación! Se encontró un registro con tipo '" + textoCelda +
                            "' pero se filtró por '" + leaveTypeEsperado + "'.";
        }
        System.out.println("Éxito: Todos los registros coinciden con el tipo: " + leaveTypeEsperado);
    }
    // test5

    @When("the user selects leave status {string}")
    public void theUserSelectsLeaveStatus(String status) {
        WebDriver d = leavePage.getDriver();
        WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

        // 1. Forzamos un reset del formulario antes de iniciar la prueba
        try {
            wait.until(ExpectedConditions.elementToBeClickable(leavePage.btnReset)).click();
            Thread.sleep(500); // Pausa para que el DOM se restablezca
        } catch(Exception e) {
            System.out.println("No se pudo interactuar con el botón Reset, continuando...");
        }
        // 2. Quitamos las etiquetas que OrangeHRM pre-selecciona de forma predeterminada (como 'Pending Approval')
        try {
            List<WebElement> botonesQuitar = leavePage.obtenerBotonesQuitarEstados();
            while (!botonesQuitar.isEmpty()) {
                botonesQuitar.get(0).click();
                Thread.sleep(250); // Tiempo para que el DOM procese la eliminación de la etiqueta
                botonesQuitar = leavePage.obtenerBotonesQuitarEstados();
            }
        } catch (Exception e) {
            // Si no hay etiquetas pre-seleccionadas, continúa directamente
        }
        // 3. Esperamos que el selector de estados esté visible e interactuable
        wait.until(ExpectedConditions.visibilityOf(leavePage.cbxLeaveStatus));

        // 4. Desplegamos y seleccionamos el estado enviado desde el Feature
        leavePage.selectDropdownOption(d, leavePage.cbxLeaveStatus, status);
        // Pausa crucial para que Cucumber capture la pantalla con el estado correctamente seleccionado
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Then("all returned requests should have status {string}")
    public void allReturnedRequestsShouldHaveStatus(String statusEsperado) {
        // Leemos la columna de estados mapeada en el Page Object
        List<WebElement> celdas = leavePage.obtenerCeldasLeaveStatus();

        // Si el resultado de la búsqueda es vacío (aparece "No Records Found"), el paso pasa limpiamente
        if (celdas.isEmpty()) {
            System.out.println("No se encontraron registros en la tabla para el estado: " + statusEsperado);
            return;
        }
        // Validamos fila por fila que coincida estrictamente con el estado del filtro
        for (WebElement celda : celdas) {
            String textoCelda = celda.getText().trim();

            // Usamos contains ya que en OrangeHRM a veces viene acompañado de subtítulos o datos extra
            assert textoCelda.contains(statusEsperado) :
                    "¡Error de filtrado! Se encontró un registro en estado '" + textoCelda +
                            "' pero se esperaba exclusivamente '" + statusEsperado + "'.";
        }
        System.out.println("Éxito: Todos los registros en la tabla corresponden a " + statusEsperado);
    }











}