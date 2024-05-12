import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JofogasTestSuite {
    public WebDriver driver;
    private static boolean loginResult = false;
    
    @Before
    public void setup()  throws MalformedURLException  {

        /* Driver for docker run
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        options.setHeadless(true);
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();*/
        System.setProperty("webdriver.chrome.driver", "C:/Users/Lenovo/SeleniumTask/selenium-2/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.setHeadless(true);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
    }
    
    //We test the login function
    @Test
    public void test1_login() {
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertEquals("https://www.jofogas.hu/", this.driver.getCurrentUrl());
        mainPage.login("jg3ih8@inf.elte.hu","PWDtest54321");
        Assert.assertTrue(mainPage.getAccountButtonText().contains("Felhasz") || mainPage.getAccountButtonText().contains("test") );
        loginResult = true;
    }

    //We test the logout function - depends on login function being successfull
    @Test
    public void test2_logout() {
        Assume.assumeTrue(loginResult);
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertEquals("https://www.jofogas.hu/", this.driver.getCurrentUrl());
        mainPage.login("jg3ih8@inf.elte.hu","PWDtest54321");
        mainPage.logout();
        Assert.assertTrue(mainPage.getSigninButtonText().contains("Bel"));
    }

    //We change the newsletter settings - depends on login function being successfull
    @Test
    public void test3_changeNewsletterSettings(){
        Assume.assumeTrue(loginResult);
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertEquals("https://www.jofogas.hu/", this.driver.getCurrentUrl());
        mainPage.login("jg3ih8@inf.elte.hu","PWDtest54321");
        AccountPage accountPage = new AccountPage(this.driver);
        Assert.assertEquals("https://www.jofogas.hu/fiok", this.driver.getCurrentUrl());
        Boolean setting1 = accountPage.getNewsletterSettings();
        accountPage.changeNewsletterSettings();
        Boolean setting2 = accountPage.getNewsletterSettings();
        Assert.assertNotSame(setting1, setting2);
    }

    //We change the username - depends on login function being successfull
    @Test
    public void test4_changeUsername(){
        //Assume.assumeTrue(loginResult);
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertEquals("https://www.jofogas.hu/", this.driver.getCurrentUrl());
        mainPage.login("jg3ih8@inf.elte.hu","PWDtest54321");
        AccountPage accountPage = new AccountPage(this.driver);
        Assert.assertEquals("https://www.jofogas.hu/fiok", this.driver.getCurrentUrl());
        String oldName = accountPage.getUsername();
        accountPage.changeUsername();
        String newName = accountPage.getUsername();
        Assert.assertNotSame(oldName, newName);
    }

    //We check the advertisements page (it is supposed to be empty) - depends on login function being successfull
    @Test
    public void test5_checkAdvertisementsEmpty(){
        Assume.assumeTrue(loginResult);
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertEquals("https://www.jofogas.hu/", this.driver.getCurrentUrl());
        mainPage.login("jg3ih8@inf.elte.hu","PWDtest54321");
        AdvertisementPage advertisementPage = new AdvertisementPage(this.driver);
        Assert.assertEquals("https://www.jofogas.hu/fiok/hirdeteseim", this.driver.getCurrentUrl());
        Assert.assertTrue(advertisementPage.checkAdvertisementContentEmptyElement().equals("Adj fel egyet most!"));
    }

    //We check the messages page (it is supposed to be empty) - depends on login function being successfull
    @Test
    public void test6_checkMessagesEmpty(){
        //Assume.assumeTrue(loginResult);
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertEquals("https://www.jofogas.hu/", this.driver.getCurrentUrl());
        mainPage.login("jg3ih8@inf.elte.hu","PWDtest54321");
        MessagePage messagePage = new MessagePage(this.driver);
        Assert.assertEquals("https://www.jofogas.hu/fiok/postalada", this.driver.getCurrentUrl());
        Assert.assertTrue(messagePage.checkMessagesContentEmptyElement().contains("nincs"));
    }

    //We check the orders page (it is supposed to be empty) - depends on login function being successfull
    @Test
    public void test6_checkOrdersEmpty(){
        Assume.assumeTrue(loginResult);
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertEquals("https://www.jofogas.hu/", this.driver.getCurrentUrl());
        mainPage.login("jg3ih8@inf.elte.hu","PWDtest54321");
        OrdersPage ordersPage = new OrdersPage(this.driver);
        Assert.assertEquals("https://www.jofogas.hu/fiok/haztol-hazig/eladok", this.driver.getCurrentUrl());
        Assert.assertTrue(ordersPage.checkOrdersContentEmptyElement().contains("Nincs"));
    }

    //We search for a car, for opel brand in this case
    @Test
    public void searchForOpelCars(){
        CarPage carPage = new CarPage(this.driver);
        Assert.assertEquals("https://auto.jofogas.hu/#channel=main_page_blue_bar&pos=blue_bar&opt=homepage", this.driver.getCurrentUrl());
        carPage.searchForOpel();
        Assert.assertTrue(this.driver.getCurrentUrl().contains("opel"));
    }

    //We search for a job, for programmer in this case
    @Test
    public void searchForDeveloperJob(){
        JobPage jobPage = new JobPage(this.driver);
        Assert.assertEquals("https://allas.jofogas.hu/#channel=main_page_blue_bar&pos=blue_bar&opt=homepage", this.driver.getCurrentUrl());
        jobPage.searchForJob();
        Assert.assertTrue(this.driver.getCurrentUrl().contains("program"));
    }

    //We search for a flat, in Budapest between 50 and 100 m2 in this case
    @Test
    public void searchForBudapest50m100m(){
        PropertiesPage propertiesPage = new PropertiesPage(this.driver);
        Assert.assertEquals("https://ingatlan.jofogas.hu/#channel=main_page_blue_bar&pos=blue_bar&opt=homepage", this.driver.getCurrentUrl());
        propertiesPage.searchForBudapest50m100m();
        Assert.assertTrue(this.driver.getCurrentUrl().contains("budapest"));
        Assert.assertTrue(this.driver.getCurrentUrl().contains("min_size=50"));
        Assert.assertTrue(this.driver.getCurrentUrl().contains("max_size=100"));
    }

    //We batch check the pages that do not require a login, make sure they do not redirect us
    @Test
    public void batchNavigationTest(){
        String[] urlArray= { "https://auto.jofogas.hu/#channel=main_page_blue_bar&pos=blue_bar&opt=homepage",
            "https://allas.jofogas.hu/#channel=main_page_blue_bar&pos=blue_bar&opt=homepage",
            "https://www.jofogas.hu/",
            "https://ingatlan.jofogas.hu/#channel=main_page_blue_bar&pos=blue_bar&opt=homepage"};

        for (int i = 0; i < urlArray.length; i++) {
            this.driver.get(urlArray[i]);
            Assert.assertEquals(this.driver.getCurrentUrl(),(urlArray[i]));
        }

    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
