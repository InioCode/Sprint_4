package ru.yandex.praktikum.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PopConfirmOrder {
    private final WebDriver DRIVER;
    //Кнопка Да
    private final By BUTTON_YES = By.xpath(".//button[text()='Да']");

    public PopConfirmOrder(WebDriver driver){
        this.DRIVER = driver;
    }
    public void  clickYes(){
        new WebDriverWait(DRIVER, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOfElementLocated(BUTTON_YES));
        DRIVER.findElement(BUTTON_YES).click();
    }

}
