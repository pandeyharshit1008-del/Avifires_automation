package com.automation.login;

import com.automation.base.BaseTest;
import com.automation.pages.LoginPage;
import com.automation.utils.AssertUtils;
import com.automation.utils.ConfigConstants;
import com.automation.utils.OTPInterceptor;
import com.automation.utils.TestDataReader;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Test class for Login functionality
 */
public class LoginTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    /**
     * Test valid login with mobile and password
     */
    @Test
    public void testValidLogin() {
        logger.info("\n========== TEST: Valid Login ==========");
        
        LoginPage loginPage = new LoginPage(driver);

        // Load encrypted credentials from test data and decrypt at runtime
        Map<String, String> userData = TestDataReader.getUser("validUser");
        if (userData.isEmpty()) {
            logger.info("✗ Test data not available for validUser");
            return;
        }

        String mobile = userData.get("mobile");
        String password = userData.get("password");

        // Execute login with decrypted credentials
        loginPage.login(mobile, password);

        // Verify login success with exact dashboard URL
        if (AssertUtils.assertUrlEquals(driver, ConfigConstants.DASHBOARD_URL)) {
            logger.info("✓ Test Passed: Valid login successful");
        } else {
            logger.info("✗ Test Failed: Login did not navigate to dashboard");
        }
        loginPage.clickSupportButton(); // Click support button after login
    }

    /**
     * Test the functionalities of financial return calculator
     */
    @Test
    public void testFinancialReturnCalculator() {
        logger.info("\n========== TEST: Financial Return Calculator ==========");
        // Implementation for financial return calculator tests

        LoginPage loginPage = new LoginPage(driver);

        // Load encrypted credentials from test data and decrypt at runtime
        Map<String, String> userData = TestDataReader.getUser("validUser");
        if (userData.isEmpty()) {
            logger.info("✗ Test data not available for validUser");
            return;
        }

        String mobile = userData.get("mobile");
        String password = userData.get("password");

        // Execute login with decrypted credentials
        loginPage.login(mobile, password);

        // Verify login success with exact dashboard URL
        if (AssertUtils.assertUrlEquals(driver, ConfigConstants.DASHBOARD_URL)) {
            logger.info("✓ Test Passed: Valid login successful");
        } else {
            logger.info("✗ Test Failed: Login did not navigate to dashboard");
        }
        loginPage.moveToHomePage(); // Navigate to home page after login
        loginPage.setInvestmentAmount(500000); // Click the financial calculator slider
        loginPage.setExpectedReturn(15);// Set the expected return on the financial calculator slider
        loginPage.setTimePeriod(10); // Set the time period on the financial calculator slider

        loginPage.getSIPValue(); // Get the SIP value after setting the sliders

    }
    
}
