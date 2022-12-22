package stepdefs;

import driver.InitAppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginStepDefs {

    LoginPage loginPage = new LoginPage(InitAppiumDriver.appiumDriver);

    @Given("user already in login page")
    public void userAlreadyInLoginPage() {
        loginPage.goToLoginPage();
    }

    @When("user input email {string} and password {string}")
    public void userInputEmailAndPassword(String email, String password) {
        loginPage.inputForLogin(email, password);
    }

    @And("user click button login")
    public void userClickButtonLogin() {
        loginPage.clickButtonLogin();
    }

    @Then("user is successfully logged in")
    public void userIsSuccessfullyLoggedIn() {
        loginPage.verifySuccessLogin();
    }

    @Then("user failed to login with error message")
    public void userFailedToLoginWithErrorMessage() {
        loginPage.getTextError();
    }
}