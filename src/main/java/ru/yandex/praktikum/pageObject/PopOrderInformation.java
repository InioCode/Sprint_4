package ru.yandex.praktikum.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PopOrderInformation {
    private final WebDriver driver;
    //текст с информацией о заказе
    private final By confirmOrderTitle = By.xpath(".//*[text()='Заказ оформлен']");

    public PopOrderInformation (WebDriver driver){
        this.driver = driver;
    }

    public String getConfirmTitle(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(confirmOrderTitle));
        return driver.findElement(confirmOrderTitle).getText();
    }
}
