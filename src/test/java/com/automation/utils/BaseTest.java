package com.automation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * Base test class containing common setup and teardown logic
 * All test classes should extend this class
 */
public class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

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

        logger.info("\n===== Test Setup Complete =====");
    }

    /**
     * Teardown method - runs after each test
     * Closes the browser and cleans up resources
     */
    @AfterMethod
    public void tearDown() {
        logger.info("===== Test Teardown Started =====\n");
        DriverManager.quitDriver();
    }

}
