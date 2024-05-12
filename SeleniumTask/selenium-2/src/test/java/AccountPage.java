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

class AccountPage extends PageBase {

    private By newsletterSlider = By.xpath("//*[@id=\"main_content\"]/div[1]/div/div/div[5]/div[2]/div/div/div/div[2]/label/span");
    private By changePopupOkButton = By.xpath("/html/body/div[10]/div[2]/div/div[3]");
    private By changeUserDataButton = By.xpath("//*[@id=\"main_content\"]/div[1]/div/div/div[2]/div/div[2]/div[2]/a");
    private By usernameInputField = By.xpath("/html/body/div[16]/div/div/div/div[2]/form/div[2]/div/div[1]/div/input");
    private By acceptPrivacyCheckbox = By.xpath("/html/body/div[16]/div/div/div/div[2]/form/div[2]/div/div[4]/label/input");
    private By saveChangesButton = By.xpath("/html/body/div[16]/div/div/div/div[2]/form/div[3]/div/div[2]/button");
    private By usernameElement = By.xpath("//*[@id=\"main_content\"]/div[1]/div/div/div[2]/div/div[2]/div[2]/div[1]/span");
    private By okButton = By.xpath("/html/body/div[10]/div[2]/div/div[3]");


    public AccountPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.jofogas.hu/fiok");
    }

    public void changeNewsletterSettings(){
        driver.findElement(newsletterSlider).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(changePopupOkButton)).click();
    }

    public void changeUsername(){
        driver.findElement(changeUserDataButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInputField)).sendKeys("test");
        driver.findElement(acceptPrivacyCheckbox).click();
        driver.findElement(saveChangesButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(okButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameElement));
    }

    public Boolean getNewsletterSettings(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(newsletterSlider)).getAttribute("class").contains("checked");
    }

    public String getUsername(){
        return this.waitAndReturnElement(usernameElement).getText();
    }

}