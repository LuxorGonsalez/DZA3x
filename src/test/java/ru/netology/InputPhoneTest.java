package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputPhoneTest {
    private WebDriver driver;
    private static ChromeOptions options;

    @BeforeAll
    static void setUpAll() {
        options = new ChromeOptions();
        options.addArguments("--headless");
        System.setProperty("webdriver.chrome.driver", "driver/linux/chromedriver");
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void testNegativePhone10Numbers() {
        driver.get("http://localhost:9999");
        WebElement form = driver.findElement(By.className("form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Александр Пушкин");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("7987000000");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=phone] span.input__sub")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    void testNegativePhone12Numbers() {
        driver.get("http://localhost:9999");
        WebElement form = driver.findElement(By.className("form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Александр Пушкин");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("798700000077");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=phone] span.input__sub")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    void testNegativePhoneCyrillicSymbol() {
        driver.get("http://localhost:9999");
        WebElement form = driver.findElement(By.className("form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Александр Пушкин");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("Футбол");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=phone] span.input__sub")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    void testNegativePhoneLatinSymbol() {
        driver.get("http://localhost:9999");
        WebElement form = driver.findElement(By.className("form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Александр Пушкин");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("HelloWorld");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=phone] span.input__sub")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    void testNegativePhonePlusInside() {
        driver.get("http://localhost:9999");
        WebElement form = driver.findElement(By.className("form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Александр Пушкин");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("7?9012345678");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=phone] span.input__sub")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }
}