package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Instant;

public class PlayListPage extends BasePage {

    private By playlist = By.cssSelector(".playlist:nth-child(4)");
    private By playlistNameInput = By.cssSelector("input[data-testid='inline-playlist-name-input']");
    private By toast = By.cssSelector("div[class='success show']");

    public PlayListPage(WebDriver driver) {
        super(driver);
    }

    public void renamePlaylist(String newName) {
        WebElement selectedPlaylist = wait.until(ExpectedConditions.elementToBeClickable(playlist));
        actions.doubleClick(selectedPlaylist).perform();

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(playlistNameInput));
        input.sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
        input.sendKeys(newName);
        input.sendKeys(Keys.ENTER);
    }

    public String getToastText() {
        return findElement(toast).getText();
    }
}
