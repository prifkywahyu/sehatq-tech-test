package stepdefs;

import driver.InitAppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.RegisterPage;

public class RegisterStepDefs {

    RegisterPage registerPage = new RegisterPage(InitAppiumDriver.appiumDriver);

    @Given("user already in register page")
    public void userAlreadyInRegisterPage() {
        registerPage.goToRegisterPage();
    }

    @When("user input full name, email and password")
    public void userInputFullNameEmailAndPassword() {
        registerPage.setDynamicRegister();
    }

    @When("user input full name {string}, email {string} and password {string}")
    public void userInputFullNameEmailAndPassword(String name, String email, String password) {
        registerPage.inputForRegister(name, email, password);
    }

    @And("user check terms and conditions")
    public void userCheckTermsAndConditions() {
        registerPage.clickTermsCheckbox();
    }

    @And("user click button register")
    public void userClickButtonRegister() {
        registerPage.clickButtonRegister();
    }

    @Then("user success redirect to OTP page")
    public void userSuccessRedirectToOtpPage() {
        registerPage.verifySuccessRegister();
    }

    @Then("user failed to register with error message")
    public void userFailedToRegisterWithErrorMessage() {
        registerPage.getTextError();
    }
}