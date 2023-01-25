package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.*;

public class MainPage {
    private final WebDriver driver;
    private final By accordionComponent = By.className("accordion");
    private final By yesCookieButton = By.xpath("//*[@id='rcc-confirm-button']");
    private final String[] array = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. " +
                    "Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. " +
                    "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. " +
                    "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. " +
                    "Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. " +
                    "Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Скролим до аккордиона раздела «Вопросы о важном»
    public void scrollToAccordionComponent() {
        WebElement element = driver.findElement(accordionComponent);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    // Проверка текста при нажатии на стрелочку в выпадающем списке раздела «Вопросы о важном».
    public void checkTextAccordionComponent() {
        driver.findElement(yesCookieButton).click();
        for (int i = 0; i < 8; i++) {
            By elementAccordionButton = By.xpath("//*[@id='accordion__heading-" + i + "']");
            driver.findElement(elementAccordionButton).click();
            By descriptionElementAccordionButton = By.xpath("//*[@id='accordion__panel-" + i + "']/p");
            String result = driver.findElement(descriptionElementAccordionButton).getText();
            assertEquals("Комментарий на вкладке " + i + " не соответствует требованиям", array[i], result);
        }
    }
}
