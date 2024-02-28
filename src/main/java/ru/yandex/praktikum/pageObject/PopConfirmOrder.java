package ru.yandex.praktikum.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PopConfirmOrder {
    private final WebDriver driver;
    //Кнопка Да
    private final By buttonYes = By.xpath(".//button[text()='Да']");

    public PopConfirmOrder(WebDriver driver){
        this.driver = driver;
    }
    public void  clickYes(){
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOfElementLocated(buttonYes));
        driver.findElement(buttonYes).click();
    }

}
