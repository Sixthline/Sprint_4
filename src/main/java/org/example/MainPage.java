package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    // Кнопка "Самокат" (шапка страницы)
    private final By scooterButton = By.xpath(".//img[@alt='Scooter']");
    // Изображение самоката на главной странице сервиса
    private final By scooterBlueprint = By.xpath(".//img[@alt='Scooter blueprint']");
    // Кнопка "Заказать" шапка страницы
    private final By orderButtonHeader = By.xpath(".//button[@class='Button_Button__ra12g']");
    // Кнопка "Заказать" в центре страницы
    private final By orderButtonCentrePage = By.xpath(".//button[@class='Button_Button__ra12g']");
    // Элемент аккордеон
    private final By accordionComponent = By.className("accordion");
    // Кнопка Куки
    private final By acceptCookieButton = By.xpath("//*[@id='rcc-confirm-button']");
    // Драйвер
    private final WebDriver driver;

    // Конструктор класса главной страницы
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Ждем загрузки главной страницы
    public void mainPageWait() {
        new WebDriverWait(driver, 3).until(driver -> (driver.findElement(scooterBlueprint) != null));
    }

    // Клик по кнопке "Заказать" (шапка страницы)
    public void clickOrderButtonHeader() {
        driver.findElement(orderButtonHeader).click();
    }

    // Клик по кнопке "Заказать" в центре страницы
    public void clickOrderButtonCentrePage() {
        driver.findElement(orderButtonCentrePage).click();
    }

    // Клик по конкретной кнопке "Заказать", в зависимости от принимаемого параметра
    public void clickOrderButton(String position) {
        if (position.equals("centre")) {
            scrollToOrderButtonCentrePage();
            clickOrderButtonCentrePage();
        } else {
            clickOrderButtonHeader();
        }
    }

    // Клик по кнопке "Принять куки"
    public void clickAcceptCookieButton() {
        driver.findElement(acceptCookieButton).click();
    }

    // Клик по кнопке "Самокат" (шапка страницы)
    public void clickScooterButton() {
        driver.findElement(scooterButton).click();
    }

    // Скролим до аккордиона в разделе «Вопросы о важном»
    public void scrollToAccordionComponent() {
        WebElement element = driver.findElement(accordionComponent);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    // Скролим до кнопки "Заказать" в центре страницы
    public void scrollToOrderButtonCentrePage() {
        WebElement element = driver.findElement(orderButtonCentrePage);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
}
