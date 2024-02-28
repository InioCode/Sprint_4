import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.pageObject.*;

@RunWith(Parameterized.class)
public class CreateOrder {
    public static final String URL = "https://qa-scooter.praktikum-services.ru/";
    private WebDriver driver = null;
    private final String name;
    private final String secondName;
    private final String address;
    private final String phone;
    private final int afterDays;
    private final String color;
    private final String comment;



    public CreateOrder(String name, String secondName, String address, String phone, int afterDays, String color, String comment){
        this.name = name;
        this.secondName = secondName;
        this.address = address;
        this.phone = phone;
        this.afterDays = afterDays;
        this.color = color;
        this.comment = comment;

    }
    @Parameterized.Parameters
    public static Object[][] getOrderInformation() {
        return new Object[][]{
                {"Иван","Иванов","Мира 11","89995553322",3,"black","Test comment"},
                {"Петр","Петрович","Азина 26","89994441122",5,"grey","no comment"}
        };
    }

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
    public void createOrder(){
        HomePage objHomePage = new HomePage(driver);
        FormPagePersonalData objFormPersonalData = new FormPagePersonalData(driver);
        FormPageAboutOrder objPageAboutOrder = new FormPageAboutOrder(driver);
        PopConfirmOrder objPageConfirmOrder = new PopConfirmOrder(driver);
        PopOrderInformation objPageOrderInformation = new PopOrderInformation(driver);

        objHomePage.waitingLoadPage();
        objHomePage.clickCookieButton();

        objHomePage.clickHeaderButton();

        objFormPersonalData.waitingLoadPage();
        objFormPersonalData.setInputs(name, secondName, address, phone);
        objFormPersonalData.clickButtonNext();

        objPageAboutOrder.setInputs(afterDays, color, comment);
        objPageAboutOrder.clickOrderButton();

        objPageConfirmOrder.clickYes();
        Assert.assertTrue(objPageOrderInformation.getConfirmTitle().contains("Заказ оформлен"));
    }
}
