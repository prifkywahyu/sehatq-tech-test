package stepdefs;

import driver.InitAppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ProfilePage;

public class ProfileStepDefs {

    ProfilePage profilePage = new ProfilePage(InitAppiumDriver.appiumDriver);

    @Given("user success to logged in")
    public void userSuccessToLoggedIn() {
        profilePage.triggerLoggedIn();
    }

    @When("user already in edit profile page")
    public void userAlreadyInEditProfilePage() {
        profilePage.goToEditProfile();
    }

    @And("user edit full name, height, weight and address")
    public void userEditFullNameHeightWeightAndAddress() {
        profilePage.setDynamicEditProfile();
    }

    @And("user edit full name {string}, height {string}, weight {string} and address {string}")
    public void userEditFullNameHeightWeightAndAddress(String name, String height, String weight, String address) {
        profilePage.inputForEditProfile(name, height, weight, address);
    }

    @And("user click button save profile")
    public void userClickButtonSaveProfile() {
        profilePage.clickButtonSaved();
    }

    @Then("user is successfully saved new profile")
    public void userIsSuccessfullySavedNewProfile() {
        profilePage.verifySuccessEditProfile();
    }

    @Then("user failed edit profile with error message")
    public void userFailedEditProfileWithErrorMessage() {
        profilePage.getTextError();
    }
}