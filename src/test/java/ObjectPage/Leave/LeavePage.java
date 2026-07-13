package ObjectPage.Leave;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeavePage {
    WebDriver driver;

    public LeavePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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
    public WebElement cbxLeavelType; // Mantiene el nombre con el que lo declaraste original

    @FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/div/div[1]/div/div[2]/div/div/input")
    public WebElement txtEmployeeName;

    @FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/div/div[2]/div/div[2]/div/div/div[1]")
    public WebElement cbxSubUnit;

    @FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/div/div[3]/div/label/span")
    public WebElement btnPastEmployees; // Cambiado a public para consistencia

    @FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[3]/button[2]")
    public WebElement btnSearch; // Cambiado a public

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[3]/button[1]")
    public WebElement btnReset;

    @FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/div/div[1]/div/div/div[2]/div/div/li/button/i")
    public WebElement btnOpciones;

    public void navigateToLeaveList(){
        leaveMenu.click();
        btnLeave.click();
        btnLeaveList.click();
    }

    // selecciona las opciones de los combos de orange HR
    public void selectDropdownOption(WebElement dropdown, String optionText){
        dropdown.click();
        WebElement option = driver.findElement(By.xpath("//div[@role='listbox']//span[text()='" + optionText + "']"));
        option.click();
    }

    // flujo que rellena
    public void fillLeaveSearchFrom(String fromDateValue, String toDateValue, String status, String leaveType, String employee, String subUnit, boolean includePast){
        // Se usan los nombres exactos declarados arriba
        fromDate.sendKeys(fromDateValue);
        toDate.sendKeys(toDateValue);

        // combo box metodo dinamico
        selectDropdownOption(cbxShowLeaveStatus, status);
        selectDropdownOption(cbxLeavelType, leaveType); // Corregido para que coincida con el @FindBy

        txtEmployeeName.sendKeys(employee);
        selectDropdownOption(cbxSubUnit, subUnit);

        if(includePast){
            btnPastEmployees.click();
        }
        btnSearch.click();
    }
}