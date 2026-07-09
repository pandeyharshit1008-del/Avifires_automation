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
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement element = wait.until(
                    ExpectedConditions.elementToBeClickable(locator));

            if (element.isEnabled()) {
                logger.info("✓ Element is enabled: {}", locator);
                return true;
            }

            return false;

        } catch (Exception e) {
            logger.info("✗ Element did not become enabled: {}", locator);
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
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));

            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(locator));

            String actualText = element.getText().trim();

            if (actualText.equals(expectedText)) {
                logger.info("✓ Text matches. Expected: {}, Actual: {}", expectedText, actualText);
                return true;
            } else {
                logger.info("✗ Text does not match. Expected: {}, Actual: {}", expectedText, actualText);
                return false;
            }
        } catch (Exception e) {
            logger.info("✗ Error comparing text: {}", e.getMessage());
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
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            boolean matched = wait.until(
                    ExpectedConditions.urlContains(expectedUrl));

            if (matched) {
                logger.info("✓ URL contains: {}", expectedUrl);
                return true;
            }

            return false;

        } catch (Exception e) {
            logger.info("✗ URL did not contain: {}", expectedUrl);
            logger.info("Current URL: {}", driver.getCurrentUrl());
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
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            boolean matched = wait.until(
                    ExpectedConditions.urlToBe(expectedUrl));

            if (matched) {
                logger.info("✓ URL matches: {}", expectedUrl);
                return true;
            }

            return false;

        } catch (Exception e) {
            logger.info("✗ URL did not become: {}", expectedUrl);
            logger.info("Current URL: {}", driver.getCurrentUrl());
            return false;
        }
    }

}
