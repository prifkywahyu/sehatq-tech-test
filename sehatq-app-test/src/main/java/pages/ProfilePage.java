package pages;

import config.GlobalVariable;
import driver.InitAppiumDriver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProfilePage extends BasePage {

    AppiumDriver<MobileElement> appiumDriver;
    GlobalVariable globalVariable = new GlobalVariable();
    LoginPage loginPage = new LoginPage(InitAppiumDriver.appiumDriver);

    private final By ctaDetailProfile = By.id("com.she.sehatq:id/tvDetailProfile");
    private final By buttonToEditProfile = By.id("com.she.sehatq:id/btnEdit");
    private final By editFullName = By.xpath("(//*[@resource-id='com.she.sehatq:id/et_input'])[1]");
    private final By editHeight = By.xpath("(//*[@resource-id='com.she.sehatq:id/et_input'])[4]");
    private final By editWeight = By.xpath("(//*[@resource-id='com.she.sehatq:id/et_input'])[5]");
    private final By editAddress = By.xpath("(//*[@resource-id='com.she.sehatq:id/et_input'])[4]");
    private final By textError = By.id("com.she.sehatq:id/tv_error");
    private final By errorToast = By.xpath("/hierarchy/android.widget.Toast");
    private final By buttonSaved = By.id("com.she.sehatq:id/btnSave");
    private final By textFullName = By.id("com.she.sehatq:id/tvFullName");
    private final By textHeight = By.id("com.she.sehatq:id/tvUserHeight");
    private final By imageSuccess = By.id("com.she.sehatq:id/imageView");
    private final By popupSuccess = By.id("com.she.sehatq:id/textView");
    private final By buttonOkSuccess = By.id("com.she.sehatq:id/btnOk");
    private final By iconSeeMore = By.id("com.she.sehatq:id/ivMore");
    private final By toSettingPage = By.xpath("(//*[@resource-id='com.she.sehatq:id/tvTitle'])[3]");
    private final By ctaToLogout = By.id("com.she.sehatq:id/tv_sign_out");
    private final By confirmLogout = By.id("com.she.sehatq:id/btn_yes");

    public ProfilePage(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void triggerLoggedIn() {
        loginPage.goToLoginPage();
        loginPage.inputForLogin(globalVariable.emailLogin, globalVariable.passwordLogin);
        loginPage.clickButtonLogin();
        loginPage.verifySuccessLogin();
    }

    public void goToEditProfile() {
        clickElement(ctaDetailProfile);
        swipeUp(1);
        clickElement(buttonToEditProfile);
        waitUntil(ExpectedConditions.visibilityOfElementLocated(editFullName));
        System.out.println("Redirected to edit profile page");
    }

    public void inputForEditProfile(String name, String height, String weight, String address) {
        Assert.assertTrue(verifyElementIsPresent(editFullName));
        Assert.assertTrue(verifyElementIsPresent(editHeight));
        Assert.assertTrue(verifyElementIsPresent(editWeight));
        setText(editFullName, name);
        setText(editHeight, height);
        setText(editWeight, weight);
        swipeUp(1);
        setText(editAddress, address);
    }

    public void setDynamicEditProfile() {
        String setFullName = globalVariable.inputName + " " + globalVariable.lastName;
        String setHeight = globalVariable.inputHeight;
        Assert.assertTrue(verifyElementIsPresent(editFullName));
        Assert.assertTrue(verifyElementIsPresent(editHeight));
        Assert.assertTrue(verifyElementIsPresent(editWeight));
        setText(editFullName, setFullName);
        setText(editHeight, setHeight);
        setText(editWeight, globalVariable.inputWeight);
        swipeUp(1);
        setText(editAddress, globalVariable.inputAddress);
        globalVariable.setFullName(setFullName);
        globalVariable.setHeight(setHeight);
    }

    public void clickButtonSaved() {
        waitUntil(ExpectedConditions.visibilityOfElementLocated(buttonSaved));
        appiumDriver.findElement(buttonSaved).isEnabled();
        clickElement(buttonSaved);
        appiumDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        System.out.println("User submit edit profile");
    }

    public void verifySuccessEditProfile() {
        waitUntil(ExpectedConditions.visibilityOfElementLocated(imageSuccess));
        System.out.println(getText(popupSuccess));
        clickElement(buttonOkSuccess);
        String newFullName = getText(textFullName);
        String newHeight = getText(textHeight);
        System.out.println("New full name: " + newFullName);
        Assert.assertEquals(globalVariable.getFullName(), newFullName);
        Assert.assertEquals(globalVariable.getHeight(), newHeight);
        if (verifyElementIsPresent(textFullName)) {
            appiumDriver.navigate().back();
            triggerLogout();
        }
    }

    public void getTextError() {
        if (verifyElementIsPresent(textError)) {
            checkTextError(textError);
        } else if (verifyElementIsPresent(errorToast)) {
            checkTextError(errorToast);
        } else if (verifyElementNotPresent(textError)) {
            swipeScreen("Down");
            checkTextError(textError);
        } else {
            System.out.println("Cannot get error in edit profile");
        }

        appiumDriver.navigate().back();
        if (verifyElementIsPresent(textFullName)) {
            appiumDriver.navigate().back();
            triggerLogout();
        }
    }

    private void triggerLogout() {
        clickElement(iconSeeMore);
        clickElement(toSettingPage);
        clickElement(ctaToLogout);
        clickElement(confirmLogout);
        Assert.assertTrue(verifyElementNotPresent(textFullName));
    }

    private void checkTextError(By locator) {
        List<MobileElement> elements = appiumDriver.findElements(locator);
        List<String> list = new ArrayList<>();
        for (MobileElement element : elements) {
            if (!list.contains(element.getText()) && !element.getText().isEmpty()) {
                list.add(element.getText());
            }
        }
        System.out.println("Edit profile failed with error: " + String.join(", ", list));
        Assert.assertFalse(verifyElementNotPresent(locator));
    }
}