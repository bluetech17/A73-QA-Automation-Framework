import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import java.time.Duration;
import java.time.Instant;

import org.testng.annotations.AfterMethod;


public class Homework21Test extends BaseTest {
    private WebDriver driver;
private WebDriverWait wait;
private Actions actions;
   

//@AfterMethod
 // public void tearDown() {
   //    if (driver != null) {
      //      driver.quit();
     //  }
   // }



    @Parameters({"baseUrl"})
    @Test
    public void createPlaylist() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Initialize wait and actions
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);

        String expectedPlaylistCreatedToast = "Updated playlist \"renameMe!.\""; //change this text

      //  Actions actions = new Actions(driver);
        String baseUrl = "https://qa.koel.app/";
        navigateToPage (baseUrl);
        enterEmail("arianna.gunn@testpro.io");
        enterPassword("Seventeen17");
        clickSubmit();
        doubleClickSelectedPlaylist();
        enterNewPlaylistName("renameMe!");
        Assert.assertEquals(getCreatedPlaylistToast(), expectedPlaylistCreatedToast);

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


private void doubleClickSelectedPlaylist() {
    WebElement selectedPlaylist = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".playlist:nth-child(4)")
      ));
    actions.doubleClick(selectedPlaylist).perform();
}

private void enterNewPlaylistName(String newPlaylistName) {
    WebElement newPlaylistInput = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("input[data-testid='inline-playlist-name-input']")));
    
    newPlaylistInput.sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
    newPlaylistInput.sendKeys(newPlaylistName);  // Use the parameter here
    newPlaylistInput.sendKeys(Keys.ENTER);
}

    private String getCreatedPlaylistToast() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[class='success show']")
        ));
        System.out.println("Toast text: " + notification.getText());
        return notification.getText();
    }
}