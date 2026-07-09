package com.automation.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Manages WebDriver instance creation and cleanup
 */
public class DriverManager {

    private static WebDriver driver;

    /**
     * Initialize ChromeDriver
     *
     */
    public static WebDriver initializeDriver() {
        if (driver == null) {
            // Setup Chrome driver using WebDriverManager
            WebDriverManager.chromedriver().setup();

            // Configure Chrome options
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
//             Uncomment to run in any mode
//            options.addArguments("--headless");
            options.addArguments("--headed");

            // Initialize ChromeDriver
            driver = new ChromeDriver(options);

            // Maximize browser window
            driver.manage().window().maximize();

            System.out.println("✓ Browser launched successfully");
        }
        return driver;
    }

    /**
     * Get current WebDriver instance
     *
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    /**
     * Quit WebDriver and close browser
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("✓ Browser closed successfully");
        }
    }

    /**
     * Close current browser window
     */
    public static void closeDriver() {
        if (driver != null) {
            driver.close();
            System.out.println("✓ Browser window closed");
        }
    }

}
