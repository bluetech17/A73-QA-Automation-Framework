package utils;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserFactory {

    public static WebDriver pickBrowser(String browser, String gridUrl) throws MalformedURLException {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            return new RemoteWebDriver(new URL(gridUrl), options);
        }
        throw new IllegalArgumentException("Only Chrome is supported for this assignment");
    }
}

