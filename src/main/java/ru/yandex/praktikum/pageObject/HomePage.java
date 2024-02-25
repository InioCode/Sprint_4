package ru.yandex.praktikum.pageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class HomePage {
    private final WebDriver DRIVER;
    //Элементы списка (AccordionItem)
    private final By LIST_ELEMENTS = By.className("accordion__button");//Выдается 8 шт
    //Текст внутри элемента списка
    private final By ANSWER_TEXT = By.xpath("(.//*[@class='accordion__item']/div/p)");//Выдается 8 шт
    //Кнопка Заказать вверху страницы
    private final By ORDER_BUTTON_IN_HEADER = By.className("Button_Button__ra12g");
    //Кнопка Заказать внизу страницы
    private final By ORDER_BUTTON_IN_MIDDLE = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //Подтверждение Cookie
    private final By BUTTON_COOKIE = By.id("rcc-confirm-button");
    public HomePage(WebDriver driver){
        this.DRIVER = driver;
    }

    public void waitingLoadPage(){
        DRIVER.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void clickListElement(int numberElement){
        List<WebElement> elements = DRIVER.findElements(LIST_ELEMENTS);
        WebElement element = elements.get(numberElement);
        ((JavascriptExecutor) DRIVER).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    public String getAnswerText(int numberElement){
        List<WebElement> elements = DRIVER.findElements(ANSWER_TEXT);
        WebElement element = elements.get(numberElement);
        ((JavascriptExecutor) DRIVER).executeScript("arguments[0].scrollIntoView();", element);
        return element.getText();
    }

    public void clickHeaderButton(){
        DRIVER.findElement(ORDER_BUTTON_IN_HEADER).click();
    }

    public void clickMiddleButton(){
        DRIVER.findElement(ORDER_BUTTON_IN_MIDDLE).click();
    }

    public void clickCookieButton(){
        DRIVER.findElement(BUTTON_COOKIE).click();
    }

}
