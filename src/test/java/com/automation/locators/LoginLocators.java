package com.automation.locators;

import org.openqa.selenium.By;

public class LoginLocators {

    public static final By LOGIN_SIGNUP_BUTTON = By.xpath("//button[normalize-space()='Log In / Sign Up']");

    public static final By PASSWORD_TAB = By.xpath("(//span[normalize-space()='Password'])[3]");

    public static final By MOBILE_INPUT = By.xpath("(//input[@type='tel'])[3]");

    public static final By PASSWORD_INPUT = By.xpath("//input[@id='outlined-adornment-password']");

    public static final By LOGIN_BUTTON = By.xpath("(//button[normalize-space()='continue'])[3]");

    public static final By REQUEST_OTP_BUTTON = By.id("requestOtpBtn");

    public static final By OTP_INPUT = By.id("otpInput");

    public static final By ERROR_MESSAGE = By.className("error-message");

    public static final By SUCCESS_MESSAGE = By.className("success-message");

    public static final By MOVE_TO_HOME_PAGE_BUTTON = By.xpath("(//a[normalize-space()='Home'])[2]");

    public static final By QUESTIONNAIRE_PAGE_BUTTON = By.xpath("//a[normalize-space()='Questionnarie Form']");

    public static final By PORTFOLIO_PAGE_BUTTON = By.xpath("//a[normalize-space()='My Portfolio']");

    public static final By OPEN_SIDEBAR_BUTTON = By.cssSelector("button[aria-label='Open sidebar']");

    public static final By LOGIN_SUCCESS_TOAST =
            By.xpath("//div[text()='You have successfully logged in']");

}
  