package pages;

import config.GlobalVariable;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RegisterPage extends BasePage {

    AppiumDriver<MobileElement> appiumDriver;
    GlobalVariable globalVariable = new GlobalVariable();

    private final By getBanner = By.id("com.she.sehatq:id/ivHero");
    private final By inputFullName = By.xpath("(//*[@resource-id='com.she.sehatq:id/et_input'])[1]");
    private final By inputGender = By.xpath("(//*[@resource-id='com.she.sehatq:id/et_input'])[2]");
    private final By inputEmail = By.xpath("(//*[@resource-id='com.she.sehatq:id/et_input'])[3]");
    private final By inputPassword = By.xpath("(//*[@resource-id='com.she.sehatq:id/et_input'])[4]");
    private final By chooseMale = By.id("com.she.sehatq:id/tvMale");
    private final By termsCheckbox = By.id("com.she.sehatq:id/cb_terms");
    private final By textError = By.id("com.she.sehatq:id/tv_error");
    private final By buttonRegister = By.id("com.she.sehatq:id/btn_signup");
    private final By ctaToRegister = By.id("com.she.sehatq:id/tv_signup");
    private final By iconProfile = By.id("com.she.sehatq:id/iv_profile_nav");
    private final By titleOtpVerify = By.id("com.she.sehatq:id/tvDescription");
    private final By emailOtpVerify = By.id("com.she.sehatq:id/tvEmail");

    public RegisterPage(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void goToRegisterPage() {
        appiumDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        waitUntil(ExpectedConditions.visibilityOfElementLocated(getBanner));
        appiumDriver.findElement(iconProfile).isEnabled();
        clickElement(iconProfile);
        waitUntil(ExpectedConditions.visibilityOfElementLocated(ctaToRegister));
        clickElement(ctaToRegister);
        waitUntil(ExpectedConditions.visibilityOfElementLocated(buttonRegister));
        System.out.println("Redirected to register page");
    }

    public void inputForRegister(String name, String email, String password) {
        Assert.assertTrue(verifyElementIsPresent(inputFullName));
        Assert.assertTrue(verifyElementIsPresent(inputEmail));
        Assert.assertTrue(verifyElementIsPresent(inputPassword));
        setText(inputFullName, name);
        setText(inputEmail, email);
        setText(inputPassword, password);
        clickElement(inputGender);
        clickElement(chooseMale);
        appiumDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    public void setDynamicRegister() {
        String setEmail = globalVariable.inputEmail;
        Assert.assertTrue(verifyElementIsPresent(inputFullName));
        Assert.assertTrue(verifyElementIsPresent(inputEmail));
        Assert.assertTrue(verifyElementIsPresent(inputPassword));
        setText(inputFullName, globalVariable.inputName);
        setText(inputEmail, setEmail);
        setText(inputPassword, globalVariable.inputPassword);
        clickElement(inputGender);
        clickElement(chooseMale);
        appiumDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        globalVariable.setEmail(setEmail);
    }

    public void clickTermsCheckbox() {
        waitUntil(ExpectedConditions.visibilityOfElementLocated(termsCheckbox));
        clickElement(termsCheckbox);
    }

    public void clickButtonRegister() {
        waitUntil(ExpectedConditions.visibilityOfElementLocated(buttonRegister));
        clickElement(buttonRegister);
        appiumDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        System.out.println("User do submit register");
    }

    public void verifySuccessRegister() {
        waitUntil(ExpectedConditions.visibilityOfElementLocated(titleOtpVerify));
        String titleOtp = getText(emailOtpVerify);
        System.out.println("Sent OTP code to: " + titleOtp);
        Assert.assertTrue(titleOtp.contains(globalVariable.getEmail()));
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
            System.out.println("Register failed with error: " + String.join(", ", list));
            Assert.assertFalse(verifyElementNotPresent(textError));
        } else {
            System.out.println("Unfortunately register was succeed");
        }
    }
}