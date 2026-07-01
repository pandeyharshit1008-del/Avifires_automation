package com.automation.pages;

import com.automation.locators.SignupLocators;
import com.automation.locators.LoginLocators;
import com.automation.utils.AssertUtils;
import com.automation.utils.ConfigConstants;
import com.automation.utils.TestDataReader;
import com.automation.utils.UserCredentials;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.Map;

/**
 * Signup page object
 * Contains all signup-related actions and workflows
 */
public class SignupPage {

    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * Constructor to initialize driver and wait
     *
     * @param driver WebDriver instance
     */
    public SignupPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigConstants.WAIT_TIMEOUT_SECONDS));
    }

    /**
     * Navigate to signup page
     */
    public void navigateToSignupPage() {
        driver.navigate().to(ConfigConstants.SIGNUP_PAGE_URL);
        System.out.println("✓ Navigated to signup page");
        // AssertUtils.assertVisible(driver, SignupLocators.SIGNUP_FORM);
    }

    /**
     * Enter email in email input field
     *
     * @param email Email address
     */
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(SignupLocators.EMAIL_INPUT));
        driver.findElement(SignupLocators.EMAIL_INPUT).clear();
        driver.findElement(SignupLocators.EMAIL_INPUT).sendKeys(email);
        System.out.println("✓ Email entered");
    }

    /**
     * Enter first name
     *
     * @param firstName First name
     */
    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(SignupLocators.FIRST_NAME_INPUT));
        driver.findElement(SignupLocators.FIRST_NAME_INPUT).clear();
        driver.findElement(SignupLocators.FIRST_NAME_INPUT).sendKeys(firstName);
        System.out.println("✓ First name entered");
    }

    /**
     * Enter last name
     *
     * @param lastName Last name
     */
    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(SignupLocators.LAST_NAME_INPUT));
        driver.findElement(SignupLocators.LAST_NAME_INPUT).clear();
        driver.findElement(SignupLocators.LAST_NAME_INPUT).sendKeys(lastName);
        System.out.println("✓ Last name entered");
    }

    /**
     * Enter mobile in mobile input field
     *
     * @param mobile Mobile number
     */
    public void enterMobile(String mobile) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(SignupLocators.MOBILE_INPUT));
        driver.findElement(SignupLocators.MOBILE_INPUT).clear();
        driver.findElement(SignupLocators.MOBILE_INPUT).sendKeys(mobile);
        System.out.println("✓ Mobile entered");
    }

    /**
     * Enter password in password input field
     *
     * @param password Password
     */
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(SignupLocators.PASSWORD_INPUT));
        driver.findElement(SignupLocators.PASSWORD_INPUT).clear();
        driver.findElement(SignupLocators.PASSWORD_INPUT).sendKeys(password);
        System.out.println("✓ Password entered");
    }

    /**
     * Enter confirm password
     *
     * @param confirmPassword Confirm password
     */
    public void enterConfirmPassword(String confirmPassword) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(SignupLocators.CONFIRM_PASSWORD_INPUT));
        driver.findElement(SignupLocators.CONFIRM_PASSWORD_INPUT).clear();
        driver.findElement(SignupLocators.CONFIRM_PASSWORD_INPUT).sendKeys(confirmPassword);
        System.out.println("✓ Confirm password entered");
    }

    /**
     * Check terms and conditions checkbox
     */
    public void checkTermsCheckbox() {
        wait.until(ExpectedConditions.elementToBeClickable(SignupLocators.TERMS_CHECKBOX));
        if (!driver.findElement(SignupLocators.TERMS_CHECKBOX).isSelected()) {
            driver.findElement(SignupLocators.TERMS_CHECKBOX).click();
            System.out.println("✓ Terms and conditions checked");
        }
    }

    /**
     * Click signup button
     */
    public void clickSignupButton() {
        AssertUtils.assertClickable(driver, SignupLocators.SIGNUP_BUTTON);
        driver.findElement(SignupLocators.SIGNUP_BUTTON).click();
        System.out.println("✓ Signup button clicked");
    }

     /**
     * Click the initial Log In / Sign Up entry button
     */
    public void clickLoginSignUpButton() {
        AssertUtils.assertClickable(driver, LoginLocators.LOGIN_SIGNUP_BUTTON);
        driver.findElement(LoginLocators.LOGIN_SIGNUP_BUTTON).click();
        System.out.println("✓ Log In / Sign Up clicked");
    }

    /**
     * Click the Password login tab
     */
    public void clickPasswordTab() {
        AssertUtils.assertClickable(driver, LoginLocators.PASSWORD_TAB);
        driver.findElement(LoginLocators.PASSWORD_TAB).click();
        System.out.println("✓ Password tab selected");
    }

    /**
     * Click continue button to submit login form
     */
    public void clickLoginButton() {
        AssertUtils.assertClickable(driver, LoginLocators.LOGIN_BUTTON);
        driver.findElement(LoginLocators.LOGIN_BUTTON).click();
        System.out.println("✓ Continue button clicked");
    }

    /**
     * Complete signup flow using test data from JSON
     *
     * @param dataKey Data key from JSON (e.g., "validSignup")
     */
    public void signupWithTestData(String dataKey) {
        System.out.println("\n--- Starting Signup Flow with Test Data ---");
        Map<String, String> signupData = TestDataReader.getSignupData(dataKey);

        if (signupData.isEmpty()) {
            System.out.println("✗ Failed to load signup data for: " + dataKey);
            return;
        }

        String email = signupData.getOrDefault("email", "");
        String firstName = signupData.getOrDefault("firstName", "");
        String lastName = signupData.getOrDefault("lastName", "");
        String mobile = signupData.getOrDefault("mobile", "");
        String password = signupData.getOrDefault("password", "");
        String confirmPassword = signupData.getOrDefault("confirmPassword", "");

        // signup(email, firstName, lastName, mobile, password, confirmPassword);
    }

    /**
     * Sign up with user object
     *
     * @param user User credentials
     */
    public void signUpWithPassword(UserCredentials user) {
        System.out.println("\n--- Starting Signup Flow ---");
        navigateToSignupPage();
        clickLoginSignUpButton();
        clickPasswordTab();
        enterMobile(user.getMobile());
        enterPassword(user.getPassword());
        clickLoginButton();
        System.out.println("✓ OTP Sent to mobile: " + user.getMobile());
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
        System.out.println("✓ Signup form submitted");
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

        System.out.println("✓ Photo uploaded");
    }

    public void selectLockInPeriodAccepted(String option) {

        By radio = By.xpath(
            "//label[.//span[normalize-space()='" + option + "']]"
        );

        wait.until(ExpectedConditions.elementToBeClickable(radio))
                .click();

        System.out.println("✓ Lock-in Period Accepted: " + option);
    }

    public void selectAnnualIncome(String annualIncome) {

        wait.until(ExpectedConditions.elementToBeClickable(
                SignupLocators.ANNUAL_INCOME_DROPDOWN))
                .click();

        By option = By.xpath("//li[@role='option' and normalize-space()='" + annualIncome + "']");

        wait.until(ExpectedConditions.elementToBeClickable(option))
                .click();

        System.out.println("✓ Annual Income selected: " + annualIncome);
    }

    public void selectOccupation(String occupation) {

        wait.until(ExpectedConditions.elementToBeClickable(
                SignupLocators.OCCUPATION_DROPDOWN))
                .click();

        By option = By.xpath("//li[@role='option' and normalize-space()='" + occupation + "']");

        wait.until(ExpectedConditions.elementToBeClickable(option))
                .click();

        System.out.println("✓ Occupation selected: " + occupation);
    }

    public void selectMaritalStatus(String status) {

        wait.until(ExpectedConditions.elementToBeClickable(
                SignupLocators.MARITAL_STATUS_DROPDOWN))
                .click();

        By option = By.xpath("//li[@role='option' and normalize-space()='" + status + "']");

        wait.until(ExpectedConditions.elementToBeClickable(option))
                .click();

        System.out.println("✓ Marital Status selected: " + status);
    }

    public void selectGender(String gender) {

        wait.until(ExpectedConditions.elementToBeClickable(SignupLocators.GENDER_DROPDOWN))
                .click();

        By option = By.xpath("//li[@role='option' and normalize-space()='" + gender + "']");

        wait.until(ExpectedConditions.elementToBeClickable(option))
                .click();

        System.out.println("✓ Gender selected: " + gender);
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
        System.out.println("✓ PAN Card entered: " + pan);
        driver.findElement(SignupLocators.SIGNUP_FORM_VERIFY_PAN_CARD_BUTTON).click();
        System.out.println("✓ PAN Card verified: " + pan);
    }

    public void enterSignupName(String Name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(SignupLocators.SIGNUP_FORM_NAME));
        driver.findElement(SignupLocators.SIGNUP_FORM_NAME).clear();
        driver.findElement(SignupLocators.SIGNUP_FORM_NAME).sendKeys(Name);
        System.out.println("✓ Name entered");
    }

    /**
     * Enter OTP in password OTP field
     *
     * @param otp One-Time Password
     */
    public void enterPasswordOtp(String otp) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(SignupLocators.PASSWORD_OTP_INPUT));
        driver.findElement(SignupLocators.PASSWORD_OTP_INPUT).clear();
        driver.findElement(SignupLocators.PASSWORD_OTP_INPUT).sendKeys(otp);
        System.out.println("✓ Password OTP entered: " + otp);
    }

    /**
     * Click verify OTP button
     */
    public void clickVerifyOtpButton() {
        AssertUtils.assertClickable(driver, SignupLocators.VERIFY_OTP_BUTTON);
        driver.findElement(SignupLocators.VERIFY_OTP_BUTTON).click();
        System.out.println("✓ Verify OTP button clicked");
    }


    /**
     * Verify signup was successful
     *
     * @return true if signup success message is visible, false otherwise
     */
    public boolean verifySignupSuccess() {
        return AssertUtils.assertVisible(driver, SignupLocators.SIGNUP_FORM_HEADING);
    }

    /**
     * Verify error message is displayed
     *
     * @return true if error message is visible, false otherwise
     */
    public boolean verifyErrorMessage() {
        return AssertUtils.assertVisible(driver, SignupLocators.ERROR_MESSAGE);
    }

    /**
     * Get error message text
     *
     * @return Error message text
     */
    public String getErrorMessage() {
        try {
            return driver.findElement(SignupLocators.ERROR_MESSAGE).getText();
        } catch (Exception e) {
            return "";
        }
    }
}
