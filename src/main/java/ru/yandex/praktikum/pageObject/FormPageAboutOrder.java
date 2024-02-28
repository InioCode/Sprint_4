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
    private  final WebDriver driver;
    //Поле Когда привезти
    private final By inputDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Срок аренды
    private final By inputRental = By.xpath(".//div[text()='* Срок аренды']");
        //Выпадающий список с колечиством дней аренды
    private final By rentalPeriod = By.xpath(".//div[text()='двое суток']");
    //чекбоксы
    private final By blackCheckBox = By.id("black");
    private final By greyCheckBox = By.id("grey");
    //Поле Комментарий
    private final By inputComment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Кнопка Заказать
    private final By buttonOrder = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    private final Date dateNow = new Date();
    public FormPageAboutOrder(WebDriver driver){
        this.driver = driver;
    }

    public void setDate(int orderAfter){
        if (orderAfter < 0){
            System.out.println("Число поля Когда привезти не может быть отрицательным");
        } else if (orderAfter == 0) {
            System.out.println("Число поля Когда привезти не может быть 0, привезти самокат можно только с завтрашнего дня");
        } else {
            SimpleDateFormat formatDateNow = new SimpleDateFormat("dd.MM.yyyy");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateNow);
            calendar.add(Calendar.DATE, orderAfter);
            String orderDay = formatDateNow.format(calendar.getTime());
            driver.findElement(inputDate).sendKeys(orderDay);
            driver.findElement(inputDate).sendKeys(Keys.RETURN);
        }
    }

    public void setRental(){
        driver.findElement(inputRental).click();
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(rentalPeriod));
        driver.findElement(rentalPeriod).click();
    }

    public void setColor(String color){
        if (color.equalsIgnoreCase("black")){
            driver.findElement(blackCheckBox).click();
        } else if (color.equalsIgnoreCase("grey")) {
            driver.findElement(greyCheckBox).click();
        } else {System.out.println("Цвет" + color + "не найден");}
    }

    public void setComment(String comment){
        driver.findElement(inputComment).sendKeys(comment);
    }

    public void setInputs(int afterDays, String color, String comment){
        setDate(afterDays);
        setRental();
        setColor(color);
        setComment(comment);
    }
    public void clickOrderButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(buttonOrder));
        driver.findElement(buttonOrder).click();
    }
}
