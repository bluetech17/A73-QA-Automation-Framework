import org.openqa.selenium.interactions.Actions;
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
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.JavascriptExecutor;

public class DeletePlaylistTest extends BaseTest {
    private WebDriver driver;

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    // private final String url = "https://qa.koel.app/";


    @Test
    public void deletePlaylist() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-allow-origins=*");
//
//        driver = new ChromeDriver(options);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriver driver = getDriver();          // Use BaseTest driver
        WebDriverWait wait = getWait();
        Actions actions = getActions();

        navigateToPage(url);

        String expectedPlaylistDeletedToast= "Deleted playlist \"123abc.\"";

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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // Use the specific CSS selector for the OK button
            WebElement okToDeleteBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button[class='ok']")));

            // Ensure the element is in view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", okToDeleteBtn);

            // Wait for any animations to complete
            wait.until(ExpectedConditions.elementToBeClickable(okToDeleteBtn));

            // Try regular click first, then JS click if that fails
            try {
                okToDeleteBtn.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", okToDeleteBtn);
            }

            // Wait for the button to disappear after click
            wait.until(ExpectedConditions.invisibilityOf(okToDeleteBtn));
        } catch (Exception e) {
            System.out.println("Failed to find or click OK button. Dialog content: " +
                    driver.findElement(By.tagName("body")).getText());
            throw e;
        }
    }

    private String getDeletedPlaylistToast() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[class='success show']")
        ));
        System.out.println("Toast text: " + notification.getText());
        return notification.getText();
    }

}
