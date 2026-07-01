package com.automation.login;

import com.automation.base.BaseTest;
import com.automation.pages.LoginPage;
import com.automation.utils.AssertUtils;
import com.automation.utils.ConfigConstants;
import com.automation.utils.OTPInterceptor;
import com.automation.utils.TestDataReader;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Test class for Login functionality
 */
public class LoginTest extends BaseTest {

    /**
     * Test valid login with mobile and password
     */
    @Test
    public void testValidLogin() {
        System.out.println("\n========== TEST: Valid Login ==========");
        
        LoginPage loginPage = new LoginPage(driver);

        // Load encrypted credentials from test data and decrypt at runtime
        Map<String, String> userData = TestDataReader.getUser("validUser");
        if (userData.isEmpty()) {
            System.out.println("✗ Test data not available for validUser");
            return;
        }

        String mobile = userData.get("mobile");
        String password = userData.get("password");

        // Execute login with decrypted credentials
        loginPage.login(mobile, password);

        // Verify login success with exact dashboard URL
        if (AssertUtils.assertUrlEquals(driver, ConfigConstants.DASHBOARD_URL)) {
            System.out.println("✓ Test Passed: Valid login successful");
        } else {
            System.out.println("✗ Test Failed: Login did not navigate to dashboard");
        }
        loginPage.clickSupportButton(); // Click support button after login
    }

    /**
     * Test the functionalities of financial return calculator
     */
    @Test
    public void testFinancialReturnCalculator() {
        System.out.println("\n========== TEST: Financial Return Calculator ==========");
        // Implementation for financial return calculator tests

        LoginPage loginPage = new LoginPage(driver);

        // Load encrypted credentials from test data and decrypt at runtime
        Map<String, String> userData = TestDataReader.getUser("validUser");
        if (userData.isEmpty()) {
            System.out.println("✗ Test data not available for validUser");
            return;
        }

        String mobile = userData.get("mobile");
        String password = userData.get("password");

        // Execute login with decrypted credentials
        loginPage.login(mobile, password);

        // Verify login success with exact dashboard URL
        if (AssertUtils.assertUrlEquals(driver, ConfigConstants.DASHBOARD_URL)) {
            System.out.println("✓ Test Passed: Valid login successful");
        } else {
            System.out.println("✗ Test Failed: Login did not navigate to dashboard");
        }
        loginPage.moveToHomePage(); // Navigate to home page after login
        loginPage.setInvestmentAmount(500000); // Click the financial calculator slider
        loginPage.setExpectedReturn(15);// Set the expected return on the financial calculator slider
        loginPage.setTimePeriod(10); // Set the time period on the financial calculator slider

        loginPage.getSIPValue(); // Get the SIP value after setting the sliders

    }
    
}
