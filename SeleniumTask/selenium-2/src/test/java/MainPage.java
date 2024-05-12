import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


class MainPage extends PageBase {

    private By startSigninButton = By.xpath("//*[@id=\"modal-login-open-desktop\"]");
    private By signinEmailField = By.xpath("//*[@id=\"login-email\"]");
    private By signinPasswordField = By.xpath("//*[@id=\"login-password\"]");
    private By signinButton = By.xpath("//*[@id=\"login-submit\"]");
    private By myAccountButton = By.xpath("//*[@id=\"simple-btn-keyboard-nav\"]/div/a/div");
    private By logoutButton = By.xpath("//*[@id=\"link-logout\"]");



    
    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.jofogas.hu/");
    }    
    
    public String getAccountButtonText() {
        return this.waitAndReturnElement(myAccountButton).getText();
    }

    public String getSigninButtonText() {
        return this.waitAndReturnElement(startSigninButton).getText();
    }

    public void login(String username, String password) {
        driver.findElement(startSigninButton).click();
        driver.findElement(signinEmailField).sendKeys(username);
        driver.findElement(signinPasswordField).sendKeys(password);
        driver.findElement(signinButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(myAccountButton));
    }

    public void logout() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton)).click();
    }
}
