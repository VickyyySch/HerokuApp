import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CheckboxesTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void checkboxes() {
        driver.get("http://the-internet.herokuapp.com/checkboxes");
        WebElement firstCheckbox = driver.findElement(By.cssSelector("[type=checkbox]:first-of-type"));
        WebElement secondCheckbox = driver.findElement(By.cssSelector("[type=checkbox]:nth-of-type(2)"));
        Assert.assertTrue(firstCheckbox.isDisplayed());
        Assert.assertTrue(secondCheckbox.isDisplayed());
    }

    @Test
    public void checkbox1() {
        driver.get("http://the-internet.herokuapp.com/checkboxes");

        WebElement firstCheckbox = driver.findElement(By.cssSelector("[type=checkbox]:first-of-type"));
        driver.findElements(By.cssSelector("input:not(:checked)[type='checkbox']"));
        firstCheckbox.click();
        Assert.assertTrue(firstCheckbox.isSelected());

    }

    @Test
    public void checkbox2() {
        driver.get("http://the-internet.herokuapp.com/checkboxes");

        WebElement secondCheckbox = driver.findElement(By.cssSelector("[type=checkbox]:nth-of-type(2)"));
        driver.findElements(By.cssSelector("input:checked[type='checkbox']"));
        secondCheckbox.click();
        Assert.assertFalse(secondCheckbox.isSelected());

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}