package ObjectPage.Leave;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class LeavePage extends ObjectPage.LoginPage {

    public LeavePage() {
        PageFactory.initElements(Control.DriverContext.getDriver(), this);
    }

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[1]/i")
    public WebElement leaveMenu;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[3]/a/span")
    public WebElement btnLeave;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[1]/div[2]/div[3]/button/i")
    public WebElement btnLeaveList;

    @FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div")
    public WebElement fromDate;

    @FindBy(xpath= "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/input")
    public WebElement toDate;

    @FindBy(xpath= "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[3]/div/div[2]/div")
    public WebElement cbxShowLeaveStatus;

    @FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div/div[1]")
    public WebElement cbxLeavelType;

    @FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/div/div[1]/div/div[2]/div/div/input")
    public WebElement txtEmployeeName;

    @FindBy(xpath = "//label[text()='Sub Unit']/ancestor::div[1]/following-sibling::div//div[contains(@class, 'oxd-select-wrapper')]")
    public WebElement cbxSubUnit;

    @FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/div/div[3]/div/label/span")
    public WebElement btnPastEmployees;

    @FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[3]/button[2]")
    public WebElement btnSearch;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[3]/button[1]")
    public WebElement btnReset;

    @FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/div/div[1]/div/div/div[2]/div/div/li/button/i")
    public WebElement btnOpciones;

    @FindBy(xpath = "//label[text()='From Date']/ancestor::div[1]/following-sibling::div//input")
    public WebElement txtFechaDesde;

    @FindBy(xpath = "//label[text()='To Date']/ancestor::div[1]/following-sibling::div//input")
    public WebElement txtFechaHasta;

    @FindBy(xpath = "//div[@role='listbox' or contains(@class, 'oxd-autocomplete-dropdown')]")
    private WebElement autocompleteDropdown;

    @FindBy(xpath = "//div[@class='oxd-table-body']/div[@class='oxd-table-card']/div[@role='row']/div[3]")
    private List<WebElement> celdasLeaveType;

    @FindBy(xpath = "//div[@class='oxd-table-body']/div[@class='oxd-table-card']/div[@role='row']/div[7]")
    private List<WebElement> celdasSubUnit;

    @FindBy(xpath = "//label[text()='Leave Type']/ancestor::div[1]/following-sibling::div//div[contains(@class, 'oxd-select-wrapper')]")
    public WebElement cbxLeaveType;

    @FindBy(xpath = "//div[contains(@class, 'oxd-input-group')][descendant::label[contains(translate(text(), 'LEAVE WITH STATUS', 'leave with status'), 'leave with status')]]//div[contains(@class, 'oxd-multiselect-wrapper') or contains(@class, 'oxd-select-wrapper')]")
    public WebElement cbxLeaveStatus;

    @FindBy(xpath = "//div[contains(@class, 'oxd-input-group')][descendant::label[contains(translate(text(), 'LEAVE WITH STATUS', 'leave with status'), 'leave with status')]]//span[contains(@class, 'oxd-chip')]//i[contains(@class, 'oxd-chip-icon')]")
    private List<WebElement> btnQuitarEstadosPredeterminados;

    // Selector para la columna 'Status' de la tabla de resultados (usualmente es la columna 6)
    @FindBy(xpath = "//div[@class='oxd-table-body']//div[@role='row']/div[@role='cell'][6]")
    private List<WebElement> celdasLeaveStatus;


    public WebElement getAutocompleteDropdown() {
        return autocompleteDropdown;
    }

    public WebDriver getDriver() {
        return Control.DriverContext.getDriver();
    }

    public void navigateToLeaveList(){
        btnLeave.click();
    }

    public void selectDropdownOption(WebDriver driver, WebElement dropdownContainer, String optionText) {
        dropdownContainer.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        By optionLocator = By.xpath("//div[@role='listbox']//div[@role='option' and .//*[text()='" + optionText + "']] | //div[@role='listbox']//div[@role='option'][normalize-space()='" + optionText + "']");
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
        option.click();
    }

    public WebElement getTxtEmployeeName() {
        return txtEmployeeName;
    }

    public List<WebElement> obtenerCeldasSubUnit() {
        return celdasSubUnit;
    }

    public List<WebElement> obtenerCeldasLeaveType() {
        return celdasLeaveType;
    }

    public List<WebElement> obtenerCeldasLeaveStatus() {
        return celdasLeaveStatus;
    }

    public List<WebElement> obtenerBotonesQuitarEstados() {
        return btnQuitarEstadosPredeterminados;
    }
}