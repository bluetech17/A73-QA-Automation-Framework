import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddSongToPlaylistTest extends BaseTest {

    @Test
    public void addSongToPlaylist() {
        WebDriver driver = getDriver();
        WebDriverWait wait = getWait();

        driver.get(url);

        // Login
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys("arianna.gunn@testpro.io");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Seventeen17");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Search song
        WebElement songSearch = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search']")));
        songSearch.clear();
        songSearch.sendKeys("beautiful");

        WebElement viewAllButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"searchExcerptsWrapper\"]/div/div/section[1]/h1/button")));
        viewAllButton.click();

        WebElement firstSearchSong = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"songResultsWrapper\"]/div/div/div[1]/table/tr[1]")));
        firstSearchSong.click();

        WebElement addToButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-test='add-to-btn']")));
        addToButton.click();

        WebElement playlistSelect = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//li[@class='playlist'])[last()]")));
        playlistSelect.click();

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".success.show")));
        String actualMessage = successMessage.getText();
        System.out.println("Success message text: " + actualMessage);
        Assert.assertEquals(actualMessage, "Added 1 song into \"test pro.\"");
    }
}
