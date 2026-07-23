package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Runner para ejecutar tests SOLO en BrowserStack
 * Ejecuta ÚNICAMENTE el archivo browserstack_test.feature
 * 
 * Uso: gradle test --tests BrowserStackRunner
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/browserstack_test.feature",
        glue = {"StepDefinition"},
        tags = "@browserstack",
        plugin = {
                "pretty",
                "html:target/browserstack-report.html",
                "json:target/browserstack-report.json"
        },
        monochrome = true
)
public class BrowserStackRunner {
}

