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

class AdvertisementPage extends PageBase {

    private By advertisementContentEmptyDiv = By.xpath("//*[@id=\"main_content\"]/div[1]/div[2]/div/div/div[2]/div[1]/div[2]/h3");
    public AdvertisementPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.jofogas.hu/fiok/hirdeteseim");
    }

    public String checkAdvertisementContentEmptyElement() {
        return this.waitAndReturnElement(advertisementContentEmptyDiv).getText();
    }

}