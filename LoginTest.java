import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.example.Common.Constants;


public class TestLogin {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(Constants.CHROME_ARGUMENT);
        driver = new ChromeDriver(chromeOptions);
    }

    @AfterMethod
    public void cleanup() {
        driver.quit();
    }

    @Test
    public void verifyLoginWithValidCredentials() {
        PageLogin pageLogin = new PageLogin(driver);
        PageLogin.open();
        PageLogin.setUsername("admin");
        PageLogin.setPassword("manager");
        PageLogin.clickLoginButton();
    }

    @Test
    public void verifyLoginWithIncorrectUserName() {
        PageLogin pageLogin = new PageLogin(driver);
        PageLogin.open();
        PageLogin.setUsername("User");
        PageLogin.setPassword("manager");
        PageLogin.clickLoginButton();
    }

    @Test
    public void verifyLoginWithIncorrectPassword() {
        PageLogin loginPage = new PageLogin(driver);
        PageLogin.open();
        PageLogin.setUsername("admin");
        PageLogin.setPassword("Password");
        PageLogin.clickLoginButton();
    }
    @Test
    public void verifySuccessfulLogout() {
        PageLogin pageLogin = new PageLogin(driver);
        ReportPage reportsPage = new ReportPage(driver);

        PageLogin.open();
        PageLogin.setUsername("admin");
        PageLogin.setPassword("manager");
        PageLogin.clickLoginButton();

    }
    @Test
    public void verifyLoginWithEmptyCredentials() {
        PageLogin pageLogin = new PageLogin(driver);
        PageLogin.open();
        PageLogin.setUsername("");
        PageLogin.setPassword("");
        PageLogin.clickLoginButton();

    }
    @Test
    public void verifyLoginWithLockedAccount() {
        PageLogin pageLogin = new PageLogin(driver);
        PageLogin.open();
        PageLogin.setUsername("lockedUser");
        PageLogin.setPassword("password123");
        PageLogin.clickLoginButton();

    }



    @Test
    public void verifyAccessToReportsWithoutLogin() {
        ReportPage reportsPage = new ReportPage(driver);
        reportsPage.open();

    }



    @Test
    public void reportsDashboard() {
        PageLogin PageLogin = new PageLogin(driver);
        ReportPage reportsPage = new ReportPage(driver);

        PageLogin.open();
        PageLogin.setUsername("admin");
        PageLogin.setPassword("manager");
        PageLogin.clickLoginButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/user/submit_tt.do"));

        reportsPage.clickReportsContainer();
    }
}