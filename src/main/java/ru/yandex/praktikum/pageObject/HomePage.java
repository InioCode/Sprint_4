package ru.yandex.praktikum.pageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class HomePage {
    private final WebDriver driver;
    //Элементы списка (AccordionItem)
    private final By listElements = By.className("accordion__button");//Выдается 8 шт
    //Текст внутри элемента списка
    private final By answerText = By.xpath("(.//*[@class='accordion__item']/div/p)");//Выдается 8 шт
    //Кнопка Заказать вверху страницы
    private final By orderButtonInHeader = By.className("Button_Button__ra12g");
    //Кнопка Заказать внизу страницы
    private final By orderButtonInMiddle = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //Подтверждение Cookie
    private final By buttonCookie = By.id("rcc-confirm-button");
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void waitingLoadPage(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void clickListElement(int numberElement){
        List<WebElement> elements = driver.findElements(listElements);
        WebElement element = elements.get(numberElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    public String getAnswerText(int numberElement){
        List<WebElement> elements = driver.findElements(answerText);
        WebElement element = elements.get(numberElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return element.getText();
    }

    public void clickHeaderButton(){
        driver.findElement(orderButtonInHeader).click();
    }

    public void clickMiddleButton(){
        driver.findElement(orderButtonInMiddle).click();
    }

    public void clickCookieButton(){
        driver.findElement(buttonCookie).click();
    }

}
