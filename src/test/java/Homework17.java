import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Homework17 extends BaseTest {
        @Test
        public void addSongToPlaylist() throws InterruptedException {

//      Added ChromeOptions argument below to fix websocket error
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");

            WebDriver driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            //  Navigate to login page
            String url = "https://qa.koel.app/";
            driver.get(url);

            //Enter a valid email address
            WebElement emailField=driver.findElement(By.xpath("//input[@type='email']"));
            emailField.clear();
            emailField.sendKeys("arianna.gunn@testpro.io");

            //Enter a valid password
            WebElement passwordField=driver.findElement (By.xpath("//input[@type='password']"));
            passwordField.clear();
            passwordField.sendKeys("Seventeen17");

            //Click the login button
            WebElement loginButton=driver.findElement(By.xpath("//button[@type='submit']"));
            loginButton.click();

            //On the homepage, search for a song
            WebElement songSearch=driver.findElement(By.xpath("//input[@type='search']"));
            songSearch.clear();
            songSearch.sendKeys("beautiful");

            //Click the View All button
           // WebElement viewAllButton=driver.findElement(By.xpath("//button[@data-test='view-all-songs-btn']"));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement viewAllButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"searchExcerptsWrapper\"]/div/div/section[1]/h1/button")));
            viewAllButton.click();

            //Click the first song from the results
            WebElement firstSearchSong = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[@id=\"songResultsWrapper\"]/div/div/div[1]/table/tr[1]")));
            firstSearchSong.click();

            //Click the Add To button
            WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement addToButton=driver.findElement(By.xpath("//button[@data-test='add-to-btn']"));
          addToButton.click();
          Thread.sleep(5000);

            //Add the song to a playlist
            WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement playlistSelect=driver.findElement(By.xpath("(//li[@class='playlist'])[last()]"));
         playlistSelect.click();
Thread.sleep(5000);
            //Verify that a success message displays
            WebElement successMessage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".success.show")));
            String actualMessage = successMessage.getText();
            System.out.println("Success message text: " + actualMessage);
            Assert.assertEquals(actualMessage, "Added 1 song into \"test pro.\"");
Thread.sleep(2000);

            driver.quit();




        }
}

