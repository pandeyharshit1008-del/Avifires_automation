package com.automation.utils;

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
            System.out.println("✓ Element is visible: " + locator);
            return true;
        } catch (Exception e) {
            System.out.println("✗ Element is not visible: " + locator);
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
            System.out.println("✓ Element exists in DOM: " + locator);
            return true;
        } catch (Exception e) {
            System.out.println("✗ Element does not exist in DOM: " + locator);
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
            System.out.println("✓ Element is clickable: " + locator);
            return true;
        } catch (Exception e) {
            System.out.println("✗ Element is not clickable: " + locator);
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
                System.out.println("✓ Element is enabled: " + locator);
            } else {
                System.out.println("✗ Element is not enabled: " + locator);
            }
            return isEnabled;
        } catch (Exception e) {
            System.out.println("✗ Cannot determine if element is enabled: " + locator);
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
                System.out.println("✓ Text matches. Expected: " + expectedText + ", Actual: " + actualText);
                return true;
            } else {
                System.out.println("✗ Text does not match. Expected: " + expectedText + ", Actual: " + actualText);
                return false;
            }
        } catch (Exception e) {
            System.out.println("✗ Error comparing text: " + e.getMessage());
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
                System.out.println("✓ Text contains: " + expectedText);
                return true;
            } else {
                System.out.println("✗ Text does not contain: " + expectedText + ". Actual: " + actualText);
                return false;
            }
        } catch (Exception e) {
            System.out.println("✗ Error checking text content: " + e.getMessage());
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
                System.out.println("✓ URL contains: " + expectedUrl);
                return true;
            } else {
                System.out.println("✗ URL does not contain: " + expectedUrl + ". Current URL: " + currentUrl);
                return false;
            }
        } catch (Exception e) {
            System.out.println("✗ Error checking URL: " + e.getMessage());
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
                System.out.println("✓ URL matches: " + expectedUrl);
                return true;
            } else {
                System.out.println("✗ URL does not match. Expected: " + expectedUrl + ", Current: " + currentUrl);
                return false;
            }
        } catch (Exception e) {
            System.out.println("✗ Error checking URL: " + e.getMessage());
            return false;
        }
    }

}
