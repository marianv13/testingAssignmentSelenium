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

class OrdersPage extends PageBase {

    private By ordersContentEmptyDiv = By.xpath("//*[@id=\"main_content\"]/div[1]/div/div[3]/div/div/p");
    public OrdersPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.jofogas.hu/fiok/haztol-hazig/eladok");
    }

    public String checkOrdersContentEmptyElement() {
        return this.waitAndReturnElement(ordersContentEmptyDiv).getText();
    }

}