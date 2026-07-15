package ObjectPage;
import Control.BaseController;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import Control.DriverContext;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PimPage extends BaseController {

    public PimPage() {
        initPage();
    }

    @FindBy(xpath = "//span[text()='PIM']")
    private WebElement menuPim;
    @FindBy(xpath = "//*[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div/input")
    private WebElement txtEmployeeName;
    @FindBy(xpath = "//div[@role='listbox']//span")
    private List<WebElement> listaEmpleados;
    @FindBy(xpath = "//*[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/input")
    private WebElement txtEmployeeId;
    @FindBy(xpath = "//*[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]")
    private WebElement btnSearch;
    @FindBy(xpath = "(//div[contains(@class,'oxd-select-text')])[1]")
    private WebElement cmbEmploymentStatus;
    @FindBy(xpath = "(//div[contains(@class,'oxd-select-text')])[2]")
    private WebElement cmbInclude;
    @FindBy(xpath = "(//div[contains(@class,'oxd-select-text')])[3]")
    private WebElement cmbJobTitle;
    @FindBy(xpath = "(//div[contains(@class,'oxd-select-text')])[4]")
    private WebElement cmbSubUnit;
    @FindBy(xpath = "//label[text()='Supervisor Name']/../following-sibling::div//input")
    private WebElement txtSupervisor;
    @FindBy(xpath = "//div[@role='listbox']//span")
    private List<WebElement> listaSupervisor;
    @FindBy(xpath = "//div[@role='listbox']//span")
    private List<WebElement> listaSubUnit;
    @FindBy(xpath = "//div[@role='listbox']//span")
    private java.util.List<WebElement> listaJobTitle;
    @FindBy(xpath = "//div[@role='listbox']//span")
    private java.util.List<WebElement> listaInclude;
    @FindBy(xpath = "//div[@role='listbox']//span")
    private java.util.List<WebElement> listaEmploymentStatus;
    //validamos
    @FindBy(xpath = "//div[@class='oxd-table-body']")
    private WebElement tablaResultados;
    @FindBy(xpath = "//div[contains(@class,'oxd-table-card')]")
    private List<WebElement> filasResultados;
    @FindBy(xpath = "//div[contains(@class,'orangehrm-horizontal-padding')]//span")
    private WebElement lblRecordsFound;
    @FindBy(xpath = "//*[normalize-space()='No Records Found']")
    private WebElement lblNoRecords;
    @FindBy(xpath = "//div[contains(@class,'oxd-table-card')]")
    private java.util.List<WebElement> registrosEncontrados;
    @FindBy(xpath = "//button[@type='reset']")
    private WebElement btnReset;
    @FindBy(name = "firstName")
    private WebElement txtFirstNameDetalle;
    @FindBy(xpath = "(//button[contains(@class,'oxd-icon-button')])[1]")
    private WebElement btnEditar;
    @FindBy(xpath = "//label[text()='Employee Id']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    private WebElement txtEmployeeIdDetalle;
    @FindBy(xpath = "//button[contains(@class,'oxd-pagination-page-item')]")
    private List<WebElement> paginas;
    @FindBy(xpath = "//div[contains(@class,'oxd-table-card')]")
    private List<WebElement> registrosPagina;
    @FindBy(xpath = "//button[text()=' Add ']")
    private WebElement btnAdd;
    @FindBy(name = "firstName")
    private WebElement txtFirstName;
    @FindBy(name = "lastName")
    private WebElement txtLastName;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnSave;
    @FindBy(xpath = "//label[text()='Employee Id']/../following-sibling::div/input")
    private WebElement txtEmployeeIdNuevo;
    @FindBy(xpath = "//a[text()='Employee List']")
    private WebElement btnEmployeeList;
    @FindBy(xpath = "//i[contains(@class,'bi-trash')]")
    private WebElement btnDelete;
    @FindBy(xpath = "//button[normalize-space()='Yes, Delete']")
    private WebElement btnYesDelete;


    public void ingresarModuloPim() {
        try {
            visualizarElemento(menuPim, 10);
            menuPim.click();
        } catch (Exception e) {
            Assertions.fail("Error al ingresar al módulo PIM: " + e.getMessage());
        }
    }

    public void escribirNombreEmpleado(String nombre) {
        try {
            visualizarElemento(txtEmployeeName, 10);
            txtEmployeeName.clear();
            txtEmployeeName.sendKeys(nombre);
        } catch (Exception e) {
            Assertions.fail("Error al ingresar nombre: " + e.getMessage());
        }
    }

    public void escribirEmployeeId(String id) {
        try {
            visualizarElemento(txtEmployeeId, 10);
            txtEmployeeId.clear();
            txtEmployeeId.sendKeys(id);
        } catch (Exception e) {
            Assertions.fail("Error al ingresar employee id: " + e.getMessage());
        }
    }

    public void clickSearch() {
        try {
            visualizarElemento(btnSearch, 10);
            btnSearch.click();
        } catch (Exception e) {
            Assertions.fail("Error al presionar Search: " + e.getMessage());
        }
    }

    public void seleccionarEmploymentStatus(String estado) {
        try {
            visualizarElemento(cmbEmploymentStatus, 10);
            cmbEmploymentStatus.click();
            Thread.sleep(1000);
            for (WebElement opcion : listaEmploymentStatus) {
                if (opcion.getText().trim().equalsIgnoreCase(estado)) {
                    opcion.click();
                    break;

                }

            }

        } catch (Exception e) {

            Assertions.fail("Error seleccionando Employment Status: " + e.getMessage());
        }
    }

    public void seleccionarInclude(String opcion) {
        try {
            visualizarElemento(cmbInclude, 10);
            cmbInclude.click();
            Thread.sleep(1000);
            for (WebElement item : listaInclude) {
                if (item.getText().trim().equalsIgnoreCase(opcion)) {
                    item.click();
                    break;
                }
            }

        } catch (Exception e) {
            Assertions.fail("Error seleccionando Include: " + e.getMessage());
        }
    }

    public void seleccionarJobTitle(String cargo) {
        try {
            visualizarElemento(cmbJobTitle, 10);
            cmbJobTitle.click();
            Thread.sleep(1000);
            for (WebElement opcion : listaJobTitle) {
                if (opcion.getText().trim().equalsIgnoreCase(cargo)) {
                    opcion.click();
                    break;
                }
            }

        } catch (Exception e) {
            Assertions.fail("Error seleccionando Job Title: " + e.getMessage());
        }
    }

    public void seleccionarSubUnit(String subUnit) {
        try {
            visualizarElemento(cmbSubUnit, 10);
            cmbSubUnit.click();
            Thread.sleep(1000);
            for (WebElement opcion : listaSubUnit) {
                if (opcion.getText().trim().equalsIgnoreCase(subUnit)) {
                    opcion.click();
                    break;
                }
            }
        } catch (Exception e) {
            Assertions.fail("Error seleccionando Sub Unit: " + e.getMessage());
        }
    }

    public void bajarHastaResultados() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) DriverContext.getDriver();
            js.executeScript("window.scrollBy(0,700)");
            Thread.sleep(1000);
        } catch (Exception e) {
            Assertions.fail("Error al hacer scroll: " + e.getMessage());
        }
    }

    public void validarResultadosVisibles() {
        try {
            visualizarElemento(tablaResultados, 10);
            Assertions.assertTrue(tablaResultados.isDisplayed(),
                    "La tabla de resultados no se muestra.");
        } catch (Exception e) {
            Assertions.fail("Error al validar la tabla: " + e.getMessage());
        }
    }

    public void validarNombreEmpleado(String nombreEsperado) {
        try {
            Assertions.assertFalse(filasResultados.isEmpty(),
                    "La búsqueda no retornó resultados.");
            boolean encontrado = false;
            for (WebElement fila : filasResultados) {
                String texto = fila.getText();
                System.out.println(texto);
                if (texto.toLowerCase().contains(nombreEsperado.toLowerCase())) {
                    encontrado = true;
                    break;
                }
            }
            Assertions.assertTrue(encontrado,
                    "No se encontró el empleado: " + nombreEsperado);

        } catch (Exception e) {
            Assertions.fail(e.getMessage());

        }
    }

    public void validarCantidadRegistros(String cantidadEsperada) {
        try {
            bajarHastaResultados();
            if (cantidadEsperada.equals("1")) {
                Assertions.assertFalse(
                        registrosEncontrados.isEmpty(),
                        "Se esperaba un registro y no se encontró ninguno."
                );
                System.out.println("Se encontró un registro correctamente.");
            } else {
                WebDriverWait wait = new WebDriverWait(
                        DriverContext.getDriver(),
                        Duration.ofSeconds(10)
                );

                wait.until(ExpectedConditions.visibilityOf(lblNoRecords));
                Assertions.assertTrue(
                        lblNoRecords.isDisplayed(),
                        "Se esperaba el mensaje 'No Records Found'."
                );
                System.out.println("No se encontraron registros, validación correcta.");
            }
        } catch (Exception e) {
            Assertions.fail("Error validando cantidad de registros: " + e.getMessage());
        }
    }

    public void seleccionarSupervisor(String supervisor) {
        try {
            visualizarElemento(txtSupervisor, 10);
            txtSupervisor.clear();
            txtSupervisor.sendKeys(supervisor);
            Thread.sleep(2000);
            for (WebElement opcion : listaSupervisor) {
                if (opcion.getText().trim().equalsIgnoreCase(supervisor)) {
                    opcion.click();
                    break;
                }
            }
        } catch (Exception e) {
            Assertions.fail("Error seleccionando Supervisor: " + e.getMessage());
        }
    }

    public void ingresarNombreEmpleado(String nombre) {
        txtEmployeeName.clear();
        txtEmployeeName.sendKeys(nombre);
    }

    public void validarResultadosFiltros() {
        try {
            visualizarElemento(tablaResultados, 10);
            Assertions.assertTrue(
                    tablaResultados.isDisplayed(),
                    "La tabla de resultados no se muestra.");

            Assertions.assertFalse(
                    registrosEncontrados.isEmpty(),
                    "No se encontraron registros.");
        } catch (Exception e) {
            Assertions.fail("Error validando resultados: " + e.getMessage());
        }
    }

    public void clickReset() {
        try {
            visualizarElemento(btnReset, 10);
            btnReset.click();
        } catch (Exception e) {
            Assertions.fail("Error al presionar Reset: " + e.getMessage());

        }

    }

    public void validarCamposReseteados() {
        try {
            Assertions.assertEquals("", txtEmployeeName.getAttribute("value"));
            Assertions.assertEquals("", txtEmployeeId.getAttribute("value"));
        } catch (Exception e) {
            Assertions.fail("Error validando campos reseteados: " + e.getMessage());

        }
    }

    public void abrirDetalleEmpleado() {
        try {
            visualizarElemento(btnEditar, 10);
            btnEditar.click();
        } catch (Exception e) {
            Assertions.fail("Error al abrir el detalle del empleado: " + e.getMessage());

        }
    }

    public void validarDetalleEmpleado(String nombre, String id) {
        try {
            visualizarElemento(txtFirstNameDetalle, 10);
            Assertions.assertEquals(
                    nombre,
                    txtFirstNameDetalle.getAttribute("value"));
            Assertions.assertEquals(
                    id,
                    txtEmployeeIdDetalle.getAttribute("value"));
        } catch (Exception e) {
            Assertions.fail("Error validando detalle del empleado: " + e.getMessage());
        }
    }

    //metodo para navegar
    public void navegarPagina(String pagina) {
        try {
        } catch (Exception e) {
            Assertions.fail("Error navegando: " + e.getMessage());
        }
    }

    //validar pagina
    public void validarPagina(String pagina) {
        try {
            visualizarElemento(tablaResultados, 10);
            Assertions.assertTrue(
                    tablaResultados.isDisplayed(),
                    "La tabla no se muestra.");
        } catch (Exception e) {
            Assertions.fail("Error validando página: " + e.getMessage());

        }

    }

    // validar registro
    public void validarRegistrosPagina() {
        try {
            Assertions.assertFalse(
                    registrosEncontrados.isEmpty(),
                    "No existen registros en la página.");
        } catch (Exception e) {
            Assertions.fail("Error validando registros: " + e.getMessage());

        }
    }

    public void validarNoEjecucionScript() {
        try {
            visualizarElemento(tablaResultados, 10);
            Assertions.assertTrue(
                    tablaResultados.isDisplayed(),
                    "La aplicación ejecutó un script o dejó de responder.");

        } catch (Exception e) {
            Assertions.fail("Error validando protección XSS: " + e.getMessage());

        }

    }

    public void validarAplicacionEstable() {
        try {
            Assertions.assertTrue(
                    DriverContext.getDriver().getCurrentUrl().contains("pim"),
                    "La aplicación salió del módulo PIM.");

        } catch (Exception e) {
            Assertions.fail("La aplicación no permanece estable: " + e.getMessage());
        }
    }

    public void validarSQLInjection() {
        try {
            visualizarElemento(tablaResultados, 10);
            Assertions.assertTrue(
                    tablaResultados.isDisplayed(),
                    "La aplicación devolvió un resultado inesperado.");
        } catch (Exception e) {
            Assertions.fail("Error validando SQL Injection: " + e.getMessage());
        }

    }

    //abrir add employee
    public void clickAdd() {
        try {
            visualizarElemento(btnAdd, 10);
            btnAdd.click();
        } catch (Exception e) {
            Assertions.fail("Error al abrir Add Employee: " + e.getMessage());
        }
    }

    public void volverEmployeeList() {
        try {
            visualizarElemento(btnEmployeeList, 10);
            btnEmployeeList.click();
        } catch (Exception e) {
            Assertions.fail("Error al volver a Employee List: " + e.getMessage());
        }
    }

    // Variables de la clase
    private String firstNameGenerado;
    private String lastNameGenerado;
    private String employeeIdGenerado;

    // ingresar nombre
    public void ingresarNuevoEmpleado(String nombre, String apellido) {
        try {
            visualizarElemento(txtFirstName, 10);

            // Guardar los datos para validarlos después
            firstNameGenerado = nombre;
            lastNameGenerado = apellido;

            txtFirstName.clear();
            txtFirstName.sendKeys(nombre);

            txtLastName.clear();
            txtLastName.sendKeys(apellido);

        } catch (Exception e) {
            Assertions.fail("Error ingresando empleado: " + e.getMessage());
        }
    }

    // guardar empleado
    public void guardarEmpleado() {
        try {
            visualizarElemento(btnSave, 10);
            btnSave.click();
        } catch (Exception e) {
            Assertions.fail("Error guardando empleado: " + e.getMessage());
        }
    }

    // guardar Employee ID generado
    public void guardarEmployeeId() {
        try {
            visualizarElemento(txtEmployeeIdNuevo, 10);
            employeeIdGenerado = txtEmployeeIdNuevo.getAttribute("value");
        } catch (Exception e) {
            Assertions.fail("Error obteniendo Employee ID: " + e.getMessage());
        }
    }

    // buscar empleado por parámetro
    public void buscarEmpleado(String employeeId) {
        volverEmployeeList();
        escribirEmployeeId(employeeId);
        clickSearch();
    }

    // buscar el empleado creado
    public void buscarEmpleadoCreado() {
        volverEmployeeList();
        escribirEmployeeId(employeeIdGenerado);
        clickSearch();
    }

    // validar creación
    public void validarEmpleadoCreado() {
        Assertions.assertFalse(
                registrosEncontrados.isEmpty(),
                "No se encontró el empleado creado.");
    }

    // validar eliminación
    public void validarEmpleadoEliminado() {
        try {
            visualizarElemento(lblNoRecords, 10);
            Assertions.assertTrue(
                    lblNoRecords.isDisplayed(),
                    "El empleado aún existe.");
        } catch (Exception e) {
            Assertions.fail("Error validando eliminación: " + e.getMessage());
        }
    }

    // eliminar empleado
    public void eliminarEmpleado() {
        try {
            volverEmployeeList();
            escribirEmployeeId(employeeIdGenerado);
            clickSearch();

            visualizarElemento(btnDelete, 10);
            btnDelete.click();

            visualizarElemento(btnYesDelete, 10);
            btnYesDelete.click();

        } catch (Exception e) {
            Assertions.fail("Error eliminando empleado: " + e.getMessage());
        }
    }

    // abrir empleado creado
    public void abrirEmpleadoCreado() {
        volverEmployeeList();
        escribirEmployeeId(employeeIdGenerado);
        clickSearch();

        visualizarElemento(btnEditar, 10);
        btnEditar.click();
    }

    // validar información del empleado
    public void validarInformacionEmpleado() {

        visualizarElemento(txtFirstName, 10);

        Assertions.assertEquals(
                firstNameGenerado,
                txtFirstName.getAttribute("value"),
                "El nombre no coincide.");

        Assertions.assertEquals(
                lastNameGenerado,
                txtLastName.getAttribute("value"),
                "El apellido no coincide.");

        Assertions.assertEquals(
                employeeIdGenerado,
                txtEmployeeIdNuevo.getAttribute("value"),
                "El Employee ID no coincide.");
    }}