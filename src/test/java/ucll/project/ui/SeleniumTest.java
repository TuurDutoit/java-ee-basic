package ucll.project.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ucll.project.ui.pages.HomePage;
import ucll.project.ui.pages.LoginPage;
import ucll.project.ui.pages.SignUpPage;

import static org.junit.Assert.assertEquals;
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

        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        assertEquals("Hello world!",homePage.getPageTitle());

    }

    @Test
    public void visitLoginPageFromHomePageTest(){

        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();
        LoginPage loginPage = homePage.clickLogin();
        assertEquals("Please sign in",loginPage.getPageTitle());
    }

    @Test
    public void visitSignUpPageFromHomePageTest(){

        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();
        SignUpPage signUpPage = homePage.clickSignUp();
        assertEquals("Create an account",signUpPage.getPageTitle());

    }

    @Test
    public void LoginTestExpectSuccess(){
        //v1
        /*driver.get("http://localhost:8080/user/login");
        WebElement userNameInput = driver.findElement(By.id("usernameInput"));
        userNameInput.sendKeys("user");
        WebElement passwordInput = driver.findElement(By.id("passwordInput"));
        passwordInput.sendKeys("user");
        WebElement loginButton = driver.findElement(By.id("loginButton"));
        loginButton.click();

        WebElement pageTitle = driver.findElement(By.id("pageTitle"));
        assertTrue(pageTitle.getText().equals("Hello world!"));*/

        //v2
        /*LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.enterUsername("user");
        loginPage.enterPassword("user");
        HomePage homePage = loginPage.clickLoginButton();
        assertEquals("Hello world!",homePage.getPageTitle());*/

        //v3
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        HomePage homePage = loginPage.loginAs("user","user");
        assertEquals("Hello world!", homePage.getPageTitle());

    }

    @Test
    public void loginTestWithoutUsernameExpectFailure(){

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage = loginPage.loginAsExpectingError("","user");
        assertEquals("Please sign in",loginPage.getPageTitle());

    }

    @Test
    public void loginTestWithInvalidUsernameExpectFailure(){
        //v1
        /*driver.get("http://localhost:8080/user/login");

        WebElement userNameInput = driver.findElement(By.id("usernameInput"));
        userNameInput.sendKeys("qwe");
        WebElement passwordInput = driver.findElement(By.id("passwordInput"));
        passwordInput.sendKeys("user");
        WebElement loginButton = driver.findElement(By.id("loginButton"));
        loginButton.click();

        WebElement pageTitle = driver.findElement(By.id("pageTitle"));
        assertTrue(pageTitle.getText().equals("Please sign in"));

        WebElement errorMessage = driver.findElement(By.id("errorMessage"));
        assertTrue(errorMessage.getText().equals("Invalid username"));*/

        //v2
        /*LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.enterUsername("user123");
        loginPage.enterPassword("user");
        loginPage.clickLoginButton();
        assertEquals("Please sign in",loginPage.getPageTitle());
        assertEquals("Invalid username",loginPage.getErrorMessage());*/

        //v3
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage = loginPage.loginAsExpectingError("invalid","user");
        assertEquals("Please sign in",loginPage.getPageTitle());
        assertEquals("Invalid username",loginPage.getErrorMessage());

    }

    @Test
    public void loginTestWithoutPasswordExpectFailure(){

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage = loginPage.loginAsExpectingError("user","");
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


}
