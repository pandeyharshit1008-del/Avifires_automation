import com.automation.base.BaseTest;
import com.automation.pages.LoginPage;
import com.automation.utils.AssertUtils;
import com.automation.utils.ConfigConstants;
import com.automation.utils.TestDataReader;
import org.testng.annotations.Test;

import java.util.Map;

public class QuestionnareTest extends BaseTest {


    @Test
    public void testInvestmentPlan() {
        System.out.println("\n========== TEST: Investment Plan ==========");
        // Implementation for investment plan tests

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
        loginPage.openSidebar(); // Open the sidebar
        loginPage.moveToQuestionnairePage(); // Select the investment plan from the sidebar
        loginPage.selectInvestmentGoal("Emergency Fund");
        loginPage.setCorpusNeeded(1000000); // Set the corpus needed to achieve the goal
        loginPage.setTimeOfInvestment(5); // Set the time of investment to achieve the goal
        loginPage.submitInvestmentPlan(); // Submit the investment plan
    }

    @Test
    public void testPreferencesAndPriorities() {
        System.out.println("\n========== TEST: Preferences and Priorities ==========");
        // Implementation for preferences and priorities tests

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
        loginPage.openSidebar(); // Open the sidebar
        loginPage.moveToQuestionnairePage(); // Select the preferences and priorities from the sidebar
        loginPage.selectPreferencesAndPriorities(); // Select preferences and priorities
        loginPage.answerQuestionnaireQuestions("I avoid risks at all costs"); // Answer the questionnaire questions
        loginPage.submitPreferencesAndPriorities(); // Submit preferences and priorities
    }
}