package ru.yandex.praktikum.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class FormPagePersonalData {
    private final WebDriver DRIVER;
    //Поле Имя
    private final By INPUT_NAME = By.xpath(".//input[@placeholder='* Имя']");
    //Поля Фамилия
    private final By INPUT_SECOND_NAME = By.xpath(".//input[@placeholder='* Фамилия']");
    //Поле Адрес
    private final By INPUT_ADDRESS = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Поле Станция метро
    private final By INPUT_METRO_STATION = By.xpath(".//input[@placeholder='* Станция метро']");
    //Станция из выпадающего списка
    private final By METRO_STATION = By.xpath(".//div[text()='Черкизовская']");
    private final By LIST_METRO_STATION = By.xpath(".//div[@class='select-search__select']");
    //Поле Телефон
    private final By INPUT_PHONE = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //Кнопка Далее
    private final By BUTTON_NEXT = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public FormPagePersonalData(WebDriver driver){
        this.DRIVER = driver;
    }

    public void waitingLoadPage(){
        DRIVER.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void setName(String name){
        DRIVER.findElement(INPUT_NAME).sendKeys(name);
    }

    public void setSecondName(String secondName){
        DRIVER.findElement(INPUT_SECOND_NAME).sendKeys(secondName);
    }

    public void setAddress(String address){
        DRIVER.findElement(INPUT_ADDRESS).sendKeys(address);
    }

    public void setMetroStation(){ //сделать выбор определенного элемента
        DRIVER.findElement(INPUT_METRO_STATION).click();
        new WebDriverWait(DRIVER, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(LIST_METRO_STATION));
        DRIVER.findElement(METRO_STATION).click();
    }

    public void setPhone(String phone){
        DRIVER.findElement(INPUT_PHONE).sendKeys(phone);
    }

    public void clickButtonNext(){
        DRIVER.findElement(BUTTON_NEXT).click();
    }

    public void setInputs(String name, String secondName,String address,String phone){
        setName(name);
        setSecondName(secondName);
        setAddress(address);
        setMetroStation();
        setPhone(phone);
    }

}
