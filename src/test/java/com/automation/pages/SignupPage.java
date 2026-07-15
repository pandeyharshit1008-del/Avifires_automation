package com.automation.pages;

import com.automation.locators.SignupLocators;
import com.automation.locators.LoginLocators;
import com.automation.Helpers.HelperFunctions;
import com.automation.utils.ConfigConstants;
import com.automation.utils.UserCredentials;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.file.Paths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Signup page object
 * Contains all signup-related actions and workflows
 */
public class SignupPage extends HelperFunctions {

    private static final Logger logger = LoggerFactory.getLogger(SignupPage.class);


    /**
     * Constructor to initialize driver and wait
     *
     * @param driver WebDriver instance
     */
    public SignupPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navigate to signup page
     */
    public void navigateToSignupPage() {
        navigateTo(ConfigConstants.SIGNUP_PAGE_URL);
        logger.info("✓ Navigated to signup page");
    }

    /**
     * Enter mobile in mobile input field
     *
     * @param mobile Mobile number
     */
    public void enterMobile(String mobile) {
        type(SignupLocators.MOBILE_INPUT, mobile);
        logger.info("✓ Mobile entered");
    }

    /**
     * Enter password in password input field
     *
     * @param password Password
     */
    public void enterPassword(String password) {
        type(SignupLocators.PASSWORD_INPUT, password);
        logger.info("✓ Password entered");
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
     * Click continue button to submit login form
     */
    public void clickLoginButton() {
        click(LoginLocators.LOGIN_BUTTON);
        logger.info("✓ Continue button clicked");
    }

    /**
     * Sign up with user object
     *
     * @param user User credentials
     */
    public void signUpWithPassword(UserCredentials user) {
        logger.info("\n--- Starting Signup Flow ---");
        navigateToSignupPage();
        clickLoginSignUpButton();
        clickPasswordTab();
        enterMobile(user.getMobile());
        enterPassword(user.getPassword());
        clickLoginButton();
        logger.info("✓ OTP Sent to mobile: " + user.getMobile());
    }

    public void enterSignupFormDetails(UserCredentials user) {
        enterSignupName(user.getFirstName());
        selectDOB("15-10-1980");
        selectGender("Male");
        selectMaritalStatus("Single");
        verifyPANCard("GZOPG1234A");
        selectOccupation("Medical");
        selectAnnualIncome("100000-500000");
        selectLockInPeriodAccepted("Yes");
        uploadProfilePhoto("image.png");
        driver.findElement(SignupLocators.SIGNUP_FORM_SUBMIT_BUTTON).click();
        logger.info("✓ Signup form submitted");
    }

    public void uploadProfilePhoto(String fileName) {
        String filePath = Paths.get(
                "testdata",
                "documents",
                fileName
        ).toAbsolutePath().toString();

        WebElement uploadInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        SignupLocators.PROFILE_PHOTO_INPUT));
        uploadInput.sendKeys(filePath);

        logger.info("✓ Photo uploaded");
    }

    public void selectLockInPeriodAccepted(String option) {
        By radio = By.xpath(
            "//label[.//span[normalize-space()='" + option + "']]"
        );
        wait.until(ExpectedConditions.elementToBeClickable(radio))
                .click();

        logger.info("✓ Lock-in Period Accepted: " + option);
    }

    public void selectAnnualIncome(String annualIncome) {
        wait.until(ExpectedConditions.elementToBeClickable(
                SignupLocators.ANNUAL_INCOME_DROPDOWN))
                .click();

        By option = By.xpath("//li[@role='option' and normalize-space()='" + annualIncome + "']");
        wait.until(ExpectedConditions.elementToBeClickable(option))
                .click();

        logger.info("✓ Annual Income selected: " + annualIncome);
    }

    public void selectOccupation(String occupation) {
        wait.until(ExpectedConditions.elementToBeClickable(
                SignupLocators.OCCUPATION_DROPDOWN))
                .click();

        By option = By.xpath("//li[@role='option' and normalize-space()='" + occupation + "']");
        wait.until(ExpectedConditions.elementToBeClickable(option))
                .click();

        logger.info("✓ Occupation selected: " + occupation);
    }

    public void selectMaritalStatus(String status) {
        wait.until(ExpectedConditions.elementToBeClickable(
                SignupLocators.MARITAL_STATUS_DROPDOWN))
                .click();

        By option = By.xpath("//li[@role='option' and normalize-space()='" + status + "']");
        wait.until(ExpectedConditions.elementToBeClickable(option))
                .click();

        logger.info("✓ Marital Status selected: " + status);
    }

    public void selectGender(String gender) {
        wait.until(ExpectedConditions.elementToBeClickable(SignupLocators.GENDER_DROPDOWN))
                .click();

        By option = By.xpath("//li[@role='option' and normalize-space()='" + gender + "']");
        wait.until(ExpectedConditions.elementToBeClickable(option))
                .click();

        logger.info("✓ Gender selected: " + gender);
    }

    public void selectDOB(String date) {
    WebElement dob = driver.findElement(By.name("dob"));
    dob.clear();
    dob.sendKeys(date);   // yyyy-MM-dd
    }

    public void verifyPANCard(String pan) {
        WebElement panInput = driver.findElement(SignupLocators.SIGNUP_FORM_PAN_CARD);
        panInput.clear();
        panInput.sendKeys(pan);
        logger.info("✓ PAN Card entered: " + pan);
        driver.findElement(SignupLocators.SIGNUP_FORM_VERIFY_PAN_CARD_BUTTON).click();
        logger.info("✓ PAN Card verified: " + pan);
    }

    public void enterSignupName(String name) {
        type(SignupLocators.SIGNUP_FORM_NAME, name);
        logger.info("✓ Name entered");
    }

    /**
     * Enter OTP in password OTP field
     *
     * @param otp One-Time Password
     */
    public void enterPasswordOtp(String otp) {
        type(SignupLocators.PASSWORD_OTP_INPUT, otp);
        logger.info("✓ Password OTP entered");
    }

    /**
     * Click verify OTP button
     */
    public void clickVerifyOtpButton() {
        click(SignupLocators.VERIFY_OTP_BUTTON);
        logger.info("✓ Verify OTP button clicked");
    }


    /**
     * Verify signup was successful
     *
     * @return true if signup success message is visible, false otherwise
     */
    public boolean verifySignupSuccess() {
        return isVisible(SignupLocators.SIGNUP_FORM_HEADING);
    }

    /**
     * Verify error message is displayed
     *
     * @return true if error message is visible, false otherwise
     */
    public boolean verifyErrorMessage() {
        return isVisible(SignupLocators.ERROR_MESSAGE);
    }

    /**
     * Get error message text
     *
     * @return Error message text
     */
    public String getErrorMessage() {
        return getText(SignupLocators.ERROR_MESSAGE);
    }
}
