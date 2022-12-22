package pages;

import config.GlobalVariable;
import driver.InitWebDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProfilePage extends BasePage {

    ChromeDriver driver;
    GlobalVariable globalVariable = new GlobalVariable();
    LoginPage loginPage = new LoginPage(InitWebDriver.driver);

    private final By buttonToEditProfile = By.xpath("//*[@id='__next']/div[2]/div[2]/div[2]/div[2]/a");
    private final By titleEditProfile = By.xpath("//*[@id='__next']/div[1]/div/div/div[2]/div[1]/h2");
    private final By editFullName = By.name("name");
    private final By editWeight = By.name("weight");
    private final By editHeight = By.name("height");
    private final By editAddress = By.name("address");
    private final By generalTextError = By.xpath("//div[@class='sc-jAaTju bnbPMr']");
    private final By addressTextError = By.xpath("//div[@class='sc-elJkPf cQagAC']");
    private final By buttonSaved = By.xpath("//button[@class='sc-bxivhb giIfj sc-ifAKCX kmPYUr']");
    private final By textFullName = By.xpath("//*[@id='__next']/div[1]/div/div/div[2]/div[2]/div/div/div[1]/span[1]");
    private final By textWeight = By.xpath("//*[@id='__next']/div[1]/div/div/div[2]/div[2]/div/div/div[1]/div/div[3]/span[1]");
    private final By textHeight = By.xpath("//*[@id='__next']/div[1]/div/div/div[2]/div[2]/div/div/div[1]/div/div[2]/span[1]");

    public ProfilePage(ChromeDriver driver) {
        this.driver = driver;
    }

    public void triggerLoggedIn() {
        loginPage.goToLoginPage();
        loginPage.inputForLogin(globalVariable.emailLogin, globalVariable.passwordLogin);
        loginPage.clickButtonLogin();
        loginPage.verifySuccessLogin();
    }

    public void goToEditProfile() {
        clickElement(buttonToEditProfile);
        waitUntil(ExpectedConditions.visibilityOfElementLocated(editFullName));
        System.out.println("Redirected to edit profile page");
    }

    public void inputForEditProfile(String name, String height, String weight, String address) {
        Assert.assertTrue(verifyElementIsPresent(editFullName));
        Assert.assertTrue(verifyElementIsPresent(editHeight));
        Assert.assertTrue(verifyElementIsPresent(editWeight));
        Assert.assertTrue(verifyElementIsPresent(editAddress));
        setText(editFullName, name);
        setText(editHeight, height);
        setText(editWeight, weight);
        scrollToElement(editWeight);
        setText(editAddress, address);
    }

    public void setDynamicEditProfile() {
        String setFullName = globalVariable.inputName + " " + globalVariable.lastName;
        String setHeight = globalVariable.inputHeight;
        String setWeight = globalVariable.inputWeight;
        Assert.assertTrue(verifyElementIsPresent(editFullName));
        Assert.assertTrue(verifyElementIsPresent(editHeight));
        Assert.assertTrue(verifyElementIsPresent(editWeight));
        Assert.assertTrue(verifyElementIsPresent(editAddress));
        setText(editFullName, setFullName);
        setText(editHeight, setHeight);
        setText(editWeight, setWeight);
        scrollToElement(editWeight);
        setText(editAddress, globalVariable.inputAddress);
        globalVariable.setFullName(setFullName);
        globalVariable.setHeight(setHeight);
        globalVariable.setWeight(setWeight);
    }

    public void clickButtonSaved() {
        scrollToElement(editAddress);
        waitUntil(ExpectedConditions.visibilityOfElementLocated(buttonSaved));
        driver.findElement(buttonSaved).isEnabled();
        clickElement(buttonSaved);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        System.out.println("User submit edit profile");
    }

    public void verifySuccessEditProfile() {
        waitUntil(ExpectedConditions.visibilityOfElementLocated(textHeight));
        String newFullName = getText(textFullName);
        String newHeight = getText(textHeight);
        String newWeight = getText(textWeight);
        System.out.println("New full name: " + newFullName);
        Assert.assertEquals(globalVariable.getFullName(), newFullName);
        Assert.assertEquals(globalVariable.getHeight(), newHeight);
        Assert.assertEquals(globalVariable.getWeight(), newWeight);
    }

    public void getTextError() {
        if (verifyElementIsPresent(generalTextError)) {
            checkTextError(generalTextError);
        } else if (verifyElementIsPresent(addressTextError)) {
            checkTextError(addressTextError);
        } else if (verifyElementNotPresent(generalTextError)) {
            scrollToElement(titleEditProfile);
            checkTextError(generalTextError);
        } else {
            System.out.println("Unfortunately edit profile was succeed");
        }
    }

    private void checkTextError(By locator) {
        List<WebElement> elements = findElements(locator);
        List<String> list = new ArrayList<>();
        for (WebElement element : elements) {
            if (!list.contains(element.getText()) && !element.getText().isEmpty()) {
                list.add(element.getText());
            }
        }
        System.out.println("Edit profile failed with error: " + String.join(", ", list));
        Assert.assertFalse(verifyElementNotPresent(locator));
    }
}