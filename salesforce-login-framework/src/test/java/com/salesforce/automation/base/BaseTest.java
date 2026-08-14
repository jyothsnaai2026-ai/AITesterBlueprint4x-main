package com.salesforce.automation.base;

import com.salesforce.automation.config.ConfigReader;
import com.salesforce.automation.pages.LoginPage;
import com.salesforce.automation.utils.WaitUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    protected WebDriver driver;
    protected ConfigReader config;
    protected WaitUtil waitUtil;
    protected LoginPage loginPage;

    @BeforeTest
    public void setUp() {
        config = new ConfigReader("config.properties");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(config.getProperty("implicit.wait.seconds"))));
        waitUtil = new WaitUtil(driver, Long.parseLong(config.getProperty("explicit.wait.seconds")));
    }

    @BeforeMethod
    public void openLoginPage() {
        driver.get(config.getProperty("base.url"));
        waitUtil.waitForUrlContains("login.salesforce.com");
        loginPage = new LoginPage(driver, waitUtil);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
