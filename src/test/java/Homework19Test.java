import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import java.time.Duration;

public class Homework19Test extends BaseTest {
    private WebDriver driver;
   // private final String url = "https://qa.koel.app/";

    @Parameters({"baseUrl"})
    @Test
    public void deletePlaylist() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String expectedPlaylistDeletedToast = "Delete the playlist \"123abc\"?\nCANCELOK";


        String baseUrl = "https://qa.koel.app/";
        navigateToPage (baseUrl);
        enterEmail("arianna.gunn@testpro.io");
        enterPassword("Seventeen17");
        clickSubmit();
        clickSelectedPlaylist();
        clickDeleteThisPlaylist();
        clickOkToDeleteBtn();
        Assert.assertEquals(getDeletedPlaylistToast(), expectedPlaylistDeletedToast);
    }

    private void navigateToPage(String baseUrl) {
        driver.get(baseUrl);
    }

    private void enterEmail(String email) {
        WebElement emailField = driver.findElement(By.xpath("//input[@type='email']"));
        emailField.clear();
        emailField.sendKeys(email);
    }

    private void enterPassword(String password) {
        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    private void clickSubmit() {
        WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
        submit.click();
    }

    private void clickSelectedPlaylist() {
        WebElement selectedPlaylist = driver.findElement(By.xpath("//*[@id=\"playlists\"]/ul/li[3]"));
        selectedPlaylist.click();
    }

    private void clickDeleteThisPlaylist() {
        WebElement deleteThisPlaylist = driver.findElement(By.xpath("//*[@id=\"playlistWrapper\"]/header/div[3]/span/button[2]"));
        deleteThisPlaylist.click();
    }

    private void clickOkToDeleteBtn() {
        WebElement okToDeleteBtn = driver.findElement(By.xpath("/html/body/div[3]/div/div/nav/button[2]"));
       okToDeleteBtn.click();
    }

    private String getDeletedPlaylistToast() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html/body/div[2]")));

        return notification.getText();


    }
}