package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class InitAppiumDriver {

    public static WebDriverWait webDriverWait;
    public static AppiumDriver<MobileElement> appiumDriver;

    public static void init() throws Exception {
        appiumDriver = null;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy S22");
        capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.PAGE_LOAD_STRATEGY, "eager");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
        capabilities.setCapability("appPackage", "com.she.sehatq");
        capabilities.setCapability("appActivity", "com.she.sehatq.view.intro.SplashActivity");
        capabilities.setCapability("appWaitActivity", "com.she.sehatq.home.ui.HomeActivity");

        if (appiumDriver == null) {
            try {
                appiumDriver = new AppiumDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                System.out.println("App is started successfully!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                throw e;
            }
        } else {
            throw new Exception("Driver is null, abort task.");
        }

        webDriverWait = new WebDriverWait(appiumDriver, 15);
    }

    public static void quit() {
        appiumDriver.quit();
    }
}