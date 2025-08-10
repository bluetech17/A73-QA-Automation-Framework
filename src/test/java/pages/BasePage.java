package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected By WebElement;

    public BasePage(WebDriver givenDriver) {
        this.driver = givenDriver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver);
        PageFactory.initElements(givenDriver, this);
    }

    public WebElement findElement(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}
   // public BasePage(WebDriver givenDriver) {



//    private final By emailField = By.xpath("//input[@type='email']");
//    private final By passwordField = By.xpath("//input[@type='password']");
//    private final By submitButton = By.xpath("//button[@type='submit']");

    //  public pages.LoginPage(WebDriver givenDriver) {
    //    super(givenDriver);
    // }
//    protected void click(WebElement webElement){
//        wait.until(ExpectedConditions.elementToBeClickable(WebElement)).click();
//    }
//protected WebElement findElement(WebElement webElement){
//        return wait.until(ExpectedConditions.visibiltiyOf(webElement));
//}
//protected void contextClick(WebElement webElement){
//   actions.contectClick(findElement(webElement)).perform();


