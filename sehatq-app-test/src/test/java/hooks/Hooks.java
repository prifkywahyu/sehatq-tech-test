package hooks;

import driver.InitAppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @Before
    public void initAppiumDriver() throws Exception {
        InitAppiumDriver.init();
    }

    @After
    public void quitAppiumDriver(Scenario scenario) {
        if (scenario.isFailed()) {
            TakesScreenshot screenshot = InitAppiumDriver.appiumDriver;
            byte[] imageByte = screenshot.getScreenshotAs(OutputType.BYTES);
            scenario.attach(imageByte, "image/png", scenario.getId());
        }
        InitAppiumDriver.quit();
    }
}