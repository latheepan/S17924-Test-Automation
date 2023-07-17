import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.example.Common.Constants;

public class LoginPage {
    private WebDriver driver;
    private By usernameField = By.id("username");
    private By passwordField = By.name("pwd");
    private By loginButton = By.id("loginButton");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public static void open() {
        driver.get(Constants.WEB_URL);
        driver.manage().window().maximize();
    }

    public static void setUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public static void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public static void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}

