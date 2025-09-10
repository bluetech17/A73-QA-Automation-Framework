


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
        import pages.LoginPage;
import pages.ProfilePreferencesPage;


import java.time.Duration;


public class UpdateEmailTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProfilePreferencesPage profilePreferencesPage;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        loginPage = new LoginPage(driver);
        profilePreferencesPage = new ProfilePreferencesPage(driver);
    }

    @Parameters({"baseUrl"})
    @Test
    public void updateEmailPreferenceTest(String baseUrl) {
        driver.get(baseUrl);

        loginPage.login("arianna.gunn@testpro.io", "Seventeen17");

        profilePreferencesPage.student.click();
        profilePreferencesPage.enterPassword("Seventeen17");
        profilePreferencesPage.enterNewEmail("ariannagunn17@gmail.com");
        profilePreferencesPage.saveProfileBtn.click();


        String actualToast = profilePreferencesPage.getToastText();
        String expectedToast = "Profile updated.";

        Assert.assertEquals(actualToast, expectedToast);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}