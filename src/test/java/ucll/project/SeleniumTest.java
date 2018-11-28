package ucll.project;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class SeleniumTest {

    public static WebDriver driver;

    @BeforeClass
    public static void SetupChromeDriver(){
        // Setup the Chrome driver for the whole class
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void CloseChrome(){
        // close it in the end, comment this away to keep chrome open
        driver.close();
    }

    @Test
    public void SeleniumExample() {
        // Visit the home page of your application
        driver.get("http://localhost:8080/");

        // There should be the string "Hello world!" somewhere in the html
        assertTrue(driver.getPageSource().toString().contains("Hello world!"));
    }

    @Test
    public void VisitHomePageTest() {
        driver.get("http://localhost:8080/");

        WebElement header = driver.findElement(By.id("pageTitle"));
        assertTrue(header.getText().equals("Hello world!"));
    }

    @Test
    public void visitLoginPageFromHomePageTest(){
        driver.get("http://localhost:8080/");

        WebElement loginLink = driver.findElement(By.id("loginLink"));
        loginLink.click();

        WebElement header = driver.findElement(By.id("pageTitle"));
        assertTrue(header.getText().equals("Please sign in"));
    }


}
