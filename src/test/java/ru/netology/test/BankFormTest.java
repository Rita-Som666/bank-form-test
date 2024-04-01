package ru.netology.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;


public class BankFormTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldSuccessSendForm() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] .input__control")).sendKeys("Иван Иванов");
        driver.findElement(By.cssSelector("[data-test-id='phone'] .input__control")).sendKeys("+79160000000");
        driver.findElement(By.cssSelector("[data-test-id=\"agreement\"]")).click();
        driver.findElement(By.className("button_view_extra")).click();
        String text = driver.findElement(By.cssSelector
                ("[data-test-id=\"order-success\"]")).getText();
        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.",
                text.trim());
    }
    @Test
            void FieldNamePositiveValidation1() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] .input__control")).sendKeys("Иван-Иванов");
        driver.findElement(By.cssSelector("[data-test-id='phone'] .input__control")).sendKeys("+79160000000");
        driver.findElement(By.cssSelector("[data-test-id=\"agreement\"]")).click();
        driver.findElement(By.className("button_view_extra")).click();
        String text = driver.findElement(By.cssSelector
                ("[data-test-id=\"order-success\"]")).getText();
        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.",
                text.trim());
    }
    @Test
    void FieldNamePositiveValidation2() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] .input__control")).sendKeys("Иван");
        driver.findElement(By.cssSelector("[data-test-id='phone'] .input__control")).sendKeys("+79160000000");
        driver.findElement(By.cssSelector("[data-test-id=\"agreement\"]")).click();
        driver.findElement(By.className("button_view_extra")).click();
        String text = driver.findElement(By.cssSelector
                ("[data-test-id=\"order-success\"]")).getText();
        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.",
                text.trim());
    }

    @Test
    void FieldNamePositiveValidation3(){
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] .input__control")).sendKeys("Иван-Иванов Иванович");
        driver.findElement(By.cssSelector("[data-test-id='phone'] .input__control")).sendKeys("+79160000000");
        driver.findElement(By.cssSelector("[data-test-id=\"agreement\"]")).click();
        driver.findElement(By.className("button_view_extra")).click();
        String text = driver.findElement(By.cssSelector
                ("[data-test-id=\"order-success\"]")).getText();
        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.",
                text.trim());

    }

    @Test
    void fieldNameNegativeValidation() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] .input__control")).sendKeys("Ivan Ivanov");
        driver.findElement(By.className("button_view_extra")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText();
        Assertions.assertEquals
                ("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.",
                        text.trim());
    }

    @Test
    void fieldNameNegativeValidation2() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] .input__control")).sendKeys("Иван1");
        driver.findElement(By.className("button_view_extra")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText();
        Assertions.assertEquals
                ("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.",
                        text.trim());
    }
    @Test
    void fieldNameNegativeValidation3() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] .input__control")).sendKeys("Иван.Иванов");
        driver.findElement(By.className("button_view_extra")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText();
        Assertions.assertEquals
                ("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.",
                        text.trim());
    }

    @Test
    void fieldNameNegativeValidation4(){
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] .input__control")).sendKeys("");
        driver.findElement(By.className("button_view_extra")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText();
        Assertions.assertEquals
                ("Поле обязательно для заполнения",
                        text.trim());
        driver.navigate().refresh();

    }

    @Test
    void fieldPhoneNegativeValidation() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] .input__control")).sendKeys("Иван Иванов");
        driver.findElement(By.cssSelector("[data-test-id='phone'] .input__control")).sendKeys("+800000000000");
        driver.findElement(By.className("button_view_extra")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();
        Assertions.assertEquals
                ("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.",
                        text.trim());
        }
    @Test
    void fieldPhoneNegativeValidation2() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] .input__control")).sendKeys("Иван Иванов");
        driver.findElement(By.cssSelector("[data-test-id='phone'] .input__control")).sendKeys("+8000000000");
        driver.findElement(By.className("button_view_extra")).click();

        String text = driver.findElement(By.cssSelector(".input_invalid[data-test-id='phone'] .input__sub")).getText();
        Assertions.assertEquals
                ("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.",
                        text.trim());
    }
    @Test
    void fieldPhoneNegativeValidation3() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] .input__control")).sendKeys("Иван Иванов");
        driver.findElement(By.cssSelector("[data-test-id='phone'] .input__control")).sendKeys("80000000000");
        driver.findElement(By.className("button_view_extra")).click();

        String text = driver.findElement(By.cssSelector(".input_invalid[data-test-id='phone'] .input__sub")).getText();
        Assertions.assertEquals
                ("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.",
                        text.trim());
        driver.navigate().refresh();
    }

    @Test
    void notShouldSuccessSendFormWithoutAgreement(){
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] .input__control")).sendKeys("Иван Иванов");
        driver.findElement(By.cssSelector("[data-test-id='phone'] .input__control")).sendKeys("+79160000000");
        driver.findElement(By.className("button_view_extra")).click();
        driver.findElement(By.cssSelector(".input_invalid[data-test-id=\"agreement\"]"));
    }
}
