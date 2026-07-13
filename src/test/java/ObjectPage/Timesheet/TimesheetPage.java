package ObjectPage.Timesheet;

import Control.BaseController;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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



    public void clickTimeMenu() {
        try{
            if (btnTimesheet.isDisplayed()) {
                btnTimesheet.click();
            }
        } catch (Exception e) {
            System.out.println("Error al hacer clic en el menú de tiempo: " + e.getMessage());
        }
    }

    public void llenarNombre(String nombre) {
        try {
            if (txtNombre.isDisplayed()) {
                txtNombre.click();
                txtNombre.clear();
                txtNombre.sendKeys(nombre);
            }
        } catch (Exception e) {
            System.out.println("Error al llenar el campo de nombre: " + e.getMessage());
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
            System.out.println("¡Validación exitosa! El nombre: "+nombreEsperado+" es correcto.");
        } else {
            System.out.println("No encontró el nombre: " + nombreEsperado + " (Se encontró: " + nombreEnPantalla + ")");
        }
    }



}
