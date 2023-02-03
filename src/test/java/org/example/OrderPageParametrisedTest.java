package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderPageParametrisedTest {
    private WebDriver driver;
    private final String position;
    private final String name;
    private final String surname;
    private final String address;
    private final String phoneNumber;
    private final String period;
    private final String color;
    private final String comment;

    public OrderPageParametrisedTest(String position, String name, String surname,
                                     String address, String phoneNumber, String period,
                                     String color, String comment) {
        this.position = position;
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
                {"centre", "Сеня", "Иванов", "Москва", "89997777777", "двое суток", "серый", "коммент"},
                {"header", "Лёня", "Петров", "Питер", "86661313131", "сутки", "черный", "поторапливайся"},
        };
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void orderScooterTest() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickAcceptCookieButton();
        objMainPage.clickOrderButton(position);
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.fillPersonalDataForm(name, surname, address, phoneNumber);
        objOrderPage.clickNextButton();
        objOrderPage.fillDetailsOrderForm(period, color, comment);
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
