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
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;



public class Homework18Test extends BaseTest {

    @Test
    public void playSong() throws InterruptedException {



//      Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //  Navigate to login page
        String url = "https://qa.koel.app/";
        driver.get(url);

        //Enter a valid email address
        WebElement emailField = driver.findElement(By.xpath("//input[@type='email']"));
        emailField.clear();
        emailField.sendKeys("arianna.gunn@testpro.io");

        //Enter a valid password
        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
        passwordField.clear();
        passwordField.sendKeys("Seventeen17");

        //Click the login button
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();

        //Click a song from the homepage
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement playThisSong = driver.findElement(By.xpath("//*[@id=\"homeWrapper\"]/div/div[1]/section[1]/ol/li[1]/article/span[1]"));
        playThisSong.click();
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));


        //Verify the pause button displays\\
        //Verify 1a. Hover over the circle image
        WebElement hoverTarget = driver.findElement(By.xpath("//*[@id=\"mainFooter\"]/div[1]"));

       //Verify 1b. Hover over the element to reveal hidden controls
        Actions actions = new Actions(driver);
        actions.moveToElement(hoverTarget).perform();

       // Verify 1c. Wait for the pause button to appear
        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement pauseButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"mainFooter\"]/div[1]/span/span[2]")));
        Assert.assertTrue(pauseButton.isDisplayed(), "The pause button is displayed.");



    }
}
