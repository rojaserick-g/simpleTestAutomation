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
    public void seleccionarEmploymentStatus(String estado){

        try{

            visualizarElemento(cmbEmploymentStatus,10);
            cmbEmploymentStatus.click();

            Thread.sleep(1000);

            for(WebElement opcion : listaEmploymentStatus){

                if(opcion.getText().trim().equalsIgnoreCase(estado)){

                    opcion.click();
                    break;

                }

            }

        }catch(Exception e){

            Assertions.fail("Error seleccionando Employment Status: " + e.getMessage());

        }

    }
    public void seleccionarInclude(String opcion){

        try{

            visualizarElemento(cmbInclude,10);
            cmbInclude.click();

            Thread.sleep(1000);

            for(WebElement item : listaInclude){

                if(item.getText().trim().equalsIgnoreCase(opcion)){

                    item.click();
                    break;

                }

            }

        }catch(Exception e){

            Assertions.fail("Error seleccionando Include: " + e.getMessage());

        }

    }
    public void seleccionarJobTitle(String cargo){

        try{

            visualizarElemento(cmbJobTitle,10);
            cmbJobTitle.click();

            Thread.sleep(1000);

            for(WebElement opcion : listaJobTitle){

                if(opcion.getText().trim().equalsIgnoreCase(cargo)){

                    opcion.click();
                    break;

                }

            }

        }catch(Exception e){

            Assertions.fail("Error seleccionando Job Title: " + e.getMessage());

        }

    }
    public void seleccionarSubUnit(String subUnit){

        try{

            visualizarElemento(cmbSubUnit,10);
            cmbSubUnit.click();

            Thread.sleep(1000);

            for(WebElement opcion : listaSubUnit){

                if(opcion.getText().trim().equalsIgnoreCase(subUnit)){

                    opcion.click();
                    break;

                }

            }

        }catch(Exception e){

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
}