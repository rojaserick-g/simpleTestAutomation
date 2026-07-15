package StepDefinition;

import ObjectPage.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class LoginDefinition {
    private final LoginPage loginPage = new LoginPage();

    @And("ingresar el usuario {string}")
    public void ingresarElUsuario(String nombreUsuario) throws InterruptedException {
        Thread.sleep(2000);
        loginPage.escribirUsersname(nombreUsuario);
    }

    @And("ingresar la pass {string}")
    public void ingresarLaPass(String pass) throws InterruptedException {
        Thread.sleep(2000);
        loginPage.escribirPassword(pass);
    }

    @When("presiono el boton Login")
    public void presionoElBotonLogin() throws InterruptedException {
        Thread.sleep(2000);
        loginPage.clickBtnLogin();
    }

    @Given("the user is logged into OrangeHRM")
    public void loggedInToOrangeHRM() {
        loginPage.escribirUsersname("Admin");
        loginPage.escribirPassword("admin123");
        loginPage.clickBtnLogin();
    }

}