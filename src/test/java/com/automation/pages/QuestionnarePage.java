package com.automation.pages;

import com.automation.locators.QuestionnareLocators;
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
import org.openqa.selenium.JavascriptExecutor;

/**
 * Login page object
 * Contains all login-related actions and workflows
 */
public class QuestionnarePage {

    private static final Logger logger = LoggerFactory.getLogger(QuestionnarePage.class);

    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * Constructor to initialize driver and wait
     *
     * @param driver WebDriver instance
     */
    public QuestionnarePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigConstants.WAIT_TIMEOUT_SECONDS));
    }

    public void selectInvestmentGoal(String goal) {
        By locator = By.xpath(String.format(QuestionnareLocators.INVESTMENT_GOAL_OPTION, goal));

        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );

        element.click();
        logger.info("✓ Selected investment goal: " + goal);
    }

    public void setCorpusNeeded(int amount) {
        WebElement corpusInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(QuestionnareLocators.CORPUS_NEEDED_INPUT)
        );

        corpusInput.clear();
        corpusInput.sendKeys(String.valueOf(amount));
        logger.info("✓ Set corpus needed to: " + amount);
    }

    public void setTimeOfInvestment(int years) {
        WebElement timeInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(QuestionnareLocators.TIME_OF_INVESTMENT)
        );

        timeInput.clear();
        timeInput.sendKeys(String.valueOf(years));
        logger.info("✓ Set time of investment to: " + years);
    }

    public void submitInvestmentPlan() {
        AssertUtils.assertClickable(driver, QuestionnareLocators.INVESTMENT_GOAL_SUBMIT_BUTTON);
        driver.findElement(QuestionnareLocators.INVESTMENT_GOAL_SUBMIT_BUTTON).click();
        logger.info("✓ Investment plan submitted");
    }

    public void selectPreferencesAndPriorities() {
        AssertUtils.assertClickable(driver, QuestionnareLocators.PREFERENCES_AND_PRIORITIES);
        driver.findElement(QuestionnareLocators.PREFERENCES_AND_PRIORITIES).click();
        logger.info("✓ Preferences and Priorities selected");
    }

    public void answerQuestionnaireQuestions(String optionText) {
        By locator = By.xpath(String.format(QuestionnareLocators.RADIO_OPTION_BY_TEXT, optionText));

        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );
        scrollToElement(locator);

        element.click();
        logger.info("✓ Selected radio option: " + optionText);
    }

    public void scrollToElement(By locator) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element);
    }

    public void moveToNextQuestion() {
        AssertUtils.assertClickable(driver, QuestionnareLocators.NEXT_BUTTON);
        driver.findElement(QuestionnareLocators.NEXT_BUTTON).click();
        logger.info("Moved to next question");
    }

    public void submitPreferencesAndPriorities() {
        AssertUtils.assertClickable(driver, QuestionnareLocators.PREFERENCES_AND_PRIORITIES_SUBMIT_BUTTON);
        driver.findElement(QuestionnareLocators.PREFERENCES_AND_PRIORITIES_SUBMIT_BUTTON).click();
        logger.info("✓ Preferences and Priorities submitted");
    }
}

