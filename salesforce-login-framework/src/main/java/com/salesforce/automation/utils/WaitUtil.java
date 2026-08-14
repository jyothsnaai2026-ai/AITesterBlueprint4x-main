package com.salesforce.automation.utils;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {

    private final WebDriverWait wait;

    public WaitUtil(WebDriver driver, long timeoutSeconds) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
    }

    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean waitForUrlContains(String text) {
        return wait.until(ExpectedConditions.urlContains(text));
    }

    public boolean waitForUrlNotContains(String text) {
        return wait.until(ExpectedConditions.not(ExpectedConditions.urlContains(text)));
    }

    public boolean waitForNonEmptyText(WebElement element) {
        return wait.until(driver -> element.isDisplayed() && !element.getText().trim().isEmpty());
    }
}
