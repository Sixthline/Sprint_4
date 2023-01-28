package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    // Добавили поле driver
    private final WebDriver driver;
    // Форма "Личные данные"
    private final By personalDataForm = By.xpath(".//div[@class='Order_Form__17u6u']");
    // Поле ввода имени
    private final By nameInputField = By.xpath("//input[@placeholder='* Имя']");
    // Поле ввода фамилии
    private final By surnameInputField = By.xpath("//input[@placeholder='* Фамилия']");
    // Поле ввода адреса
    private final By addressInputField = By.xpath("//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='* Адрес: куда привезти заказ']");
    // Поле для выбора станции метро
    private final By metroInputField = By.className("select-search__input");
    // Скрытый баян
    private final By hiddenListMetro = By.xpath(".//div[@class='select-search__select']");
    // Поле ввода телефона
    private final By phoneNumberInputField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка "Далее"
    private final By nextButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Далее']");
    // Поле ввода даты доставки самоката
    private final By dateInputField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    // Поле выбора срока аренды
    private final By rentalPeriodInputField = By.className("Dropdown-placeholder");
    // Поле выбора цвета самоката
    private final By colorInputField = By.xpath("//input[@id='black']");
    // Поле ввода комментария для курьера
    private final By commentInputField = By.linkText("Да");
    // Кнопка "Заказать"
    private final By toOrderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillForm(String name, String surname, String address, String metro, String number) {
        driver.findElement(nameInputField).sendKeys(name);
        driver.findElement(surnameInputField).sendKeys(surname);
        driver.findElement(addressInputField).sendKeys(address);
        driver.findElement(metroInputField).sendKeys(metro);
        driver.findElement(hiddenListMetro).click();
        driver.findElement(phoneNumberInputField).sendKeys(number);
        driver.findElement(nextButton).click();
    }

    // метод ожидания прогрузки данных профиля
    public void waitForOrderPage() {
        new WebDriverWait(driver, 3)
                .until(driver -> (driver.findElement(personalDataForm) != null
                ));
    }
}
