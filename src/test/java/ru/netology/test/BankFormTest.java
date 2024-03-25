package ru.netology.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.List;


public class BankFormTest {
    private WebDriver driver;
    @BeforeAll
            static void setUpAll(){
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
            void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown(){
        driver.quit();
        driver = null;
    }

    @Test
    void shouldLoad(){
        driver.get("http://localhost:9999/");
        WebElement form = driver.findElement(By.cssSelector
                ("[class=\"form form_size_m form_theme_alfa-on-white\"]"));
        List<WebElement> elements = driver.findElements(By.className("input_view_default"));
    }
}
