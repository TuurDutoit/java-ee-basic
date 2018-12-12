package ucll.project.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ucll.project.ui.pages.HomePage;
import ucll.project.ui.pages.LoginPage;

import static org.junit.Assert.assertEquals;

public class LoginPageTest {

    private static WebDriver driver;

    @BeforeClass
    public static void SetupChromeDriver(){
        // Setup the Chrome driver for the whole class
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless","--no-sandbox");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void CloseChrome(){
        // close it in the end, comment this away to keep chrome open
        driver.close();
    }

    @Test
    public void LoginTestExpectSuccessV1(){

        driver.get("http://localhost:8080/user/login");
        driver.findElement(By.id("usernameInput")).sendKeys("user");
        driver.findElement(By.id("passwordInput")).sendKeys("user");
        driver.findElement(By.id("submitButton")).click();

        String pageTitle = driver.findElement(By.id("pageTitle")).getText();
        assertEquals("Hello world!",pageTitle);

    }

    @Test
    public void LoginTestExpectSuccessV2(){

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        HomePage homePage = loginPage.loginAs("user","user");
        assertEquals("Hello world!", homePage.getPageTitle());

    }

    @Test
    public void loginTestWithInvalidUsernameExpectFailure(){

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage = loginPage.loginAsExpectingError("invalid","user");
        assertEquals("Please sign in",loginPage.getPageTitle());
        assertEquals("Invalid username",loginPage.getErrorMessage());

    }

    @Test
    public void loginTestWithoutUsernameExpectFailure(){

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage = loginPage.loginAsExpectingError("","user");
        assertEquals("Please sign in",loginPage.getPageTitle());

    }

    @Test
    public void loginTestWithInvalidPasswordExpectFailure(){

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage = loginPage.loginAsExpectingError("user","invalid");
        assertEquals("Please sign in",loginPage.getPageTitle());
        assertEquals("Invalid password",loginPage.getErrorMessage());

    }

    @Test
    public void loginTestWithoutPasswordExpectFailure(){

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage = loginPage.loginAsExpectingError("user","");
        assertEquals("Please sign in",loginPage.getPageTitle());

    }

}
