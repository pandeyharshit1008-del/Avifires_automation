package com.automation.pages;

import com.automation.locators.LoginLocators;
import com.automation.utils.AssertUtils;
import com.automation.utils.HelperFunctions;
import com.automation.utils.ConfigConstants;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Login page object
 * Contains all login-related actions and workflows
 */
public class LoginPage extends HelperFunctions {

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);


    /**
     * Constructor to initialize driver and wait
     *
     * @param driver WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navigate to login page
     */
    public void navigateToLoginPage() {
        navigateTo(ConfigConstants.LOGIN_PAGE_URL);
        isVisible(LoginLocators.LOGIN_SIGNUP_BUTTON);
        logger.info("✓ Navigated to login page");
    }

    public void moveToHomePage() {
        navigateToPage(LoginLocators.MOVE_TO_HOME_PAGE_BUTTON, "Home page");
    }

    public void moveToQuestionnairePage() {
        navigateToPage(LoginLocators.QUESTIONNAIRE_PAGE_BUTTON, "Questionnaire page");
    }

    public void moveToPortfolioPage() {
        navigateToPage(LoginLocators.PORTFOLIO_PAGE_BUTTON, "Portfolio page");
        if (AssertUtils.assertUrlContains(driver, ConfigConstants.PORTFOLIO_PAGE_URL)) {
            logger.info("✓ Landed on portfolio page");
        } else {
            logger.info("✗ Failed to load portfolio page");
        }
    }

    /**
     * Open the sidebar menu
     */
    public void openSidebar() {
        click(LoginLocators.OPEN_SIDEBAR_BUTTON);
        logger.info("✓ Sidebar opened");
    }

    /**
     * Click the initial Log In / Sign Up entry button
     */
    public void clickLoginSignUpButton() {
        click(LoginLocators.LOGIN_SIGNUP_BUTTON);
        logger.info("✓ Log In / Sign Up clicked");
    }

    /**
     * Click the Password login tab
     */
    public void clickPasswordTab() {
        click(LoginLocators.PASSWORD_TAB);
        logger.info("✓ Password tab selected");
    }

    /**
     * Enter mobile number in mobile input field
     *
     * @param mobile Mobile number
     */
    public void enterMobile(String mobile) {
        type(LoginLocators.MOBILE_INPUT, mobile);
        logger.info("✓ Mobile entered");
    }

    /**
     * Enter password in password input field
     *
     * @param password Password
     */
    public void enterPassword(String password) {
        type(LoginLocators.PASSWORD_INPUT, password);
        logger.info("✓ Password entered");
    }

    /**
     * Click continue button to submit login form
     */
    public void clickLoginButton() {
        click(LoginLocators.LOGIN_BUTTON);
        logger.info("✓ Continue button clicked");
    }


    /**
     * Click request OTP button
     */
    public void clickRequestOtpButton() {
        click(LoginLocators.REQUEST_OTP_BUTTON);
        logger.info("✓ Request OTP button clicked");
    }

    /**
     * Enter OTP in OTP input field
     *
     * @param otp One-Time Password
     */
    public void enterOtp(String otp) {
        type(LoginLocators.OTP_INPUT, otp);
        logger.info("✓ OTP entered");
    }

    /**
     * Complete login flow with mobile and password
     *
     * @param mobile Mobile number
     * @param password Password
     */
    public void login(String mobile, String password) {
        logger.info("\n--- Starting Login Flow ---");

        navigateToLoginPage();
        clickLoginSignUpButton();
        clickPasswordTab();
        enterMobile(mobile);
        enterPassword(password);
        clickLoginButton();

        logger.info("✓ Login completed");
        if (AssertUtils.assertUrlEquals(driver, ConfigConstants.DASHBOARD_URL)) {
            logger.info("✓ Test Passed: Valid login successful");
        } else {
            logger.info("✗ Test Failed: Login did not navigate to dashboard");
        }

        AssertUtils.assertTextEquals(
                driver,
                LoginLocators.LOGIN_SUCCESS_TOAST,
                "You have successfully logged in"
        );
    }

    /**
     * Verify login was successful by checking if success message is visible
     *
     * @return true if login was successful, false otherwise
     */
    public boolean verifyLoginSuccess() {
        return isVisible(LoginLocators.SUCCESS_MESSAGE);
    }

    /**
     * Verify error message is displayed
     *
     * @return true if error message is visible, false otherwise
     */
    public boolean verifyErrorMessage() {
        return isVisible(LoginLocators.ERROR_MESSAGE);
    }

    /**
     * Get error message text
     *
     * @return Error message text
     */
    public String getErrorMessage() {
        return getText(LoginLocators.ERROR_MESSAGE);
    }

}
