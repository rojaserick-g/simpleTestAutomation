package Control;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import Constant.Navegador;

public class DriverManager {
    private WebDriver driver;

    protected void resolverDriver(Navegador nav, String url){
        String os = System.getProperty("os.name").toLowerCase();
        String osVersion = System.getProperty("os.version").toLowerCase();
        System.out.println("\nSistema Operativo :" + os+", "+osVersion);
        System.out.println("\nNavegador :" + nav);
        switch (nav) {
            case Chrome -> {
                System.out.println("Chrome seleccionado");
                ChromeOptions configuracionChrome = new ChromeOptions();
                if(os.contains("linux")){
                    System.out.println(System.getProperty("user.name"));
                    configuracionChrome.addArguments("--disable-dev-shm-usage");
                    configuracionChrome.addArguments("--no-sandbox");
                    configuracionChrome.addArguments("--disable-gpu");
                    configuracionChrome.addArguments("--headless");
                    configuracionChrome.addArguments("--ignore-ssl-errors=yes");
                    configuracionChrome.addArguments("--windows-size=1920x1080");
                }
               // WebDriverManager.chromedriver().browserVersion("147").setup();
                configuracionChrome.addArguments("--remote-allow-origins=yes");
                System.setProperty("webdriver.manager.verbose","true");
                this.driver = new ChromeDriver(configuracionChrome);
                this.driver.manage().deleteAllCookies();
            }
            default -> System.out.println("No es posible levantar el navegador " + nav);
        }
        if (this.driver != null) {
            //this.driver.manage().window().maximize();
            this.driver.manage().window().setSize(new org.openqa.selenium.Dimension(1920,1080));
            this.driver.manage().window().setSize(new Dimension(1920, 1080));
            this.driver.get(url);
        }
    }

    protected WebDriver getDriver(){
        if(driver == null){
            return null;
        }else{
            return driver;
        }
    }
}
