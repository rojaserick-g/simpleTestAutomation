package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Runner para ejecutar el escenario TC-001 de PIM en BrowserStack
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/PIM.feature",
        glue = {"StepDefinition"},
        tags = "@TC-001",
        plugin = {
                "pretty",
                "html:target/browserstack-report.html",
                "json:target/browserstack-report.json"
        },
        monochrome = true
)
public class BrowserStackRunner {
}