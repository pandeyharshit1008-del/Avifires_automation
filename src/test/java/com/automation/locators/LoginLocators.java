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

    // Request OTP button
    public static final By REQUEST_OTP_BUTTON = By.id("requestOtpBtn");

    // OTP input field
    public static final By OTP_INPUT = By.id("otpInput");

    // Error message
    public static final By ERROR_MESSAGE = By.className("error-message");

    // Success message
    public static final By SUCCESS_MESSAGE = By.className("success-message");

    // Move to home page button
    public static final By MOVE_TO_HOME_PAGE_BUTTON = By.xpath("(//a[normalize-space()='Home'])[2]");

    // Questionnaire page button
    public static final By QUESTIONNAIRE_PAGE_BUTTON = By.xpath("//a[normalize-space()='Questionnarie Form']");

//    Portfolio Page Button
    public static final By PORTFOLIO_PAGE_BUTTON = By.xpath("//a[normalize-space()='My Portfolio']");

    // Sidebar open button
    public static final By OPEN_SIDEBAR_BUTTON = By.cssSelector("button[aria-label='Open sidebar']");




}
  