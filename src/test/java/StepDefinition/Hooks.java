package StepDefinition;

import Constant.Constant;
import Control.BrowserStackCredentials;
import Control.DriverContext;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {

    private Scenario scenario;
    private static final String tomarCapturaPantalla;
    private static boolean credentialsValidated = false;

    static {
        tomarCapturaPantalla = System.getProperty("evidence", "fullEvidence");
    }

    @Before
    public void setUp(Scenario scenario) {

        this.scenario = scenario;
        Constant.scenarioStep = scenario;
        Constant.build_name = "Nombre de Proyecto";

        // Validar configuración de BrowserStack solo una vez
        if (!credentialsValidated) {
            System.out.println("\n📋 Validando configuración de BrowserStack...");
            BrowserStackCredentials.printConfig();
            credentialsValidated = true;
        }
    }

    @After
    public void tearDown() {

        WebDriver driver = DriverContext.getDriver();

        if (driver != null) {

            try {

                // Enviar resultado del escenario a BrowserStack
                JavascriptExecutor js = (JavascriptExecutor) driver;

                if (scenario.isFailed()) {

                    js.executeScript(
                            "browserstack_executor: {\"action\": \"setSessionStatus\", " +
                                    "\"arguments\": {\"status\":\"failed\", " +
                                    "\"reason\": \"Scenario failed\"}}");

                    System.out.println("❌ Escenario marcado como FAILED en BrowserStack");

                } else {

                    js.executeScript(
                            "browserstack_executor: {\"action\": \"setSessionStatus\", " +
                                    "\"arguments\": {\"status\":\"passed\", " +
                                    "\"reason\": \"Scenario passed\"}}");

                    System.out.println("✅ Escenario marcado como PASSED en BrowserStack");
                }

            } catch (Exception e) {

                System.out.println("⚠ No fue posible actualizar el estado en BrowserStack.");
                System.out.println(e.getMessage());

            } finally {

                DriverContext.quitDriver();

            }
        }
    }

    public void generarEvidencia(String imageRefName) {

        if (DriverContext.getDriver() == null) {

            System.out.println("Driver no inicializado.");
            return;
        }

        byte[] screenShot =
                ((TakesScreenshot) DriverContext.getDriver())
                        .getScreenshotAs(OutputType.BYTES);

        this.scenario.attach(
                screenShot,
                "image/png",
                imageRefName
        );
    }

    @AfterStep
    public void capturaEvidencia() {

        if (DriverContext.getDriver() == null) {
            return;
        }

        if (scenario.isFailed()) {

            generarEvidencia("[FAIL] Step ScreenShots");

        } else if (tomarCapturaPantalla.equalsIgnoreCase("fullEvidence")) {

            generarEvidencia("[SUCCESS] Step ScreenShots");
        }
    }
}