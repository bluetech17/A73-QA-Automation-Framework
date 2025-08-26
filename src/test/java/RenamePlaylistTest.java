import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RenamePlaylistTest extends BaseTest {

    @Test
    public void renamePlaylist() {
        WebDriver driver = getDriver();
        WebDriverWait wait = getWait();
        Actions actions = getActions();

        driver.get(url);

        // Login
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys("arianna.gunn@testpro.io");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Seventeen17");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Double-click playlist
        WebElement playlist = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".playlist:nth-child(4)")));
        actions.doubleClick(playlist).perform();

        // Rename playlist
        WebElement playlistInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("input[data-testid='inline-playlist-name-input']")));
        playlistInput.sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
        playlistInput.sendKeys("renameMe!");
        playlistInput.sendKeys(Keys.ENTER);

        // Verify toast
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        String actualToast = toast.getText();
        System.out.println("Toast text: " + actualToast);
        Assert.assertEquals(actualToast, "Updated playlist \"renameMe!.\"");
    }
}
