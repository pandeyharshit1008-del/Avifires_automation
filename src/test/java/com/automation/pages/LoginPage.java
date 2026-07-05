package com.automation.pages;

import com.automation.locators.LoginLocators;
import com.automation.utils.AssertUtils;
import com.automation.utils.ConfigConstants;
import com.automation.utils.TestDataReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import java.util.Map;

/**
 * Login page object
 * Contains all login-related actions and workflows
 */
public class LoginPage {

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * Constructor to initialize driver and wait
     *
     * @param driver WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigConstants.WAIT_TIMEOUT_SECONDS));
    }

    /**
     * Navigate to login page
     */
    public void navigateToLoginPage() {
        driver.navigate().to(ConfigConstants.LOGIN_PAGE_URL);
        logger.info("✓ Navigated to login page");
        AssertUtils.assertVisible(driver, LoginLocators.LOGIN_SIGNUP_BUTTON);
    }

    public void moveToHomePage() {
        AssertUtils.assertVisible(driver, LoginLocators.OPEN_SIDEBAR_BUTTON);
        driver.findElement(LoginLocators.OPEN_SIDEBAR_BUTTON).click();
        AssertUtils.assertClickable(driver, LoginLocators.MOVE_TO_HOME_PAGE_BUTTON);
        driver.findElement(LoginLocators.MOVE_TO_HOME_PAGE_BUTTON).click();
        logger.info("✓ Navigated to home page");
    }

    public void moveToQuestionnairePage() {
        AssertUtils.assertClickable(driver, LoginLocators.QUESTIONNAIRE_PAGE_BUTTON);
        driver.findElement(LoginLocators.QUESTIONNAIRE_PAGE_BUTTON).click();
        logger.info("✓ Navigated to questionnaire page");
    }

    public void moveToPortfolioPage() {
        AssertUtils.assertClickable(driver, LoginLocators.PORTFOLIO_PAGE_BUTTON);
        driver.findElement(LoginLocators.PORTFOLIO_PAGE_BUTTON).click();
        logger.info("Navigated to My Profile Page");
    }

    /**
     * Open the sidebar menu
     */
    public void openSidebar() {
        AssertUtils.assertVisible(driver, LoginLocators.OPEN_SIDEBAR_BUTTON);
        driver.findElement(LoginLocators.OPEN_SIDEBAR_BUTTON).click();
        logger.info("✓ Sidebar opened");
    }

    /**
     * Click the initial Log In / Sign Up entry button
     */
    public void clickLoginSignUpButton() {
        AssertUtils.assertClickable(driver, LoginLocators.LOGIN_SIGNUP_BUTTON);
        driver.findElement(LoginLocators.LOGIN_SIGNUP_BUTTON).click();
        logger.info("✓ Log In / Sign Up clicked");
    }

    /**
     * Click the Password login tab
     */
    public void clickPasswordTab() {
        AssertUtils.assertClickable(driver, LoginLocators.PASSWORD_TAB);
        driver.findElement(LoginLocators.PASSWORD_TAB).click();
        logger.info("✓ Password tab selected");
    }

    /**
     * Enter mobile number in mobile input field
     *
     * @param mobile Mobile number
     */
    public void enterMobile(String mobile) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginLocators.MOBILE_INPUT));
        driver.findElement(LoginLocators.MOBILE_INPUT).clear();
        driver.findElement(LoginLocators.MOBILE_INPUT).sendKeys(mobile);
        logger.info("✓ Mobile entered");
    }

    /**
     * Enter password in password input field
     *
     * @param password Password
     */
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginLocators.PASSWORD_INPUT));
        driver.findElement(LoginLocators.PASSWORD_INPUT).clear();
        driver.findElement(LoginLocators.PASSWORD_INPUT).sendKeys(password);
        logger.info("✓ Password entered");
    }

    /**
     * Click continue button to submit login form
     */
    public void clickLoginButton() {
        AssertUtils.assertClickable(driver, LoginLocators.LOGIN_BUTTON);
        driver.findElement(LoginLocators.LOGIN_BUTTON).click();
        logger.info("✓ Continue button clicked");
    }

    /**
     * Click support button to submit login form
     */
    public void clickSupportButton() {
        AssertUtils.assertClickable(driver, LoginLocators.SUPPORT);
        driver.findElement(LoginLocators.SUPPORT).click();
        logger.info("✓ Support button clicked");
    }

    /**
     * Click request OTP button
     */
    public void clickRequestOtpButton() {
        AssertUtils.assertClickable(driver, LoginLocators.REQUEST_OTP_BUTTON);
        driver.findElement(LoginLocators.REQUEST_OTP_BUTTON).click();
        logger.info("✓ Request OTP button clicked");
    }

    /**
     * Enter OTP in OTP input field
     *
     * @param otp One-Time Password
     */
    public void enterOtp(String otp) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginLocators.OTP_INPUT));
        driver.findElement(LoginLocators.OTP_INPUT).clear();
        driver.findElement(LoginLocators.OTP_INPUT).sendKeys(otp);
        logger.info("✓ OTP entered: " + otp);
    }

    /**
     * Enter OTP in password OTP field
     *
     * @param otp One-Time Password
     */
    public void enterPasswordOtp(String otp) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginLocators.PASSWORD_OTP_INPUT));
        driver.findElement(LoginLocators.PASSWORD_OTP_INPUT).clear();
        driver.findElement(LoginLocators.PASSWORD_OTP_INPUT).sendKeys(otp);
        logger.info("✓ Password OTP entered: " + otp);
    }

    /**
     * Click verify OTP button
     */
    public void clickVerifyOtpButton() {
        AssertUtils.assertClickable(driver, LoginLocators.VERIFY_OTP_BUTTON);
        driver.findElement(LoginLocators.VERIFY_OTP_BUTTON).click();
        logger.info("✓ Verify OTP button clicked");
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
    }

    /**
     * Complete login flow using test data from JSON
     *
     * @param userType User type from JSON (e.g., "validUser", "invalidUser")
     */
    public void loginWithTestData(String userType) {
        logger.info("\n--- Starting Login Flow with Test Data ---");
        Map<String, String> userData = TestDataReader.getUser(userType);
        
        if (userData.isEmpty()) {
            logger.info("✗ Failed to load user data for: " + userType);
            return;
        }

        String mobile = userData.get("mobile");
        String password = userData.get("password");

        login(mobile, password);
    }

    /**
     * Complete OTP login flow
     *
     * @param mobile Mobile number
     * @param otp OTP code
     */
    public void loginWithOtp(String mobile, String otp) {
        logger.info("\n--- Starting OTP Login Flow ---");
        navigateToLoginPage();
        enterMobile(mobile);
        clickRequestOtpButton();
        enterOtp(otp);
        clickVerifyOtpButton();
        logger.info("✓ OTP login completed");
    }

    /**
     * Verify login was successful by checking if success message is visible
     *
     * @return true if login was successful, false otherwise
     */
    public boolean verifyLoginSuccess() {
        return AssertUtils.assertVisible(driver, LoginLocators.SUCCESS_MESSAGE);
    }

    /**
     * Verify error message is displayed
     *
     * @return true if error message is visible, false otherwise
     */
    public boolean verifyErrorMessage() {
        return AssertUtils.assertVisible(driver, LoginLocators.ERROR_MESSAGE);
    }

    /**
     * Get error message text
     *
     * @return Error message text
     */
    public String getErrorMessage() {
        try {
            return driver.findElement(LoginLocators.ERROR_MESSAGE).getText();
        } catch (Exception e) {
            return "";
        }
    }

}
