package config;

import com.github.javafaker.Faker;

import java.util.Locale;

public class GlobalVariable {

    private String email;
    private String fullName;
    private String height;
    private String weight;

    Faker faker = new Faker(new Locale("in-ID"));

    public final String baseUrl = "https://www.sehatq.com/";
    public final String urlOtpVerify = "https://account.sehatq.com/daftar/verify-otp";
    public final String emailLogin = "uzumakiencep@gmail.com";
    public final String passwordLogin = "testing123";
    public final String inputName = faker.name().firstName();
    public final String lastName = faker.name().lastName();
    public final String inputEmail = inputName.toLowerCase() + faker.number().digits(3) + "@sehatq.id";
    public final String inputPassword = faker.backToTheFuture().quote().substring(0, 8);
    public final String inputHeight = faker.number().digits(3);
    public final String inputWeight = faker.number().digits(2);
    public final String inputAddress = faker.address().fullAddress();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}