package com.automation.locators;

import org.openqa.selenium.By;

public class SignupLocators {

    public static final By MOBILE_INPUT = By.xpath("(//input[@type='tel'])[3]");

    public static final By PASSWORD_INPUT = By.xpath("//input[@id='outlined-adornment-password']");

    public static final By ERROR_MESSAGE = By.className("signup-error");

    public static final By SIGNUP_FORM_NAME = By.xpath("//input[@placeholder='Enter your Name']");

    public static final By SIGNUP_FORM_PAN_CARD = By.cssSelector("input[name='pan_number']");
    
    public static final By GENDER_DROPDOWN =  By.id("mui-component-select-gender");

    public static final By MARITAL_STATUS_DROPDOWN = By.id("mui-component-select-marital_status");

    public static final By OCCUPATION_DROPDOWN = By.id("mui-component-select-occupation");

    public static final By ANNUAL_INCOME_DROPDOWN = By.id("mui-component-select-annual_income");

    public static final By SIGNUP_FORM_VERIFY_PAN_CARD_BUTTON =  By.xpath("//button[normalize-space()='Verify PAN']");

    public static final By PROFILE_PHOTO_INPUT = By.cssSelector("input[type='file']");

    public static final By PASSWORD_OTP_INPUT = By.cssSelector("input[data-testid='passwordotp-input-0']");

    public static final By VERIFY_OTP_BUTTON = By.xpath("(//button[normalize-space()='continue'])[4]");

    public static final By SIGNUP_FORM_SUBMIT_BUTTON = By.xpath("//button[normalize-space()='submit']");

    public static final By SIGNUP_FORM_HEADING = By.xpath("//h2[normalize-space()='Basic Info']");

}
