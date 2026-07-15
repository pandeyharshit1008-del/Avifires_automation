package com.automation.test;

import com.automation.utils.BaseTest;
import com.automation.pages.LoginPage;
import com.automation.utils.TestDataReader;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class LoginTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    @Test
    public void testValidLogin() {
        logger.info("\n========== TEST: Valid Login ==========");
        
        LoginPage loginPage = new LoginPage(driver);

        Map<String, String> userData = TestDataReader.getUser("validUser");
        if (userData.isEmpty()) {
            logger.info("✗ Test data not available for validUser");
            return;
        }

        String mobile = userData.get("mobile");
        String password = userData.get("password");
        loginPage.login(mobile, password);
    }
}
