package com.automation.locators;

import org.openqa.selenium.By;

/**
 * Locators for Signup page
 * Contains all By locators used in signup flow
 */
public class SignupLocators {

    // Email input field
    public static final By EMAIL_INPUT = By.id("email");

    // Mobile input field
    public static final By MOBILE_INPUT = By.xpath("(//input[@type='tel'])[3]");

    // Password input field
    public static final By PASSWORD_INPUT = By.xpath("//input[@id='outlined-adornment-password']");

    // Confirm password input field
    public static final By CONFIRM_PASSWORD_INPUT = By.id("confirmPassword");

    // First name input field
    public static final By FIRST_NAME_INPUT = By.id("firstName");

    // Last name input field
    public static final By LAST_NAME_INPUT = By.id("lastName");

    // Signup button
    public static final By SIGNUP_BUTTON = By.id("signupBtn");

    // Terms and conditions checkbox
    public static final By TERMS_CHECKBOX = By.id("termsCheckbox");

    // Email verification link (from email)
    public static final By EMAIL_VERIFY_LINK = By.id("emailVerifyLink");

    // Verification success message
    public static final By VERIFICATION_SUCCESS_MSG = By.className("verification-success");

    // Already have account link
    public static final By LOGIN_LINK = By.linkText("Already have an account? Login");

    // Error message
    public static final By ERROR_MESSAGE = By.className("signup-error");

    // Success message
    public static final By SUCCESS_MESSAGE = By.className("signup-success");

    // Signup form container
    public static final By SIGNUP_FORM = By.id("signupForm");

    // Signup form name
    public static final By SIGNUP_FORM_NAME = By.xpath("//input[@placeholder='Enter your Name']");

    // Signup form pan card
    public static final By SIGNUP_FORM_PAN_CARD = By.cssSelector("input[name='pan_number']");
    
    // Signup form Gender dropdown
    public static final By GENDER_DROPDOWN =  By.id("mui-component-select-gender");

    // Signup form Marital Status dropdown
    public static final By MARITAL_STATUS_DROPDOWN = By.id("mui-component-select-marital_status");

    // Signup form Occupation dropdown
    public static final By OCCUPATION_DROPDOWN = By.id("mui-component-select-occupation");


    // Signup form Annual Income dropdown
    public static final By ANNUAL_INCOME_DROPDOWN = By.id("mui-component-select-annual_income");

        /**
     * Lock-in Period radio button
     *
     * @param value "yes" or "no"
     */
    public static By lockInPeriodRadio(String value) {
        return By.cssSelector(
            "input[name='lock_in_period_accepted'][value='" + value.toLowerCase() + "']"
        );
    }

    // Signup form verify pan card button
    public static final By SIGNUP_FORM_VERIFY_PAN_CARD_BUTTON =  By.xpath("//button[normalize-space()='Verify PAN']");

   // Signup form Profile Photo input field
    public static final By PROFILE_PHOTO_INPUT = By.cssSelector("input[type='file']");

    public static final By PASSWORD_OTP_INPUT = By.cssSelector("input[data-testid='passwordotp-input-0']");

    // Verify OTP button
    public static final By VERIFY_OTP_BUTTON = By.xpath("(//button[normalize-space()='continue'])[4]");

    // Signup form submit button
    public static final By SIGNUP_FORM_SUBMIT_BUTTON = By.xpath("//button[normalize-space()='submit']");

    // Signup form heading
    public static final By SIGNUP_FORM_HEADING = By.xpath("//h2[normalize-space()='Basic Info']");

}
