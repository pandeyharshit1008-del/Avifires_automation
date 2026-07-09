package com.automation.test.Home;

import com.automation.utils.BaseTest;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.utils.TestDataReader;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class HomeTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(com.automation.test.Home.HomeTest.class);

    @Test
    public void testFinancialReturnCalculator() {
        logger.info("\n========== TEST: Financial Return Calculator ==========");

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        Map<String, String> userData = TestDataReader.getUser("validUser");
        loginPage.login(userData.get("mobile"), userData.get("password"));

        loginPage.openSidebar();
        loginPage.moveToHomePage();

        homePage.setInvestmentAmount(500000);
        homePage.setExpectedReturn(15);
        homePage.setTimePeriod(10);
        homePage.getSIPValue();
    }
}

