package ucll.project.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ucll.project.domain.user.Gender;
import ucll.project.domain.user.Role;
import ucll.project.domain.user.User;
import ucll.project.domain.user.UserRepositoryMemory;
import ucll.project.ui.pages.HomePage;
import ucll.project.ui.pages.LoginPage;
import ucll.project.ui.pages.SignUpPage;

import static org.junit.Assert.assertEquals;

public class UserTest {

    private static WebDriver driver;
    private static User user;
    private static String password;

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

    @Before
    public void generateUser(){
        user = new User();

        String userName = RandomStringUtils.random(5,true,false);
        password = RandomStringUtils.random(5,true,false).toLowerCase();

        user.setEmail(userName+"@email.com");
        user.setUserName(userName);
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setGender(Gender.MALE);
        user.setRole(Role.USER);

        user = new User(userName,"firstName","lastName",userName+"@email.com",Gender.MALE,Role.USER);
        user.hashAndSetPassword(password);

        System.out.println(user.getUserName());
        System.out.println(password);
    }

    @After
    public void deleteUser(){
        /*
        * TODO delete user
        */
    }

    @Test
    public void userSmokeTest(){
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.navigateTo();
        signUpPage.enterEmail(user.getEmail());
        signUpPage.enterUsername(user.getUserName());
        signUpPage.enterFirstName(user.getFirstName());
        signUpPage.enterLastName(user.getLastName());
        signUpPage.selectGender(user.getGender());
        signUpPage.selectRole(user.getRole());
        signUpPage.enterDateOfBirth("01/01/2000");
        signUpPage.enterPassword(password);
        signUpPage.enterRepeatPassword(password);

        LoginPage loginPage = signUpPage.clickSubmitButton();

//        HomePage homePage = loginPage.loginAs(user.getUserName(),password);
//        assertEquals("Logout, "+ user.getUserName(), homePage.getLogoutLinkText());
//        assertEquals("Logout, "+ user.getUserName(), homePage.getLogoutLinkText());
//
//        homePage = homePage.clickLogoutLink();
//        assertEquals("Hello world!",homePage.getPageTitle());
    }


}
