package driver;

import config.GlobalVariable;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class InitWebDriver {

    public static ChromeDriver driver;

    public static void init() {
        GlobalVariable globalVariable = new GlobalVariable();
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito", "--start-maximized");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(globalVariable.baseUrl);
    }

    public static void quit() {
        driver.quit();
    }
}