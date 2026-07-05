package com.automation.pages;

import com.automation.locators.LoginLocators;
import com.automation.locators.PortfolioLocators;
import com.automation.utils.AssertUtils;
import com.automation.utils.ConfigConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.JavascriptExecutor;

import static com.automation.locators.PortfolioLocators.*;

public class PortfolioPage {

    private static final Logger logger = LoggerFactory.getLogger(PortfolioPage.class);

    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * Constructor to initialize driver and wait
     *
     * @param driver WebDriver instance
     */
    public PortfolioPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigConstants.WAIT_TIMEOUT_SECONDS));
    }

    public void scrollToElement(By locator) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element);
    }

    public void scrollBy(int pixels) {
        ((JavascriptExecutor) driver).executeScript(
                "window.scrollBy(0, arguments[0]);", pixels);
    }

    public void clickEditPortfolio() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        scrollBy(1000);          // Scroll down by 200 pixels

        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(PortfolioLocators.EDIT_PORTFOLIO_BUTTON)
        );
        scrollToElement(PortfolioLocators.EDIT_PORTFOLIO_BUTTON);

        element.click();
        logger.info("Element clicked");
    }

    public void selectInvestmentType(String option) {
        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//span[normalize-space()='Investment type']/following::select[1]")));

        dropdown.click();

        WebElement optionElement = wait.until(
                ExpectedConditions.elementToBeClickable(investmentTypeOption(option)));

        optionElement.click();
    }

    public void selectMutualFund(String fundName) {

        WebElement searchBox = wait.until(
                ExpectedConditions.elementToBeClickable(PortfolioLocators.MUTUAL_FUND_SEARCH_BOX));

        searchBox.clear();
        searchBox.sendKeys(fundName);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    public void selectFrequency(String frequency) {

        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(frequencyDropdown));

        Select select = new Select(dropdown);
        select.selectByVisibleText(frequency);
    }

    public void selectInvestmentDate(String date) {

        WebElement dateField = wait.until(
                ExpectedConditions.elementToBeClickable(
                        PortfolioLocators.INVESTMENT_DATE));

        dateField.clear();
        dateField.sendKeys(date);
    }

    public void enterAmount(int amount) {

        WebElement amountField = wait.until(
                ExpectedConditions.elementToBeClickable(
                        PortfolioLocators.AMOUNT_INPUT));

        amountField.clear();
        amountField.sendKeys(String.valueOf(amount));
    }

    public void moveToPortfolioPage() {
        AssertUtils.assertClickable(driver, ADD_RECORD_BUTTON);
        driver.findElement(ADD_RECORD_BUTTON).click();
        logger.info("Record added succesfully");
    }

}
