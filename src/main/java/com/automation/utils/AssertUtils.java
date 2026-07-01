package com.automation.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Utility class for all reusable assertions in the framework
 * Provides methods to verify elements and conditions
 */
public class AssertUtils {

    private static final Logger logger = LoggerFactory.getLogger(AssertUtils.class);

    /**
     * Assert that an element is visible on the page
     *
     * @param driver WebDriver instance
     * @param locator By locator of the element
     * @return true if element is visible, false otherwise
     */
    public static boolean assertVisible(WebDriver driver, By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigConstants.WAIT_TIMEOUT_SECONDS));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.info("✓ Element is visible: " + locator);
            return true;
        } catch (Exception e) {
            logger.info("✗ Element is not visible: " + locator);
            return false;
        }
    }

    /**
     * Assert that an element exists in the DOM (may not be visible)
     *
     * @param driver WebDriver instance
     * @param locator By locator of the element
     * @return true if element exists, false otherwise
     */
    public static boolean assertExists(WebDriver driver, By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigConstants.WAIT_TIMEOUT_SECONDS));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            logger.info("✓ Element exists in DOM: " + locator);
            return true;
        } catch (Exception e) {
            logger.info("✗ Element does not exist in DOM: " + locator);
            return false;
        }
    }

    /**
     * Assert that an element is clickable
     *
     * @param driver WebDriver instance
     * @param locator By locator of the element
     * @return true if element is clickable, false otherwise
     */
    public static boolean assertClickable(WebDriver driver, By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigConstants.WAIT_TIMEOUT_SECONDS));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            logger.info("✓ Element is clickable: " + locator);
            return true;
        } catch (Exception e) {
            logger.info("✗ Element is not clickable: " + locator);
            return false;
        }
    }

    /**
     * Assert that an element is enabled
     *
     * @param driver WebDriver instance
     * @param locator By locator of the element
     * @return true if element is enabled, false otherwise
     */
    public static boolean assertEnabled(WebDriver driver, By locator) {
        try {
            WebElement element = driver.findElement(locator);
            boolean isEnabled = element.isEnabled();
            if (isEnabled) {
                logger.info("✓ Element is enabled: " + locator);
            } else {
                logger.info("✗ Element is not enabled: " + locator);
            }
            return isEnabled;
        } catch (Exception e) {
            logger.info("✗ Cannot determine if element is enabled: " + locator);
            return false;
        }
    }

    /**
     * Assert that an element's text equals expected text
     *
     * @param driver WebDriver instance
     * @param locator By locator of the element
     * @param expectedText Expected text
     * @return true if text matches, false otherwise
     */
    public static boolean assertTextEquals(WebDriver driver, By locator, String expectedText) {
        try {
            WebElement element = driver.findElement(locator);
            String actualText = element.getText();
            if (actualText.equals(expectedText)) {
                logger.info("✓ Text matches. Expected: " + expectedText + ", Actual: " + actualText);
                return true;
            } else {
                logger.info("✗ Text does not match. Expected: " + expectedText + ", Actual: " + actualText);
                return false;
            }
        } catch (Exception e) {
            logger.info("✗ Error comparing text: " + e.getMessage());
            return false;
        }
    }

    /**
     * Assert that an element's text contains expected text
     *
     * @param driver WebDriver instance
     * @param locator By locator of the element
     * @param expectedText Expected text to be contained
     * @return true if element text contains expected text, false otherwise
     */
    public static boolean assertContains(WebDriver driver, By locator, String expectedText) {
        try {
            WebElement element = driver.findElement(locator);
            String actualText = element.getText();
            if (actualText.contains(expectedText)) {
                logger.info("✓ Text contains: " + expectedText);
                return true;
            } else {
                logger.info("✗ Text does not contain: " + expectedText + ". Actual: " + actualText);
                return false;
            }
        } catch (Exception e) {
            logger.info("✗ Error checking text content: " + e.getMessage());
            return false;
        }
    }

    /**
     * Assert that the current URL contains expected text
     *
     * @param driver WebDriver instance
     * @param expectedUrl Expected URL substring
     * @return true if URL contains expected text, false otherwise
     */
    public static boolean assertUrlContains(WebDriver driver, String expectedUrl) {
        try {
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains(expectedUrl)) {
                logger.info("✓ URL contains: " + expectedUrl);
                return true;
            } else {
                logger.info("✗ URL does not contain: " + expectedUrl + ". Current URL: " + currentUrl);
                return false;
            }
        } catch (Exception e) {
            logger.info("✗ Error checking URL: " + e.getMessage());
            return false;
        }
    }

    /**
     * Assert that the current URL equals expected URL
     *
     * @param driver WebDriver instance
     * @param expectedUrl Expected URL
     * @return true if URL matches, false otherwise
     */
    public static boolean assertUrlEquals(WebDriver driver, String expectedUrl) {
        try {
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.equals(expectedUrl)) {
                logger.info("✓ URL matches: " + expectedUrl);
                return true;
            } else {
                logger.info("✗ URL does not match. Expected: " + expectedUrl + ", Current: " + currentUrl);
                return false;
            }
        } catch (Exception e) {
            logger.info("✗ Error checking URL: " + e.getMessage());
            return false;
        }
    }

}
