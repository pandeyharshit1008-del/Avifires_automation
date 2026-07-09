package com.automation.pages;

import com.automation.utils.AssertUtils;
import com.automation.utils.ConfigConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,
                Duration.ofSeconds(ConfigConstants.WAIT_TIMEOUT_SECONDS));
    }

    protected void click(By locator) {
        AssertUtils.assertClickable(driver, locator);
        driver.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    protected boolean isVisible(By locator) {
        return AssertUtils.assertVisible(driver, locator);
    }

    protected void navigateTo(String url) {
        driver.navigate().to(url);
    }

    protected void navigateToPage(By locator, String pageName) {
        click(locator);
        System.out.println("✓ Navigated to " + pageName);
    }

    protected void scrollToElement(By locator) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element);
    }
}