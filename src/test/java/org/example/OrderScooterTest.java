package org.example;
import org.junit.After;
import org.junit.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OrderScooterTest {
    WebDriver driver;

    @Test
    public void orderScooterTest (){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");

        MainPage objMainPage = new MainPage(driver);
        objMainPage.orderButtonClick();
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.waitForOrderPage();
        objOrderPage.fillForm("Иван", "Иванов", "Москва", "Сокольники", "89997777777");



    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
