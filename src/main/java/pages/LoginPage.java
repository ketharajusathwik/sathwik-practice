package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.WaitUtils;

public class LoginPage {

    private WebDriver driver;
    private WaitUtils wait;

    private By username = By.id("user-name");
    private By password = By.id("password");
    private By loginBtn = By.id("login-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public void login(String user, String pwd) {

        wait.waitForVisibility(username).sendKeys(user);

        wait.waitForVisibility(password).sendKeys(pwd);

        wait.waitForClickable(loginBtn).click();
    }
}