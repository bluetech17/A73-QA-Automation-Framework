package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;


public class LoginPage extends BasePage {
    @FindBy(xpath = "//input[@type='email']")
    WebElement emailField;
    @FindBy(xpath = "//input[@type='password']")
    WebElement passwordField;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitButton;


    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);

    }

    public LoginPage inputEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public LoginPage inputPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public LoginPage clickSubmitButton() {
        submitButton.click();
        return this;
    }

    public void login(String email, String password) {
        inputEmail(email);
        inputPassword(password);
        clickSubmitButton();
    }
}