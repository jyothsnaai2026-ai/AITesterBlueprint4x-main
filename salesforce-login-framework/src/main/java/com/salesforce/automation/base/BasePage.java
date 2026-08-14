package com.salesforce.automation.base;

import com.salesforce.automation.utils.WaitUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WaitUtil waitUtil;

    protected BasePage(WebDriver driver, WaitUtil waitUtil) {
        this.driver = driver;
        this.waitUtil = waitUtil;
    }

    protected void click(WebElement element) {
        try {
            waitUtil.waitForClickable(element).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click element", e);
        }
    }

    protected void type(WebElement element, String text) {
        try {
            WebElement target = waitUtil.waitForVisibility(element);
            target.clear();
            target.sendKeys(text);
        } catch (Exception e) {
            throw new RuntimeException("Failed to type text into element", e);
        }
    }

    protected String getText(WebElement element) {
        try {
            return waitUtil.waitForVisibility(element).getText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read text from element", e);
        }
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            waitUtil.waitForVisibility(element);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected String getPageTitle() {
        return driver.getTitle();
    }
}
