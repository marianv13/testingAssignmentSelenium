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

class PropertiesPage extends PageBase {

    private By regionInput = By.xpath("//*[@id=\"w\"]");
    private By firstOption = By.className("MuiAutocomplete-option");
    private By minSizeInput = By.xpath("//*[@id=\"min_size\"]");
    private By maxSizeInput = By.xpath("//*[@id=\"max_size\"]");
    private By searchButton = By.xpath("//*[@id=\"page-content\"]/div[1]/div[3]/div/div/div/form/div[4]/div[2]/div/button");
    private By loginButton = By.xpath("//*[@id=\"modal-login-open-desktop\"]");

    public PropertiesPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://ingatlan.jofogas.hu/#channel=main_page_blue_bar&pos=blue_bar&opt=homepage");
    }

    public void searchForBudapest50m100m(){
        driver.findElement(regionInput).sendKeys("Budapest");
        driver.findElement(firstOption).click();
        driver.findElement(minSizeInput).sendKeys("50");
        driver.findElement(maxSizeInput).sendKeys("100");
        driver.findElement(searchButton).click();
        Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

}