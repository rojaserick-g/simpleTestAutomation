package ObjectPage.Timesheet;

import Control.BaseController;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.DriverManager;
import java.time.Duration;
import java.util.List;

public class TimesheetPage extends BaseController {

    public TimesheetPage() {
        initPage();
    }

    @FindBy(xpath = "//a[contains(@href, '/time/viewTimeModule')]/span")
    private WebElement btnTimesheet;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElement txtNombre;

    @FindBy(css = "button[type='submit']")
    private WebElement btnEnviar;

    @FindBy(xpath = "//div[text()='Employee Name']/following-sibling::div[contains(@class, 'data')]")
    private WebElement txtNombreEmployee;

    @FindBy(css = ".oxd-autocomplete-dropdown[role='listbox']")
    private WebElement autocompleteDropdown;

    @FindBy(xpath = "//div[contains(@class,'oxd-autocomplete-option')]")
    private List<WebElement> dropdownOptions;

    @FindBy(css = "button.oxd-button--secondary")
    private WebElement btnView;

    @FindBy(xpath = "//p[contains(@class,'oxd-alert-content-text')]")
    private WebElement lblNoTimesheetsFound;

    @FindBy(xpath = "//input[@placeholder='dd-mm-yyyy']")
    private WebElement txtDate;


    public void clickTimeMenu() {
        try {
            if (btnTimesheet.isDisplayed()) {
                btnTimesheet.click();
            }
        } catch (Exception e) {
            System.out.println("Error al hacer clic en el menú de tiempo: " + e.getMessage());
        }
    }

    public void llenarNombre(String partialName) {

        txtNombre.clear();
        txtNombre.sendKeys(partialName);

        try {
            Thread.sleep(2000); // esperar que carguen las sugerencias
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!dropdownOptions.isEmpty()) {
            dropdownOptions.get(0).click();
        }
    }

    public void clickSubmitButton() {
        try {
            if (btnEnviar.isDisplayed()) {
                btnEnviar.click();
            }
        } catch (Exception e) {
            System.out.println("Error al hacer clic en el botón de envío: " + e.getMessage());
        }
    }

    public void validarNombreEmployee(String nombreEsperado) {
        String nombreEnPantalla = txtNombreEmployee.getText().trim();
        if (nombreEnPantalla.equals(nombreEsperado)) {
            System.out.println("¡Validación exitosa! El nombre: " + nombreEsperado + " es correcto.");
        } else {
            System.out.println("No encontró el nombre: " + nombreEsperado + " (Se encontró: " + nombreEnPantalla + ")");
        }
    }

    public boolean selectFirstSuggestion(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Esperar a que desaparezca "Searching..."
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Searching...')]")
        ));
        // Esperar a que exista al menos una opción
        wait.until(driver1 -> !dropdownOptions.isEmpty());
        for (WebElement option : dropdownOptions) {
            String texto = option.getText().trim();
            if (texto.equalsIgnoreCase("Searching..."))
                continue;
            if (texto.equalsIgnoreCase("No Records Found"))
                return false;
            wait.until(ExpectedConditions.elementToBeClickable(option));
            option.click();
            return true;
        }
        return false;
    }

    public boolean selectSuggestion(String employeeName, WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Searching...')]")
        ));

        wait.until(driver1 -> !dropdownOptions.isEmpty());

        for (WebElement option : dropdownOptions) {

            String texto = option.getText().trim();

            if (texto.equalsIgnoreCase("Searching..."))
                continue;

            if (texto.equalsIgnoreCase("No Records Found"))
                return false;

            if (texto.equalsIgnoreCase(employeeName)) {
                wait.until(ExpectedConditions.elementToBeClickable(option));
                option.click();
                return true;
            }
        }

        return false;
    }

    public void clickView(){
        try {
            if (btnView.isDisplayed()) {
                btnView.click();
            }
        } catch (Exception e) {
            System.out.println("Error al hacer clic en el botón View: " + e.getMessage());
        }
    }

    public void validarTimesheet(String period){
        try {
            if (lblNoTimesheetsFound.isDisplayed()) {
                System.out.println("Mensaje de alerta: " + lblNoTimesheetsFound.getText());
            }
        } catch (Exception e) {
            System.out.println("Error al validar el timesheet: " + e.getMessage());
        }
    }

    public void selectPeriod(String period) {
        try {
            if (txtDate.isDisplayed()) {
                txtDate.click();
                txtDate.sendKeys(period);
            }
        } catch (Exception e) {
            System.out.println("Error al hacer clic en el period: " + e.getMessage());
        }
    }
}
