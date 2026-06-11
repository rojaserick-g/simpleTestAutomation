package StepDefinition;

import ObjectPage.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class LoginDefinition {
    private final LoginPage loginPage = new LoginPage();

    @And("ingresar el usuario {string}")
    public void ingresarElUsuario(String nombreUsuario) {

        loginPage.escribirUsersname(nombreUsuario);
    }

    @And("ingresar la pass {string}")
    public void ingresarLaPass(String pass) {
        loginPage.escribirPassword(pass);
    }

    @When("presiono el boton Submit")
    public void presionoElBotonSubmit() {
        loginPage.clickBtnSubmit();
    }

    @Then("se valida el mensaje usuario invalido")
    public void seValidaElMensajeUsuarioInvalido() {
        loginPage.validarMsgUsuarioInvalido();
    }

}