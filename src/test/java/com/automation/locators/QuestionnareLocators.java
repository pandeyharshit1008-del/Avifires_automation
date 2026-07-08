package com.automation.locators;

import org.openqa.selenium.By;

public class QuestionnareLocators {

    public static final String INVESTMENT_GOAL_OPTION = "//p[normalize-space()='%s']/ancestor::div[contains(@class,'tw-cursor-pointer')]";

    public static final By CORPUS_NEEDED_INPUT = By.xpath("//label[normalize-space()='Corpus Needed to Achieve Goal(Maturity Time)']/following-sibling::input");

    public static final By TIME_OF_INVESTMENT = By.xpath("//label[normalize-space()='Period to Realise Goal']/following-sibling::input");

    public static final By INVESTMENT_GOAL_SUBMIT_BUTTON = By.xpath("//button[normalize-space()='Submit']");

    public static final By PREFERENCES_AND_PRIORITIES = By.xpath("//span[normalize-space()='Preferences & Priorities']");

    public static final String RADIO_OPTION_BY_TEXT = "//label[normalize-space()='%s']";

    public static final By NEXT_BUTTON = By.xpath("//button[.//*[contains(@class,'lucide-arrow-right')]]");

    public static final By PREFERENCES_AND_PRIORITIES_SUBMIT_BUTTON = By.xpath("(//button[normalize-space()='Submit'])[2]");

    public static final By INVESTMENT_SUCCESS_TOAST = By.xpath("//div[text()='Investment plans and goal projection saved successfully']");

    public static final By PREFERENCES_SUCCESS_TOAST = By.xpath("//div[text()='Investment preferences submitted successfully']");

}
