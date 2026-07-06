package com.automation.locators;

import org.openqa.selenium.By;

/**
 * Locators for Signup page
 * Contains all By locators used in signup flow
 */
public class SignupLocators {

    // Mobile input field
    public static final By MOBILE_INPUT = By.xpath("(//input[@type='tel'])[3]");

    // Password input field
    public static final By PASSWORD_INPUT = By.xpath("//input[@id='outlined-adornment-password']");

    // Error message
    public static final By ERROR_MESSAGE = By.className("signup-error");

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
