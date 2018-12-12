package ucll.project.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ucll.project.ui.pages.HomePage;
import ucll.project.ui.pages.LoginPage;
import ucll.project.ui.pages.SignUpPage;

import static org.junit.Assert.assertEquals;

public class HomePageTest {

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

}
