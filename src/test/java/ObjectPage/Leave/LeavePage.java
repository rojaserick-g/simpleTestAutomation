package ObjectPage.Leave;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

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

    @FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/div/div[2]/div/div[2]/div/div/div[1]")
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

    public WebElement getAutocompleteDropdown() {
        return autocompleteDropdown;
    }

    // SOLUCIÓN AL NULL POINTER: Apuntar directamente al contexto real del Driver
    public WebDriver getDriver() {
        return Control.DriverContext.getDriver();
    }

    public void navigateToLeaveList(){
        btnLeave.click();
    }

    public void selectDropdownOption(WebDriver driver, WebElement dropdownContainer, String optionText) {
        dropdownContainer.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        By optionLocator = By.xpath("//div[@role='listbox']//span[text()='" + optionText + "']");
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
        option.click();
    }

    public WebElement getTxtEmployeeName() {
        return txtEmployeeName;
    }
}