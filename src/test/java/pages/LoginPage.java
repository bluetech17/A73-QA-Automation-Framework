package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    private final By emailField = By.xpath("//input[@type='email']");
    private final By passwordField = By.xpath("//input[@type='password']");
    private final By submitButton = By.xpath("//button[@type='submit']");

    public void login(String email, String password) {
        type(emailField, email);
        type(passwordField, password);
        click(submitButton);
    }
}