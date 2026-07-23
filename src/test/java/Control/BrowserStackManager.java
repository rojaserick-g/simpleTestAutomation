package Control;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Gestiona la conexión y configuración de WebDriver con BrowserStack
 * Usa el protocolo W3C con capabilities específicas de BrowserStack
 */
public class BrowserStackManager {

    /**
     * Crea un WebDriver conectado a BrowserStack con configuración personalizada
     */
    public static WebDriver createBrowserStackDriver(String browserName, String browserVersion, 
                                                      String osName, String osVersion) 
            throws MalformedURLException {
        
        if (!BrowserStackCredentials.validateCredentials()) {
            throw new RuntimeException("BrowserStack credentials are not properly configured");
        }

        String username = BrowserStackCredentials.getUsername();
        String accessKey = BrowserStackCredentials.getAccessKey();
        
        // Codificar las credenciales para caracteres especiales (como @)
        String encodedUsername = URLEncoder.encode(username, StandardCharsets.UTF_8);
        String encodedAccessKey = URLEncoder.encode(accessKey, StandardCharsets.UTF_8);
        String BROWSERSTACK_URL = "https://" + encodedUsername + ":" + encodedAccessKey + "@hub.browserstack.com/wd/hub";
        
        ChromeOptions options = new ChromeOptions();
        
        // BrowserStack W3C capabilities (van en bstack:options)
        // IMPORTANTE: Solo incluir propiedades válidas en bstack:options
        Map<String, Object> bstackOptions = new HashMap<>();
        
        // Nombre del Proyecto - IMPORTANTE para aparecer en dashboard
        bstackOptions.put("projectName", BrowserStackCredentials.getProjectName());
        
        // Nombre del Build - IMPORTANTE para agrupar sesiones
        bstackOptions.put("buildName", BrowserStackCredentials.getBuildName());
        
        // Nombre de la Sesión
        bstackOptions.put("sessionName", BrowserStackCredentials.getSessionName());
        
        // OS Version
        bstackOptions.put("osVersion", osVersion);
        
        // Opciones adicionales (válidas para BrowserStack)
        bstackOptions.put("local", BrowserStackCredentials.isLocalEnabled());
        bstackOptions.put("debug", false);
        bstackOptions.put("networkLogs", false);
        bstackOptions.put("consoleLogs", "info");
        
        // Asignar capabilities
        options.setCapability("bstack:options", bstackOptions);
        options.setCapability("browserVersion", browserVersion);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🔗 CONECTANDO A BROWSERSTACK");
        System.out.println("=".repeat(60));
        System.out.println("📱 Usuario: " + username);
        System.out.println("🌐 Browser: " + browserName + " v" + browserVersion);
        System.out.println("💻 OS: " + osName + " " + osVersion);
        System.out.println("📦 Proyecto: " + BrowserStackCredentials.getProjectName());
        System.out.println("🏗️  Build: " + BrowserStackCredentials.getBuildName());
        System.out.println("📝 Sesión: " + BrowserStackCredentials.getSessionName());
        System.out.println("=".repeat(60));
        System.out.println("✅ Abriendo sesión remota en BrowserStack...");

        RemoteWebDriver driver = new RemoteWebDriver(new URL(BROWSERSTACK_URL), options);
        
        // Imprimir Session ID para rastreo
        String sessionId = driver.getSessionId().toString();
        System.out.println("✅ SESIÓN CREADA");
        System.out.println("   Session ID: " + sessionId);
        System.out.println("   URL Dashboard: https://automate.browserstack.com/builds");
        System.out.println("=".repeat(60) + "\n");
        
        return driver;
    }

    /**
     * Crea un WebDriver usando la configuración del archivo properties
     */
    public static WebDriver createBrowserStackDriver() throws MalformedURLException {
        return createBrowserStackDriver(
            BrowserStackCredentials.getBrowserName(),
            BrowserStackCredentials.getBrowserVersion(),
            BrowserStackCredentials.getOSName(),
            BrowserStackCredentials.getOSVersion()
        );
    }

    /**
     * Crea un WebDriver con navegador y versión específicos
     */
    public static WebDriver createBrowserStackDriver(String browserName, String browserVersion) 
            throws MalformedURLException {
        return createBrowserStackDriver(browserName, browserVersion, 
                                       BrowserStackCredentials.getOSName(),
                                       BrowserStackCredentials.getOSVersion());
    }

    /**
     * Verifica si las credenciales están configuradas y disponibles
     */
    public static boolean isBrowserStackConfigured() {
        return BrowserStackCredentials.validateCredentials();
    }
}
