package com.automation.test.Home;


import com.automation.base.BaseTest;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.utils.TestDataReader;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Test class for Login functionality
 */
public class HomeTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(com.automation.test.Home.HomeTest.class);

    /**
     * Test the functionalities of financial return calculator
     */
    @Test
    public void testFinancialReturnCalculator() {
        logger.info("\n========== TEST: Financial Return Calculator ==========");

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        Map<String, String> userData = TestDataReader.getUser("validUser");


        String mobile = userData.get("mobile");
        String password = userData.get("password");

        loginPage.login(mobile, password);
        loginPage.moveToHomePage();

        homePage.setInvestmentAmount(500000);
        homePage.setExpectedReturn(15);
        homePage.setTimePeriod(10);
        homePage.getSIPValue();
    }
}

