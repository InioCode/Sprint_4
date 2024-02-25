package ru.yandex.praktikum.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

public class FormPageAboutOrder {
    private final WebDriver DRIVER;
    //Поле Когда привезти
    private final By INPUT_DATE = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Срок аренды
    private final By INPUT_RENTAL = By.xpath(".//div[text()='* Срок аренды']");
        //Выпадающий список с колечиством дней аренды
    private final By RENTAL_PERIOD = By.xpath(".//div[text()='двое суток']");
    //чекбоксы
    private final By BLACK_CHECK_BOX = By.id("black");
    private final By GREY_CHECK_BOX = By.id("grey");
    //Поле Комментарий
    private final By INPUT_COMMENT = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Кнопка Заказать
    private final By BUTTON_ORDER = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    private final Date DATE_NOW = new Date();
    public FormPageAboutOrder(WebDriver driver){
        this.DRIVER = driver;
    }

    public void setDate(int orderAfter){
        if (orderAfter < 0){
            System.out.println("Число поля Когда привезти не может быть отрицательным");
        } else if (orderAfter == 0) {
            System.out.println("Число поля Когда привезти не может быть 0, привезти самокат можно только с завтрашнего дня");
        } else {
            SimpleDateFormat formatDateNow = new SimpleDateFormat("dd.MM.yyyy");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(DATE_NOW);
            calendar.add(Calendar.DATE, orderAfter);
            String orderDay = formatDateNow.format(calendar.getTime());
            DRIVER.findElement(INPUT_DATE).sendKeys(orderDay);
            DRIVER.findElement(INPUT_DATE).sendKeys(Keys.RETURN);
        }
    }

    public void setRental(){
        DRIVER.findElement(INPUT_RENTAL).click();
        new WebDriverWait(DRIVER, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(RENTAL_PERIOD));
        DRIVER.findElement(RENTAL_PERIOD).click();
    }

    public void setColor(String color){
        if (color.equalsIgnoreCase("black")){
            DRIVER.findElement(BLACK_CHECK_BOX).click();
        } else if (color.equalsIgnoreCase("grey")) {
            DRIVER.findElement(GREY_CHECK_BOX).click();
        } else {System.out.println("Цвет" + color + "не найден");}
    }

    public void setComment(String comment){
        DRIVER.findElement(INPUT_COMMENT).sendKeys(comment);
    }

    public void setInputs(int afterDays, String color, String comment){
        setDate(afterDays);
        setRental();
        setColor(color);
        setComment(comment);
    }
    public void clickOrderButton(){
        new WebDriverWait(DRIVER, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(BUTTON_ORDER));
        DRIVER.findElement(BUTTON_ORDER).click();
    }
}
