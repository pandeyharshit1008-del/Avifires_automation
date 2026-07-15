package com.automation.pages;

import com.automation.locators.QuestionnareLocators;
import com.automation.utils.AssertUtils;
import com.automation.Helpers.HelperFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Questionnaire page object
 * Contains all questionnaire-related actions
 */
public class QuestionnarePage extends HelperFunctions {

    private static final Logger logger = LoggerFactory.getLogger(QuestionnarePage.class);

    /**
     * Constructor
     *
     * @param driver WebDriver instance
     */
    public QuestionnarePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Select investment goal
     */
    public void selectInvestmentGoal(String goal) {
        By locator = By.xpath(
                String.format(QuestionnareLocators.INVESTMENT_GOAL_OPTION, goal));
        click(locator);
        logger.info("✓ Selected investment goal: {}", goal);
    }

    /**
     * Enter corpus needed
     */
    public void setCorpusNeeded(int amount) {
        type(QuestionnareLocators.CORPUS_NEEDED_INPUT,
                String.valueOf(amount));
        logger.info("✓ Set corpus needed to: {}", amount);
    }

    /**
     * Enter investment duration
     */
    public void setTimeOfInvestment(int years) {
        type(QuestionnareLocators.TIME_OF_INVESTMENT,
                String.valueOf(years));
        logger.info("✓ Set time of investment to: {}", years);
    }

    /**
     * Submit investment plan
     */
    public void submitInvestmentPlan() {
        click(QuestionnareLocators.INVESTMENT_GOAL_SUBMIT_BUTTON);
        logger.info("✓ Investment plan submitted");

        AssertUtils.assertTextEquals(
                driver,
                QuestionnareLocators.INVESTMENT_SUCCESS_TOAST,
                "Investment plans and goal projection saved successfully"
        );
    }

    /**
     * Open Preferences & Priorities questionnaire
     */
    public void selectPreferencesAndPriorities() {
        click(QuestionnareLocators.PREFERENCES_AND_PRIORITIES);
        logger.info("✓ Preferences and Priorities selected");
    }

    /**
     * Select questionnaire option
     */
    public void answerQuestionnaireQuestions(String optionText) {
        By locator = By.xpath(
                String.format(
                        QuestionnareLocators.RADIO_OPTION_BY_TEXT,
                        optionText));

        scrollToElement(locator);
        click(locator);
        logger.info("✓ Selected radio option: {}", optionText);
    }



    /**
     * Move to next question
     */
    public void moveToNextQuestion() {
        click(QuestionnareLocators.NEXT_BUTTON);
        logger.info("✓ Moved to next question");
    }

    /**
     * Submit Preferences & Priorities questionnaire
     */
    public void submitPreferencesAndPriorities() {
        click(QuestionnareLocators.PREFERENCES_AND_PRIORITIES_SUBMIT_BUTTON);
        logger.info("✓ Preferences and Priorities submitted");

        AssertUtils.assertTextEquals(
                driver,
                QuestionnareLocators.PREFERENCES_SUCCESS_TOAST,
                "Investment preferences submitted successfully"
        );
    }
}