package com.salesforce.automation.tests;

import com.salesforce.automation.base.BaseTest;
import com.salesforce.automation.config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginInvalidTests extends BaseTest {

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentialsData() {
        ConfigReader testConfig = new ConfigReader("config.properties");
        return new Object[][] {
            {"", testConfig.getProperty("valid.password"), "Please enter your username."},
            {testConfig.getProperty("valid.username"), "", "Please enter your password."},
            {testConfig.getProperty("invalid.username"), testConfig.getProperty("invalid.password"), "Please check your username and password"},
            {"", "", "Please enter your username."}
        };
    }

    @Test(dataProvider = "invalidCredentials")
    public void verifyInvalidLoginDisplaysError(String username, String password, String expectedMessage) {
        loginPage.doLogin(username, password);
        String actualMessage = loginPage.getErrorMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "Expected message to contain [" + expectedMessage + "] but found [" + actualMessage + "]");
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error banner should be displayed for invalid credentials");
        Assert.assertTrue(waitUtil.waitForUrlContains("login.salesforce.com"), "User should remain on the login page");
    }
}
