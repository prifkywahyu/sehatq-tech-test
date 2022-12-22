package pages;

import config.GlobalVariable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RegisterPage extends BasePage {

    ChromeDriver driver;
    GlobalVariable globalVariable = new GlobalVariable();

    private final By inputFullName = By.id("name");
    private final By inputEmail = By.id("email");
    private final By inputPassword = By.name("password");
    private final By termsCheckbox = By.xpath("//div[@class='sc-bqiQRQ bZZvNs']");
    private final By textError = By.xpath("//div[@class='sc-gWXaA-D hKFCEo']");
    private final By buttonRegister = By.xpath("//button[@type='submit']");
    private final By ctaToRegister = By.xpath("//span[@class='sc-dkPtyc hjXmuM']");
    private final By iconProfile = By.xpath("//img[@alt='SehatQ Profile']");
    private final By titleOtpVerify = By.xpath("//span[@class='sc-dkPtyc iThpYw']");

    public RegisterPage(ChromeDriver driver) {
        this.driver = driver;
    }

    public void goToRegisterPage() {
        clickElement(iconProfile);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        clickElement(ctaToRegister);
        waitUntil(ExpectedConditions.visibilityOfElementLocated(inputFullName));
        System.out.println("Redirected to register page");
    }

    public void inputForRegister(String name, String email, String password) {
        Assert.assertTrue(verifyElementIsPresent(inputFullName));
        Assert.assertTrue(verifyElementIsPresent(inputEmail));
        Assert.assertTrue(verifyElementIsPresent(inputPassword));
        setText(inputFullName, name);
        setText(inputEmail, email);
        setText(inputPassword, password);
    }

    public void setDynamicRegister() {
        String setEmail = globalVariable.inputEmail;
        Assert.assertTrue(verifyElementIsPresent(inputFullName));
        Assert.assertTrue(verifyElementIsPresent(inputEmail));
        Assert.assertTrue(verifyElementIsPresent(inputPassword));
        setText(inputFullName, globalVariable.inputName);
        setText(inputEmail, setEmail);
        setText(inputPassword, globalVariable.inputPassword);
        globalVariable.setEmail(setEmail);
    }

    public void clickTermsCheckbox() {
        scrollToElement(inputFullName);
        waitUntil(ExpectedConditions.visibilityOfElementLocated(termsCheckbox));
        clickElementByOffset(termsCheckbox);
    }

    public void clickButtonRegister() {
        waitUntil(ExpectedConditions.visibilityOfElementLocated(buttonRegister));
        clickElement(buttonRegister);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        System.out.println("User do submit register");
    }

    public void verifySuccessRegister() {
        waitUntil(ExpectedConditions.visibilityOfElementLocated(titleOtpVerify));
        String getUrl = driver.getCurrentUrl();
        String titleOtp = getText(titleOtpVerify);
        System.out.println(titleOtp);
        Assert.assertTrue(titleOtp.contains(globalVariable.getEmail()));
        Assert.assertEquals(globalVariable.urlOtpVerify, getUrl);
    }

    public void getTextError() {
        if (verifyElementIsPresent(textError)) {
            List<WebElement> elements = findElements(textError);
            List<String> list = new ArrayList<>();
            for (WebElement element : elements) {
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