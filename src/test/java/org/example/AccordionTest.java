package org.example;

import org.junit.After;
import org.junit.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class AccordionTest {
    WebDriver driver;

    @Test
    public void checkPushButtonAccordion() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");


        MainPage objMainPage = new MainPage(driver);
        objMainPage.scrollToAccordionComponent();
        objMainPage.checkTextAccordionComponent();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}