package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.security.Key;
import java.util.List;
import java.util.regex.Matcher;

public class GoogleTest {

    @Test
    public void searchTest() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://google.com");

        String searchItem = "apple";

        driver.findElement(By.name("q")).sendKeys(searchItem, Keys.ENTER);
        List<WebElement> headings = driver.findElements(By.tagName("h3"));
        //remove blank results
        headings.removeIf(heading -> heading.getText().isEmpty());

        headings.forEach(heading -> {
            System.out.println("Found heading: " + heading.getText());
            Assertions.assertTrue(heading.getText().contains(searchItem));
        });
        driver.quit();
    }
}
