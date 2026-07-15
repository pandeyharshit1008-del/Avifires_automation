package com.automation.utils;

import com.automation.pages.SignupPage;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

/**
 * High-level signup workflow that encapsulates the business flow and OTP handling.
 */
public class SignupWorkflow {

    private static final Logger logger = LoggerFactory.getLogger(SignupWorkflow.class);

    private final WebDriver driver;
    private final SignupPage signupPage;
    private final OTPInterceptor otpInterceptor;
    private final WebDriverWait otpWait;

    public SignupWorkflow(WebDriver driver) {
        this.driver = driver;
        this.signupPage = new SignupPage(driver);
        this.otpInterceptor = new OTPInterceptor(driver);
        this.otpWait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private String generateStrongPassword(Faker faker) {
        String password;

        do {
            password = faker.internet().password(12, 16, true, true, true);
        } while (!isStrongPassword(password));

        return password;
    }

    public boolean isStrongPassword(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).{12,16}$");
    }

    public UserCredentials generateRandomUser() {
        Faker faker = new Faker();

        String email = faker.internet().emailAddress();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String mobile = "+91" + faker.regexify("[6-9][0-9]{9}");
        String password = generateStrongPassword(faker);

        UserCredentials user = new UserCredentials(email, firstName, lastName, mobile, password);
        logger.info("Generated mobile: " + user.getMobile());
        logger.info("Generated password: " + user.getPassword());
        return user;
    }

    public void startOtpCapture() {
        otpInterceptor.startListening();
    }

    public void Signup(UserCredentials user) {
        signupPage.signUpWithPassword(user);
        verifyOtp();
    }

    public void completeSignup(UserCredentials user) {
        signupPage.signUpWithPassword(user);
        verifyOtp();
        signupPage.enterSignupFormDetails(user);
    }

    public boolean verifySignupSuccess() {
        boolean success = signupPage.verifySignupSuccess();
        if (success) {
            logger.info("Signup successful");
        }
        return success;
    }

    public void stopOtpCapture() {
        otpInterceptor.stopListening();
    }

    private void verifyOtp() {
        String otp = waitForOtp();
        if (otp == null || otp.isBlank()) {
            throw new IllegalStateException("OTP was not captured for signup");
        }

        logger.info("OTP captured");
        signupPage.enterPasswordOtp(otp);
        signupPage.clickVerifyOtpButton();
    }

    private String waitForOtp() {
        return otpWait.until(driver -> {
            String capturedOtp = otpInterceptor.getCapturedOtp();
            return (capturedOtp != null && !capturedOtp.isBlank()) ? capturedOtp : null;
        });
    }
}
