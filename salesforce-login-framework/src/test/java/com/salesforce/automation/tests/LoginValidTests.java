package com.salesforce.automation.tests;

import com.salesforce.automation.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginValidTests extends BaseTest {

    @Test(priority = 1)
    public void verifyLoginPageElementsAreDisplayed() {
        Assert.assertTrue(loginPage.isUsernameFieldDisplayed(), "Username field is not displayed");
        Assert.assertTrue(loginPage.isPasswordFieldDisplayed(), "Password field is not displayed");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button is not displayed");
        Assert.assertTrue(loginPage.isRememberMeDisplayed(), "Remember Me checkbox is not displayed");
    }

    @Test(priority = 2)
    public void verifyValidLoginRedirectsToHomePage() {
        loginPage.doLogin(config.getProperty("valid.username"), config.getProperty("valid.password"));
        boolean redirected = waitUtil.waitForUrlNotContains("login.salesforce.com");
        Assert.assertTrue(redirected, "User was not redirected after submitting valid credentials");
        Assert.assertFalse(loginPage.isErrorDisplayed(), "Error banner should not be shown for valid credentials");
    }

    @Test(priority = 3)
    public void verifyRememberMeCheckboxCanBeSelected() {
        loginPage.checkRememberMe();
        Assert.assertTrue(loginPage.isRememberMeSelected(), "Remember Me checkbox should be selected after clicking it");
    }
}
