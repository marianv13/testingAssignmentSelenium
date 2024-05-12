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

class MessagePage extends PageBase {

    private By messagesContentEmptyDiv = By.xpath("//*[@id=\"messaging-widget-empty\"]/div/h3");
    public MessagePage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.jofogas.hu/fiok/postalada");
    }

    public String checkMessagesContentEmptyElement() {
        return this.waitAndReturnElement(messagesContentEmptyDiv).getText();
    }

}