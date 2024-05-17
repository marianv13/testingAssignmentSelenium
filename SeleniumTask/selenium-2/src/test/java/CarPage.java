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

class CarPage extends PageBase {

    private By brandInput = By.xpath("//*[@id=\"cb_cars\"]");
    private By firstOption = By.className("MuiAutocomplete-option");
    private By photoSearchOnlyCheckbox = By.xpath("/html/body/div[2]/div/div[1]/div[3]/div/div/div/form/div[1]/div[13]/label/span[1]/input");
    private By searchButton = By.xpath("//*[@id=\"page-content\"]/div[1]/div[3]/div/div/div/form/div[2]/div[2]/div/button");
    private By loginButton = By.xpath("//*[@id=\"modal-login-open-desktop\"]");
    public CarPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://auto.jofogas.hu/#channel=main_page_blue_bar&pos=blue_bar&opt=homepage");
    }

    public void searchForOpel(){
        driver.findElement(brandInput).sendKeys("Opel");
        driver.findElement(firstOption).click();
        driver.findElement(photoSearchOnlyCheckbox).click();
        driver.findElement(searchButton).click();
        Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

}