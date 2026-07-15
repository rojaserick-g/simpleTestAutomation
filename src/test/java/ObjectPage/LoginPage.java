package ObjectPage;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Control.BaseController;

public class LoginPage extends BaseController {

    public LoginPage() {
        initPage();
    }

    @FindBy(name = "username")
    private WebElement nombreUsuario;

    @FindBy(name = "password")
    private WebElement passwordUsuario;

    @FindBy(css = ".orangehrm-login-button")
    private WebElement btnLogin;


    public void escribirUsersname(String nUsuario) {
        try {
            if (visualizarElemento(this.nombreUsuario, 10)) {
                this.nombreUsuario.clear();
                this.nombreUsuario.sendKeys(nUsuario);
            } else {
                Assert.fail("No se encuentra el campo Nombre de Usuario en la página");
            }
        } catch (Exception e) {
            Assert.fail("Error al escribir en el campo de usuario: " + e.getMessage());
        }
    }

    public void escribirPassword(String pass) {
        try {
            this.passwordUsuario.clear();
            this.passwordUsuario.sendKeys(pass);
        } catch (Exception e) {
            Assert.fail("Error al escribir en el campo de password: " + e.getMessage());
        }
    }

    public void clickBtnLogin() {
        try {
            this.btnLogin.click();
        } catch (Exception e) {
            Assert.fail("Error al hacer click en el botón Submit: " + e.getMessage());
        }
    }
}