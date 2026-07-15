package com.automation.pages;

import com.automation.locators.HomeLocators;
import com.automation.utils.ConfigConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

/**
 * Login page object
 * Contains all login-related actions and workflows
 */
public class HomePage {

    private static final Logger logger = LoggerFactory.getLogger(HomePage.class);

    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * Constructor to initialize driver and wait
     *
     * @param driver WebDriver instance
     */
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigConstants.WAIT_TIMEOUT_SECONDS));
    }

    /**
     * Set the investment amount on the financial return calculator slider
     *
     * @param targetAmount The investment amount to set
     */
    public void setInvestmentAmount(int targetAmount) {

        WebElement slider = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        HomeLocators.FINANCIAL_CALCULATOR_SLIDER));

        WebElement thumb = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        HomeLocators.FINANCIAL_CALCULATOR_SLIDER_THUMB));

        WebElement input = thumb.findElement(By.cssSelector("input[type='range']"));

        int min = Integer.parseInt(input.getAttribute("min"));
        int max = Integer.parseInt(input.getAttribute("max"));
        int current = Integer.parseInt(input.getAttribute("aria-valuenow"));

        if (targetAmount < min || targetAmount > max) {
            throw new IllegalArgumentException(
                    "Investment amount must be between " + min + " and " + max);
        }

        int sliderWidth = slider.getSize().getWidth();

        double currentPercent =
                (double)(current - min) / (max - min);

        double targetPercent =
                (double)(targetAmount - min) / (max - min);

        int movePixels =
                (int)((targetPercent - currentPercent) * sliderWidth);

        logger.info("Current value : " + current);
        logger.info("Target value  : " + targetAmount);
        logger.info("Move pixels   : " + movePixels);

        new Actions(driver)
                .clickAndHold(thumb)
                .moveByOffset(movePixels, 0)
                .release()
                .perform();

        int tolerance = 50000; // Accept ±50,000

        wait.until(driver -> {

            WebElement valueInput =
                    driver.findElement(HomeLocators.FINANCIAL_CALCULATOR_SLIDER_THUMB)
                            .findElement(By.cssSelector("input[type='range']"));

            int newValue =
                    Integer.parseInt(valueInput.getAttribute("aria-valuenow"));

            logger.info("Slider value = " + newValue);

            return Math.abs(newValue - targetAmount) <= tolerance;
        });

        logger.info("✓ Investment amount set successfully.");
    }

    // Set the expected return on the financial calculator slider
    public void setExpectedReturn(int targetReturn) {

        WebElement slider = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        HomeLocators.EXPECTED_RETURN_SLIDER));

        WebElement thumb = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        HomeLocators.EXPECTED_RETURN_SLIDER_THUMB));

        WebElement input = thumb.findElement(By.cssSelector("input[type='range']"));

        int min = Integer.parseInt(input.getAttribute("min"));
        int max = Integer.parseInt(input.getAttribute("max"));
        int current = Integer.parseInt(input.getAttribute("aria-valuenow"));

        if (targetReturn < min || targetReturn > max) {
            throw new IllegalArgumentException(
                    "Expected return must be between " + min + " and " + max);
        }

        int sliderWidth = slider.getSize().getWidth();

        double currentPercent =
                (double) (current - min) / (max - min);

        double targetPercent =
                (double) (targetReturn - min) / (max - min);

        int movePixels =
                (int) ((targetPercent - currentPercent) * sliderWidth);

        logger.info("Current Return : " + current);
        logger.info("Target Return  : " + targetReturn);
        logger.info("Move Pixels    : " + movePixels);

        new Actions(driver)
                .clickAndHold(thumb)
                .moveByOffset(movePixels, 0)
                .release()
                .perform();

        WebElement valueInput = driver.findElement(HomeLocators.EXPECTED_RETURN_SLIDER_THUMB)
                .findElement(By.cssSelector("input[type='range']"));

        int actual = Integer.parseInt(valueInput.getAttribute("aria-valuenow"));

        int tolerance = 1;

        if (Math.abs(actual - targetReturn) > tolerance) {
            throw new AssertionError(
                    "Expected approximately " + targetReturn +
                            " but slider stopped at " + actual);
        }

        logger.info("✓ Expected return set to " + actual + "%");
    }

    // Set the time period on the financial calculator slider
    public void setTimePeriod(int targetYears) {

        WebElement slider = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        HomeLocators.TIME_PERIOD_SLIDER));

        WebElement thumb = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        HomeLocators.TIME_PERIOD_SLIDER_THUMB));

        WebElement input = thumb.findElement(By.cssSelector("input[type='range']"));

        int min = Integer.parseInt(input.getAttribute("min"));
        int max = Integer.parseInt(input.getAttribute("max"));
        int current = Integer.parseInt(input.getAttribute("aria-valuenow"));

        if (targetYears < min || targetYears > max) {
            throw new IllegalArgumentException(
                    "Time period must be between " + min + " and " + max + " years.");
        }

        int sliderWidth = slider.getSize().getWidth();

        double currentPercent =
                (double) (current - min) / (max - min);

        double targetPercent =
                (double) (targetYears - min) / (max - min);

        int movePixels =
                (int) ((targetPercent - currentPercent) * sliderWidth);

        logger.info("Current Time Period : " + current);
        logger.info("Target Time Period  : " + targetYears);
        logger.info("Move Pixels         : " + movePixels);

        new Actions(driver)
                .clickAndHold(thumb)
                .moveByOffset(movePixels, 0)
                .release()
                .perform();

        WebElement valueInput = driver.findElement(HomeLocators.TIME_PERIOD_SLIDER_THUMB)
                .findElement(By.cssSelector("input[type='range']"));

        int actual = Integer.parseInt(valueInput.getAttribute("aria-valuenow"));

        int tolerance = 1;

        if (Math.abs(actual - targetYears) > tolerance) {
            throw new AssertionError(
                    "Expected approximately " + targetYears +
                            " years but slider stopped at " + actual + " years.");
        }

        logger.info("✓ Time period set to " + actual + " years");
    }

    public void getSIPValue() {
        WebElement sipValueElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        HomeLocators.InvestmentAmount));

        String sipValueText = sipValueElement.getText();
        logger.info("✓ SIP Value: " + sipValueText);
    }
}
