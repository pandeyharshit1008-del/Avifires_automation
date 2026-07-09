package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.automation.locators.PortfolioLocators.*;
import com.automation.utils.AssertUtils;

public class PortfolioPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(PortfolioPage.class);

    public PortfolioPage(WebDriver driver) {
        super(driver);
    }

    public void scrollBy(int pixels) {
        ((JavascriptExecutor) driver).executeScript(
                "window.scrollBy(0, arguments[0]);",
                pixels);
    }

    public void clickEditPortfolio() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        scrollBy(500);
        scrollToElement(EDIT_PORTFOLIO_BUTTON);
        AssertUtils.assertEnabled(driver , EDIT_PORTFOLIO_BUTTON);
        click(EDIT_PORTFOLIO_BUTTON);
        logger.info("✓ Edit Portfolio button clicked");
    }

    public void selectInvestmentType(String option) {
        click(By.xpath("//span[normalize-space()='Investment type']/following::select[1]"));
        click(investmentTypeOption(option));
        logger.info("✓ Investment Type selected: {}", option);
    }

    public void selectMutualFund(String fundName) {
        type(MUTUAL_FUND_SEARCH_BOX, fundName);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        logger.info("✓ Mutual Fund selected: {}", fundName);
    }

    public void selectFrequency(String frequency) {
        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(frequencyDropdown));

        Select select = new Select(dropdown);
        select.selectByVisibleText(frequency);
        logger.info("✓ Frequency selected: {}", frequency);
    }

    public void selectInvestmentDate(String date) {
        type(INVESTMENT_DATE, date);
        logger.info("✓ Investment Date selected: {}", date);
    }

    public void enterAmount(int amount) {
        type(AMOUNT_INPUT, String.valueOf(amount));
        logger.info("✓ Amount entered: {}", amount);
    }

    public void clickAddRecord() {
        click(ADD_RECORD_BUTTON);
        logger.info("✓ Record added successfully");
    }
}