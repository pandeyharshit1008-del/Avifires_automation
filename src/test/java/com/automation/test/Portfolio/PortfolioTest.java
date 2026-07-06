package com.automation.test.Portfolio;

import com.automation.base.BaseTest;
import com.automation.pages.LoginPage;
import com.automation.pages.PortfolioPage;
import com.automation.test.Login.LoginTest;
import com.automation.utils.TestDataReader;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class PortfolioTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    /**
     * Add SIP Transaction
     */
    @Test
    public void testAddSIPTransaction() {
        LoginPage loginPage = new LoginPage(driver);
        PortfolioPage portfolioPage = new PortfolioPage(driver);

        Map<String, String> userData = TestDataReader.getUser("validUser");

        String mobile = userData.get("mobile");
        String password = userData.get("password");
        loginPage.login(mobile, password);


        loginPage.openSidebar();
        loginPage.moveToPortfolioPage();

        portfolioPage.clickEditPortfolio();
        portfolioPage.selectInvestmentType("SIP");
        portfolioPage.selectMutualFund("SBI Arbitrage Opportunities Fund(G)-Direct Plan");
        portfolioPage.selectFrequency("Daily");
        portfolioPage.selectInvestmentDate("03-07-2026");
        portfolioPage.enterAmount(12548);
        portfolioPage.clickAddRecord();

    }
}
