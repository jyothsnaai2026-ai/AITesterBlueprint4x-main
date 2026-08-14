package com.salesforce.automation.pages;

import com.salesforce.automation.base.BasePage;
import com.salesforce.automation.utils.WaitUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@id='username']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@id='rememberUn']")
    private WebElement rememberMeCheckbox;

    @FindBy(xpath = "//div[@id='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver, WaitUtil waitUtil) {
        super(driver, waitUtil);
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        type(usernameField, username);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public void doLogin(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public void checkRememberMe() {
        try {
            if (!rememberMeCheckbox.isSelected()) {
                click(rememberMeCheckbox);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to select Remember Me checkbox", e);
        }
    }

    public boolean isRememberMeSelected() {
        try {
            return rememberMeCheckbox.isSelected();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read Remember Me checkbox state", e);
        }
    }

    public String getErrorMessage() {
        try {
            waitUtil.waitForNonEmptyText(errorMessage);
            return errorMessage.getText().trim();
        } catch (Exception e) {
            throw new RuntimeException("Login error message did not appear", e);
        }
    }

    public boolean isErrorDisplayed() {
        try {
            return errorMessage.isDisplayed() && !errorMessage.getText().trim().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUsernameFieldDisplayed() {
        return isDisplayed(usernameField);
    }

    public boolean isPasswordFieldDisplayed() {
        return isDisplayed(passwordField);
    }

    public boolean isLoginButtonDisplayed() {
        return isDisplayed(loginButton);
    }

    public boolean isRememberMeDisplayed() {
        return isDisplayed(rememberMeCheckbox);
    }
}
