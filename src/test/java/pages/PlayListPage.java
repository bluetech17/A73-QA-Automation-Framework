package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PlayListPage extends BasePage {

    @FindBy(css = ".playlist:nth-child(4)")
    private WebElement playlist;

    @FindBy(css = "input[data-testid='inline-playlist-name-input']")
    private WebElement playlistNameInput;

    @FindBy(css = "div[class='success show']")
    private WebElement toast;

    public PlayListPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void renamePlaylist(String newName) {
        WebElement selectedPlaylist = wait.until(ExpectedConditions.elementToBeClickable(playlist));
        actions.doubleClick(selectedPlaylist).perform();

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(playlistNameInput));
        input.sendKeys(Keys.chord(Keys.CONTROL, "a")); // select all
        input.sendKeys(newName);
        input.sendKeys(Keys.ENTER);
    }

    public String getToastText() {
        return wait.until(ExpectedConditions.visibilityOf(toast)).getText();
    }
}