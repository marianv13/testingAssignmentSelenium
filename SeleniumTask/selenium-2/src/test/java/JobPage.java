import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;

class JobPage extends PageBase {

    private By jobInputField = By.xpath("//*[@id=\"q\"]");
    private By searchButton = By.xpath("//*[@id=\"page-content\"]/div[1]/div[3]/div/div/div/form/div[2]/div[2]/div/button");
    private By loginButton = By.xpath("//*[@id=\"modal-login-open-desktop\"]");

    public JobPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://allas.jofogas.hu/#channel=main_page_blue_bar&pos=blue_bar&opt=homepage");
    }
    public void searchForJob() {
        driver.findElement(jobInputField).sendKeys("programfejlesztő");
        driver.findElement(searchButton).click();
        Wait<WebDriver> wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

}