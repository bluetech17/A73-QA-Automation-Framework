import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Homework16Test extends BaseTest {
@Test
        public void registrationNavigation() throws InterruptedException {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");

    WebDriver driver = new ChromeDriver(options);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//Step 1:navigate to homepage
String url="https://qa.koel.app/";
driver.get(url);


//Step 2: Click on Registration/forgot password button
WebElement registration=driver.findElement(By.cssSelector("a[href^='registration']"));
registration.click();


//Step 3: Verify that registration page has been opened
    String expectedUrl = "https://qa.koel.app/registration";
    Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
Thread.sleep(5000);

//Step 4: close the browser
    driver.quit();
}
}
