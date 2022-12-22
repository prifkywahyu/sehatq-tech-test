package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginPage extends BasePage {

    ChromeDriver driver;

    private final By inputEmail = By.id("email");
    private final By inputPassword = By.name("password");
    private final By buttonLogin = By.xpath("//button[@type='submit']");
    private final By textError = By.xpath("//div[@class='sc-gWXaA-D hNYrMr']");
    private final By iconProfile = By.xpath("//img[@alt='SehatQ Profile']");
    private final By floatingError = By.xpath("//div[@type='danger']");
    private final By iconProfileLogin = By.id("popover-profile");
    private final By menuProfile = By.xpath("//*[@id='popover-profile']/div[2]/div/a[1]");
    private final By textFullName = By.xpath("//*[@id='__next']/div[2]/div[1]/div/div[1]/div[1]/div/p[1]");

    public LoginPage(ChromeDriver driver) {
        this.driver = driver;
    }

    public void goToLoginPage() {
        clickElement(iconProfile);
        waitUntil(ExpectedConditions.visibilityOfElementLocated(inputEmail));
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
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        System.out.println("User do submit login");
    }

    public void verifySuccessLogin() {
        waitUntil(ExpectedConditions.visibilityOfElementLocated(iconProfileLogin));
        clickElement(iconProfileLogin);
        clickElement(menuProfile);
        System.out.println("User logged in: " + getText(textFullName));
        Assert.assertTrue(verifyElementIsPresent(textFullName));
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
            System.out.println("Login failed with error: " + String.join(", ", list));
            Assert.assertFalse(verifyElementNotPresent(textError));
        } else {
            waitUntil(ExpectedConditions.visibilityOfElementLocated(floatingError));
            String getErrorFloat = getText(floatingError);
            System.out.println("Login failed with error: " + getErrorFloat);
            Assert.assertFalse(verifyElementNotPresent(floatingError));
        }
    }
}