package ru.yandex.praktikum.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PopOrderInformation {
    private final WebDriver DRIVER;
    //текст с информацией о заказе
    private final By CONFIRM_ORDER_TITLE = By.xpath(".//*[text()='Заказ оформлен']");

    public PopOrderInformation (WebDriver driver){
        this.DRIVER = driver;
    }

    public String getConfirmTitle(){
        new WebDriverWait(DRIVER, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CONFIRM_ORDER_TITLE));
        return DRIVER.findElement(CONFIRM_ORDER_TITLE).getText();
    }
}
