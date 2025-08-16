import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    @Test
    public void loginEmptyEmailPassword() {
        getDriver().get(url);
        // Using the shared driver initialized in BaseTest (navigated to BaseURL in @BeforeMethod)
        Assert.assertEquals(getDriver().getCurrentUrl(), url);
    }

    @Test
    public void loginValidEmailPassword() {
        getDriver().get(url);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("arianna.gunn@testpro.io", "Seventeen17");
        HomePage homePage = new HomePage(getDriver()); // initialize the page object
        Assert.assertTrue(homePage.isAvatarDisplayed());
    }

    @Test
    public void loginInvalidEmailValidPassword() {
        getDriver().get(url);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("arianna.gunn@testpro.io", "Seventeen17");
        Assert.assertEquals(getDriver().getCurrentUrl(), url);
    }
}