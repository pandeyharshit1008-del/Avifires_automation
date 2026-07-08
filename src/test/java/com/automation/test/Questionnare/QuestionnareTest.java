package com.automation.test.Questionnare;

import com.automation.base.BaseTest;
import com.automation.pages.LoginPage;
import com.automation.pages.QuestionnarePage;
import com.automation.utils.TestDataReader;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class QuestionnareTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(QuestionnareTest.class);

    @Test
    public void testInvestmentPlan() {
        LoginPage loginPage = new LoginPage(driver);
        QuestionnarePage questionnarePage = new QuestionnarePage(driver);

        Map<String, String> userData = TestDataReader.getUser("validUser");
        loginPage.login(userData.get("mobile"), userData.get("password"));

        loginPage.openSidebar();
        loginPage.moveToQuestionnairePage();

        questionnarePage.selectInvestmentGoal("Emergency Fund");
        questionnarePage.setCorpusNeeded(1000000);
        questionnarePage.setTimeOfInvestment(5);
        questionnarePage.submitInvestmentPlan();
    }

    @Test
    public void testPreferencesAndPriorities() {
        LoginPage loginPage = new LoginPage(driver);
        QuestionnarePage questionnarePage = new QuestionnarePage(driver);

        Map<String, String> userData = TestDataReader.getUser("validUser");
        loginPage.login(userData.get("mobile"), userData.get("password"));

        loginPage.openSidebar();
        loginPage.moveToQuestionnairePage();

        questionnarePage.selectPreferencesAndPriorities();
        questionnarePage.answerQuestionnaireQuestions("I avoid risks at all costs");
        questionnarePage.answerQuestionnaireQuestions("None, I cannot afford to lose money");
        questionnarePage.moveToNextQuestion();
        questionnarePage.moveToNextQuestion();
        questionnarePage.answerQuestionnaireQuestions("Moderate fluctuation");
        questionnarePage.moveToNextQuestion();
        questionnarePage.submitPreferencesAndPriorities();
    }
}
