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

    @Test
    public void LoginTestExpectSuccess(){
        driver.get("http://localhost:8080/user/login");
        WebElement userNameInput = driver.findElement(By.id("usernameInput"));
        userNameInput.sendKeys("user");
        WebElement passwordInput = driver.findElement(By.id("passwordInput"));
        passwordInput.sendKeys("user");
        WebElement loginButton = driver.findElement(By.id("loginButton"));
        loginButton.click();

        WebElement pageTitle = driver.findElement(By.id("pageTitle"));
        assertTrue(pageTitle.getText().equals("Hello world!"));
    }

    @Test
    public void loginTestWithoutUsernameExpectFailure(){
        driver.get("http://localhost:8080/user/login");
        WebElement passwordInput = driver.findElement(By.id("passwordInput"));
        passwordInput.sendKeys("user");
        WebElement loginButton = driver.findElement(By.id("loginButton"));
        loginButton.click();

        WebElement pageTitle = driver.findElement(By.id("pageTitle"));
        assertTrue(pageTitle.getText().equals("Please sign in"));
    }

    @Test
    public void loginTestWithInvalidUsernameExpectFailure(){
        driver.get("http://localhost:8080/user/login");

        WebElement userNameInput = driver.findElement(By.id("usernameInput"));
        userNameInput.sendKeys("qwe");
        WebElement passwordInput = driver.findElement(By.id("passwordInput"));
        passwordInput.sendKeys("user");
        WebElement loginButton = driver.findElement(By.id("loginButton"));
        loginButton.click();

        WebElement pageTitle = driver.findElement(By.id("pageTitle"));
        assertTrue(pageTitle.getText().equals("Please sign in"));

        WebElement errorMessage = driver.findElement(By.id("errorMessage"));
        assertTrue(errorMessage.getText().equals("Invalid username"));
    }

    @Test
    public void loginTestWithoutPasswordExpectFailure(){
        driver.get("http://localhost:8080/user/login");
        WebElement userNameInput = driver.findElement(By.id("usernameInput"));
        userNameInput.sendKeys("user");
        WebElement loginButton = driver.findElement(By.id("loginButton"));
        loginButton.click();

        WebElement pageTitle = driver.findElement(By.id("pageTitle"));
        assertTrue(pageTitle.getText().equals("Please sign in"));
    }

    @Test
    public void loginTestWithInvalidPasswordExpectFailure(){
        driver.get("http://localhost:8080/user/login");
        WebElement userNameInput = driver.findElement(By.id("usernameInput"));
        userNameInput.sendKeys("user");
        WebElement passwordInput = driver.findElement(By.id("passwordInput"));
        passwordInput.sendKeys("qwe");
        WebElement loginButton = driver.findElement(By.id("loginButton"));
        loginButton.click();

        WebElement pageTitle = driver.findElement(By.id("pageTitle"));
        assertTrue(pageTitle.getText().equals("Please sign in"));

        WebElement errorMessage = driver.findElement(By.id("errorMessage"));
        assertTrue(errorMessage.getText().equals("Invalid password"));
    }


}
