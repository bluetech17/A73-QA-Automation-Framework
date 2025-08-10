import pages.LoginPage;
import pages.PlayListPage;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class Homework23Test {
    private WebDriver driver;
    private LoginPage loginPage;
    private PlayListPage playlistPage;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        loginPage = new LoginPage(driver);
        playlistPage = new PlayListPage(driver);
    }

    @Parameters({"baseUrl"})
    @Test
    public void createPlaylist(String baseUrl) {
        driver.get(baseUrl);

        loginPage.inputEmail("arianna.gunn@testpro.io")
        .inputPassword("Seventeen17")
        .clickSubmitButton();

        playlistPage.renamePlaylist("renameMe!");

        String actualToast = playlistPage.getToastText();
        String expectedToast = "Updated playlist \"renameMe!.\"";

        Assert.assertEquals(actualToast, expectedToast);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}



