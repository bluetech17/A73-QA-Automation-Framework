package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePreferencesPage extends BasePage {

    @FindBy(css = "img.avatar")
    public WebElement student;

    @FindBy(xpath = "//*[@id='inputProfileCurrentPassword']")
    public WebElement currentPassword;

    @FindBy(xpath = "//*[@id='inputProfileEmail']")
    public WebElement currentEmail;

    @FindBy(xpath = "//*[@id='inputProfileNewPassword']")
    public WebElement newPassword;

    @FindBy(xpath = "//*[@id='profileWrapper']/div/form/div[5]/button")
    public WebElement saveProfileBtn;

    @FindBy(css = "div[class='success show']")
    private WebElement toast;


    public ProfilePreferencesPage(WebDriver givenDriver) {
        super(givenDriver);
        PageFactory.initElements(driver, this);
    }

    public void clickStudentIcon() {
       // WebElement submit = driver.findElement(By.cssSelector("img.avatar"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(student));
        student.click();
    }

    public void enterPassword(String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(currentPassword));
        //WebElement passwordField = driver.findElement(By.xpath("//*[@id='inputProfileCurrentPassword']"));
        currentPassword.clear();
        currentPassword.sendKeys(password);
    }

    public void enterMyEmail(String email) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(currentEmail));
        // WebElement emailField = driver.findElement(By.xpath("//*[@id='inputProfileEmail']"));
        currentEmail.clear();
        currentEmail.sendKeys(email);
    }
    public void enterNewPassword(String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(newPassword));
        newPassword.clear();
        newPassword.sendKeys(password);
    }

    public void clickSaveProfileBtn() {
        WebElement submit = driver.findElement(By.xpath("//*[@id='profileWrapper']/div/form/div[5]/button"));
        submit.click();
    }

    public String getToastText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(toast));
        return toast.getText();
    }
}

