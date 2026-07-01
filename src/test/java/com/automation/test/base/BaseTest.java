package com.automation.base;

import com.automation.utils.ConfigConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

/**
 * Base test class containing common setup and teardown logic
 * All test classes should extend this class
 */
public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * Setup method - runs before each test
     * Initializes WebDriver and WebDriverWait
     */
    @BeforeMethod
    public void setUp() {
        driver = DriverManager.initializeDriver();

        // Initialize explicit wait (10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigConstants.WAIT_TIMEOUT_SECONDS));

        System.out.println("\n===== Test Setup Complete =====");
    }

    /**
     * Teardown method - runs after each test
     * Closes the browser and cleans up resources
     */
    @AfterMethod
    public void tearDown() {
        System.out.println("===== Test Teardown Started =====\n");
        DriverManager.quitDriver();
    }

    /**
     * Navigate to a specific URL
     *
     * @param url The URL to navigate to
     */
    protected void navigateTo(String url) {
        driver.navigate().to(url);
        System.out.println("✓ Navigated to: " + url);
    }

}
