package ru.yandex.praktikum.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class FormPagePersonalData {
    private final WebDriver driver;
    //Поле Имя
    private final By inputName = By.xpath(".//input[@placeholder='* Имя']");
    //Поля Фамилия
    private final By inputSecondName = By.xpath(".//input[@placeholder='* Фамилия']");
    //Поле Адрес
    private final By inputAddress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Поле Станция метро
    private final By inputMetroStation = By.xpath(".//input[@placeholder='* Станция метро']");
    //Станция из выпадающего списка
    private final By metroStation = By.xpath(".//div[text()='Черкизовская']");
    private final By listMetroStation = By.xpath(".//div[@class='select-search__select']");
    //Поле Телефон
    private final By inputPhone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //Кнопка Далее
    private final By buttonNext = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public FormPagePersonalData(WebDriver driver){
        this.driver = driver;
    }

    public void waitingLoadPage(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void setName(String name){
        driver.findElement(inputName).sendKeys(name);
    }

    public String getPlaceholderName(){
        return driver.findElement(inputName).getAttribute("placeholder");
    }

    public void setSecondName(String secondName){
        driver.findElement(inputSecondName).sendKeys(secondName);
    }

    public void setAddress(String address){
        driver.findElement(inputAddress).sendKeys(address);
    }

    public void setMetroStation(){ //сделать выбор определенного элемента
        driver.findElement(inputMetroStation).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(listMetroStation));
        driver.findElement(metroStation).click();
    }

    public void setPhone(String phone){
        driver.findElement(inputPhone).sendKeys(phone);
    }

    public void clickButtonNext(){
        driver.findElement(buttonNext).click();
    }

    public void setInputs(String name, String secondName,String address,String phone){
        setName(name);
        setSecondName(secondName);
        setAddress(address);
        setMetroStation();
        setPhone(phone);
    }

}
