package hooks;

import driver.InitWebDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @Before
    public void initWebDriver() {
        InitWebDriver.init();
    }

    @After
    public void quitBrowser(Scenario scenario) {
        if (scenario.isFailed()) {
            TakesScreenshot screenshot = InitWebDriver.driver;
            byte[] imageByte = screenshot.getScreenshotAs(OutputType.BYTES);
            scenario.attach(imageByte, "image/png", scenario.getId());
        }
        InitWebDriver.quit();
    }
}