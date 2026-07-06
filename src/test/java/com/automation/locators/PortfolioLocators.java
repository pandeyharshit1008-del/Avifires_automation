package com.automation.locators;

import org.openqa.selenium.By;

public class PortfolioLocators {

    public static final By EDIT_PORTFOLIO_BUTTON = By.xpath("//button[normalize-space()='Edit Portfolio']");

    public static final By investmentTypeOption(String option) {
        return By.xpath(
                "//span[normalize-space()='Investment type']/following::select[1]/option[normalize-space()='" + option + "']");
    }

    public static final By MUTUAL_FUND_SEARCH_BOX = By.xpath("//input[@placeholder='Search mutual fund…']");

    public static final By frequencyDropdown =   By.xpath("//span[normalize-space()='Frequency']/following::select[1]");

    public static final By INVESTMENT_DATE = By.xpath("//span[normalize-space()='Investment date']/following::input[@type='date'][1]");

    public static final By AMOUNT_INPUT =
            By.xpath("//span[normalize-space()='Amount (₹)']/following::input[@type='number'][1]");

    public static final By ADD_RECORD_BUTTON = By.xpath("//button[normalize-space()='Add record']");

}
