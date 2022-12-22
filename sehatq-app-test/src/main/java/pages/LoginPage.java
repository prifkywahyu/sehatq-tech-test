package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginPage extends BasePage {

    AppiumDriver<MobileElement> appiumDriver;

    private final By getBanner = By.id("com.she.sehatq:id/ivHero");
    private final By inputEmail = By.xpath("(//*[@resource-id='com.she.sehatq:id/et_input'])[1]");
    private final By inputPassword = By.xpath("(//*[@resource-id='com.she.sehatq:id/et_input'])[2]");
    private final By buttonLogin = By.id("com.she.sehatq:id/btn_login");
    private final By textError = By.id("com.she.sehatq:id/tv_error");
    private final By imageError = By.id("com.she.sehatq:id/iv_error");
    private final By popupError = By.id("com.she.sehatq:id/tv_detail");
    private final By buttonOkError = By.id("com.she.sehatq:id/btn_ok");
    private final By iconProfile = By.id("com.she.sehatq:id/iv_profile_nav");
    private final By textFullName = By.id("com.she.sehatq:id/tvFullName");

    public LoginPage(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void goToLoginPage() {
        appiumDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        waitUntil(ExpectedConditions.visibilityOfElementLocated(getBanner));
        appiumDriver.findElement(iconProfile).isEnabled();
        clickElement(iconProfile);
        waitUntil(ExpectedConditions.visibilityOfElementLocated(buttonLogin));
        System.out.println("Redirected to login page");
    }

    public void inputForLogin(String email, String password) {
        Assert.assertTrue(verifyElementIsPresent(inputEmail));
        Assert.assertTrue(verifyElementIsPresent(inputPassword));
        setText(inputEmail, email);
        setText(inputPassword, password);
    }

    public void clickButtonLogin() {
        waitUntil(ExpectedConditions.visibilityOfElementLocated(buttonLogin));
        clickElement(buttonLogin);
        appiumDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        System.out.println("User do submit login");
    }

    public void verifySuccessLogin() {
        waitUntil(ExpectedConditions.visibilityOfElementLocated(iconProfile));
        clickElement(iconProfile);
        waitUntil(ExpectedConditions.visibilityOfElementLocated(textFullName));
        System.out.println("User logged in: " + getText(textFullName));
        Assert.assertTrue(verifyElementIsPresent(textFullName));
    }

    public void getTextError() {
        if (verifyElementIsPresent(textError)) {
            List<MobileElement> elements = appiumDriver.findElements(textError);
            List<String> list = new ArrayList<>();
            for (MobileElement element : elements) {
                if (!list.contains(element.getText()) && !element.getText().isEmpty()) {
                    list.add(element.getText());
                }
            }
            System.out.println("Login failed with error: " + String.join(", ", list));
            Assert.assertFalse(verifyElementNotPresent(textError));
        } else {
            waitUntil(ExpectedConditions.visibilityOfElementLocated(imageError));
            String getErrorFloat = getText(popupError);
            System.out.println("Login failed with error: " + getErrorFloat);
            Assert.assertFalse(verifyElementNotPresent(popupError));
            clickElement(buttonOkError);
        }
    }
}