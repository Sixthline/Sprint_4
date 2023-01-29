package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderPage {
    // driver
    private final WebDriver driver;
    // Поле ввода имени
    private final By nameInputField = By.xpath("//input[@placeholder='* Имя']");
    // Поле ввода фамилии
    private final By surnameInputField = By.xpath("//input[@placeholder='* Фамилия']");
    // Поле ввода адреса
    private final By addressInputField = By.xpath("//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='* Адрес: куда привезти заказ']");
    // Поле ввода станции метро
    private final By metroInputField = By.className("select-search__input");
    // Скрытый баян со списком станций метро
    private final By hiddenListMetro = By.xpath(".//div[@class='select-search__select']");
    // Поле ввода телефона
    private final By phoneNumberInputField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка "Далее"
    private final By nextButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Далее']");
    // Поле ввода даты доставки самоката
    private final By dateInputField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    // Кнопка разворачивающая список с вариантами выбора срока
    private final By rentalPeriodInputField = By.xpath("//span[@class='Dropdown-arrow']");
    // Чекбокс выбора черного цвета самоката
    private final By colorBlackInput = By.xpath("//input[@id='black']");
    // Чекбокс выбора серого цвета самоката
    private final By colorGreyInput = By.xpath("//input[@id='grey']");
    // Поле ввода комментария для курьера
    private final By commentInputField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    // Кнопка "Заказать"
    private final By toOrderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    // Кнопка подтверждения заказа "Да"
    private final By yesButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    // Окно "Заказ оформлен"
    private final By orderIsProcessed = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Заполнить поле "Имя"
    public void inputName(String name) {
        driver.findElement(nameInputField).sendKeys(name);
    }

    // Заполнить поле "Фамилия"
    public void inputSurname(String surname) {
        driver.findElement(surnameInputField).sendKeys(surname);
    }

    // Заполнить поле "Адресс"
    public void inputAddress(String address) {
        driver.findElement(addressInputField).sendKeys(address);
    }

    // Заполнить поле "Станция метро"
    public void inputMetro() {
        driver.findElement(metroInputField).click();
        driver.findElement(hiddenListMetro).click();
    }

    // Заполнить поле "Номер телефона"
    public void inputPhoneNumber(String number) {
        driver.findElement(phoneNumberInputField).sendKeys(number);
    }

    // Клик по кнопке "Далее"
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    // Заполнить поле "Дата доставки"
    public void inputDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date today = new Date();
        String date = formatter.format(today);
        driver.findElement(dateInputField).sendKeys(date);
    }

    // Выбрать срок аренды
    public void inputPeriod(String period) {
        driver.findElement(rentalPeriodInputField).click();
        driver.findElement(By.xpath("//div[@class='Dropdown-option' and text()='" + period + "']")).click();
    }

    // Выбрать цвет самоката
    public void chooseColorScooter(String color) {
        if (color.equals("серый")) {
            driver.findElement(colorGreyInput).click();
        } else {
            driver.findElement(colorBlackInput).click();
        }
    }

    // Ввести комментарий
    public void inputComment(String comment) {
        driver.findElement(commentInputField).sendKeys(comment);
    }

    // Клик по кнопке "Заказать" в конце формы
    public void clickToOrderButton() {
        driver.findElement(toOrderButton).click();
    }

    // Клик по кнопке "Да" - потвердить заказ
    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }

    // Получить текст сообщения о статусе заказа
    public String getTextStatusOrder() {
        return driver.findElement(orderIsProcessed).getText();
    }
}
