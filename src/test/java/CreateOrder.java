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
    private WebDriver driver = null;
    private final String NAME;
    private final String SECOND_NAME;
    private final String ADDRESS;
    private final String PHONE;
    private final int AFTER_DAYS;
    private final String COLOR;
    private final String COMMENT;
    private final String ORDER_BUTTON;



    public CreateOrder(String orderButton, String name, String secondName, String address, String phone, int afterDays, String color, String comment){
        this.ORDER_BUTTON = orderButton;
        this.NAME = name;
        this.SECOND_NAME = secondName;
        this.ADDRESS = address;
        this.PHONE = phone;
        this.AFTER_DAYS = afterDays;
        this.COLOR = color;
        this.COMMENT = comment;

    }
    @Parameterized.Parameters
    public static Object[][] getOrderInformation() {
        return new Object[][]{
                {"header", "Иван","Иванов","Мира 11","89995553322",3,"black","Test comment"},
                {"middle", "Петр","Петрович","Азина 26","89994441122",5,"grey","no comment"}
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
        driver.get("https://qa-scooter.praktikum-services.ru/");
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

        if (ORDER_BUTTON.equalsIgnoreCase("header")){
            objHomePage.clickHeaderButton();
        } else if (ORDER_BUTTON.equalsIgnoreCase("middle")) {
            objHomePage.clickMiddleButton();
        } else {
            System.out.println("Кнопка не найдена");
        }

        objFormPersonalData.waitingLoadPage();
        objFormPersonalData.setInputs(NAME,SECOND_NAME,ADDRESS,PHONE);
        objFormPersonalData.clickButtonNext();

        objPageAboutOrder.setInputs(AFTER_DAYS,COLOR, COMMENT);
        objPageAboutOrder.clickOrderButton();

        objPageConfirmOrder.clickYes();
        Assert.assertTrue(objPageOrderInformation.getConfirmTitle().contains("Заказ оформлен"));
    }
}
