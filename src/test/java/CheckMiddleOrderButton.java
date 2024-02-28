import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.pageObject.FormPagePersonalData;
import ru.yandex.praktikum.pageObject.HomePage;

public class CheckMiddleOrderButton {
    public static final String URL = "https://qa-scooter.praktikum-services.ru/";
    private WebDriver driver = null;
    public WebDriver createWebDriver(String browser){
        if (browser.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            System.out.println("Неподдреживаемый браузер:" + browser);
        }
        return driver;
    }

    @Before
    public void setUp(){
        driver = createWebDriver("chrome");
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void checkMiddleOrderButton(){
        HomePage objHomePage = new HomePage(driver);
        FormPagePersonalData objFormPersonalData = new FormPagePersonalData(driver);

        objHomePage.waitingLoadPage();
        objHomePage.clickCookieButton();

        objHomePage.clickMiddleButton();
        objFormPersonalData.waitingLoadPage();
        Assert.assertTrue(objFormPersonalData.getPlaceholderName().contains("Имя"));
    }
}
