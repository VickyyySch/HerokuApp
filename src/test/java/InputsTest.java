import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class InputsTest {
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
    public void inputNumericValueArrowUp() {
        driver.get("http://the-internet.herokuapp.com/inputs");
        WebElement inputField = driver.findElement(By.tagName("input"));

        inputField.clear();
        inputField.sendKeys("123");
        inputField.sendKeys(Keys.ARROW_UP);
        String updatedValue = inputField.getAttribute("value");
        Assert.assertEquals(updatedValue, "124", "Updated value after pressing Arrow Up");
    }

    @Test
    public void inputNumericValueArrowDown() {
        driver.get("http://the-internet.herokuapp.com/inputs");
        WebElement inputField = driver.findElement(By.tagName("input"));
        inputField.clear();
        inputField.sendKeys("123");
        inputField.sendKeys(Keys.ARROW_DOWN);
        String updatedValue = inputField.getAttribute("value");
        Assert.assertEquals(updatedValue, "122", "Updated value after pressing Arrow Down");
    }

    @Test
    public void inputNonNumericValueArrowDown() {
        driver.get("http://the-internet.herokuapp.com/inputs");
        WebElement inputField = driver.findElement(By.tagName("input"));
        inputField.clear();
        inputField.sendKeys("abc");
        Assert.assertEquals(inputField.getAttribute("value"), "");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}