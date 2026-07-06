package com.automation.locators;

import org.openqa.selenium.By;

public class HomeLocators {

    public static final By FINANCIAL_CALCULATOR_SLIDER = By.id("slider-financialCalculator");

    public static final By FINANCIAL_CALCULATOR_SLIDER_THUMB = By.cssSelector("#slider-financialCalculator .MuiSlider-thumb");

    public static final By EXPECTED_RETURN_SLIDER = By.xpath("//input[@aria-labelledby='expected-return-slider']/ancestor::span[contains(@class,'MuiSlider-root')]");

    public static final By EXPECTED_RETURN_SLIDER_THUMB = By.xpath("//input[@aria-labelledby='expected-return-slider']/ancestor::span[contains(@class,'MuiSlider-root')]//span[contains(@class,'MuiSlider-thumb')]");

    public static final By TIME_PERIOD_SLIDER = By.xpath("//input[@aria-labelledby='time-period-slider']/ancestor::span[contains(@class,'MuiSlider-root')]");

    public static final By TIME_PERIOD_SLIDER_THUMB = By.xpath("//input[@aria-labelledby='time-period-slider']/ancestor::span[contains(@class,'MuiSlider-root')]//span[contains(@class,'MuiSlider-thumb')]");

    public static final By InvestmentAmount = By.xpath("(//div[contains(@class,'tw-rounded-md')]//input)[1]");

}
