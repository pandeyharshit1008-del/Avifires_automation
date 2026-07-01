package com.automation.locators;

import org.openqa.selenium.By;

/**
 * Locators for Login page
 * Contains all By locators used in login flow
 */
public class LoginLocators {

    // Login / Sign Up entry button
    public static final By LOGIN_SIGNUP_BUTTON = By.xpath("//button[normalize-space()='Log In / Sign Up']");

    // Password login tab
    public static final By PASSWORD_TAB = By.xpath("(//span[normalize-space()='Password'])[3]");

    // Phone input field for password login
    public static final By MOBILE_INPUT = By.xpath("(//input[@type='tel'])[3]");

    // Password input field for password login
    public static final By PASSWORD_INPUT = By.xpath("//input[@id='outlined-adornment-password']");

    // Continue / Login button
    public static final By LOGIN_BUTTON = By.xpath("(//button[normalize-space()='continue'])[3]");

    public static final By SUPPORT = By.xpath("//a[normalize-space()='Support']");

    // Request OTP button
    public static final By REQUEST_OTP_BUTTON = By.id("requestOtpBtn");

    // OTP input field
    public static final By OTP_INPUT = By.id("otpInput");
    public static final By PASSWORD_OTP_INPUT = By.cssSelector("input[data-testid='passwordotp-input-0']");

    // Verify OTP button
    public static final By VERIFY_OTP_BUTTON = By.xpath("(//button[normalize-space()='continue'])[4]");

    // Error message
    public static final By ERROR_MESSAGE = By.className("error-message");

    // Success message
    public static final By SUCCESS_MESSAGE = By.className("success-message");

    // Remember me checkbox
    public static final By REMEMBER_ME_CHECKBOX = By.id("rememberMe");

    // Forgot password link
    public static final By FORGOT_PASSWORD_LINK = By.linkText("Forgot Password?");

    // Login form container
    public static final By LOGIN_FORM = By.id("loginForm");

    // OTP form container
    public static final By OTP_FORM = By.id("otpForm");

    // Move to home page button
    public static final By MOVE_TO_HOME_PAGE_BUTTON = By.xpath("(//a[normalize-space()='Home'])[2]");

    // Questionnaire page button
    public static final By QUESTIONNAIRE_PAGE_BUTTON = By.xpath("//a[normalize-space()='Questionnarie Form']");

    // Financial return calculator slider
    public static final By FINANCIAL_CALCULATOR_SLIDER = By.id("slider-financialCalculator");

    // Financial return calculator slider thumb
    public static final By FINANCIAL_CALCULATOR_SLIDER_THUMB = By.cssSelector("#slider-financialCalculator .MuiSlider-thumb");

    // Sidebar open button
    public static final By OPEN_SIDEBAR_BUTTON = By.cssSelector("button[aria-label='Open sidebar']");

    // Expected Return Slider
    public static final By EXPECTED_RETURN_SLIDER = By.xpath("//input[@aria-labelledby='expected-return-slider']/ancestor::span[contains(@class,'MuiSlider-root')]");

    // Expected Return Slider Thumb
    public static final By EXPECTED_RETURN_SLIDER_THUMB = By.xpath("//input[@aria-labelledby='expected-return-slider']/ancestor::span[contains(@class,'MuiSlider-root')]//span[contains(@class,'MuiSlider-thumb')]");

    // Time Period Slider
    public static final By TIME_PERIOD_SLIDER = By.xpath("//input[@aria-labelledby='time-period-slider']/ancestor::span[contains(@class,'MuiSlider-root')]");

    // Time Period Slider Thumb
    public static final By TIME_PERIOD_SLIDER_THUMB = By.xpath("//input[@aria-labelledby='time-period-slider']/ancestor::span[contains(@class,'MuiSlider-root')]//span[contains(@class,'MuiSlider-thumb')]");

    public static final By InvestmentAmount = By.xpath("(//div[contains(@class,'tw-rounded-md')]//input)[1]");
    public static final By ExpectedReturn = By.xpath("(//div[contains(@class,'tw-rounded-md')]//input)[2]");
    public static final By TimePeriod = By.xpath("(//div[contains(@class,'tw-rounded-md')]//input)[3]");

    public static final String INVESTMENT_GOAL_OPTION = "//p[normalize-space()='%s']/ancestor::div[contains(@class,'tw-cursor-pointer')]";
    
    // Corpus Needed input field
    public static final By CORPUS_NEEDED_INPUT = By.xpath("//label[normalize-space()='Corpus Needed to Achieve Goal(Maturity Time)']/following-sibling::input");

    public static final By TIME_OF_INVESTMENT = By.xpath("//label[normalize-space()='Period to Realise Goal']/following-sibling::input");

    public static final By INVESTMENT_GOAL_SUBMIT_BUTTON = By.xpath("//button[normalize-space()='Submit']");

    public static final By PREFERENCES_AND_PRIORITIES = By.xpath("//span[normalize-space()='Preferences & Priorities']");

    public static final String RADIO_OPTION_BY_TEXT = "//label[normalize-space()='%s']";

    public static final By PREFERENCES_AND_PRIORITIES_SUBMIT_BUTTON = By.xpath("(//button[normalize-space()='Submit'])[2]");
}
  