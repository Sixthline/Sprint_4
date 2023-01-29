package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class OrderPageParametrisedTest {
    WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String phoneNumber;
    private final String period;
    private final String color;
    private final String comment;


    public OrderPageParametrisedTest(String name, String surname, String address,
                                     String phoneNumber, String period, String color,
                                     String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.period = period;
        this.color = color;
        this.comment = comment;

    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {"Сеня", "Иванов", "Москва", "89997777777", "двое суток", "серый", "коммент"},
                {"Лёня", "Петров", "Питер", "86661313131", "сутки", "черный", "поторапливайся"},
        };
    }

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void orderScooterTest() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickAcceptCookieButton();
        objMainPage.clickOrderButton();
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.inputName(name);
        objOrderPage.inputSurname(surname);
        objOrderPage.inputAddress(address);
        objOrderPage.inputMetro();
        objOrderPage.inputPhoneNumber(phoneNumber);
        objOrderPage.clickNextButton();
        objOrderPage.inputDate();
        objOrderPage.inputPeriod(period);
        objOrderPage.chooseColorScooter(color);
        objOrderPage.inputComment(comment);
        objOrderPage.clickToOrderButton();
        objOrderPage.clickYesButton();
        String actual = objOrderPage.getTextStatusOrder();
        String expected = "Заказ оформлен";
        assertEquals("Заказ не оформлен", expected, actual);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
