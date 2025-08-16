import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class BaseTest {

    private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
    public String url = null;

    public static WebDriver getDriver() {
        return threadDriver.get();
    }

    @BeforeMethod
    @Parameters({"BaseURL"})
    public void setUpBrowser(@Optional("https://qa.koel.app/") String BaseURL) throws MalformedURLException {
        url = BaseURL;
        String browser = System.getProperty("browser", "chrome");
        WebDriver driver = pickBrowser(browser);
        threadDriver.set(driver);

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        navigateToPage();
    }

    public WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridURL = "http://<GRID_HOST>:<PORT>/wd/hub";

        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();

            case "MicrosoftEdge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                return new EdgeDriver(edgeOptions);

            case "grid-firefox":
                caps.setCapability("browserName", "firefox");
                return new RemoteWebDriver(URI.create(gridURL).toURL(), caps);

            case "grid-edge":
                caps.setCapability("browserName", "MicrosoftEdge");
                return new RemoteWebDriver(URI.create(gridURL).toURL(), caps);

            case "grid-chrome":
                caps.setCapability("browserName", "chrome");
                return new RemoteWebDriver(URI.create(gridURL).toURL(), caps);

            case "cloud":
                return lambdaTest();

            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                return new ChromeDriver(chromeOptions);
        }
    }

    private void navigateToPage() {
        if (getDriver() != null && url != null && !url.isBlank()) {
            getDriver().get(url);
        }
    }

    private WebDriver lambdaTest() throws MalformedURLException {
        String hubURL = "https://hub.lambdatest.com/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "latest");

        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("username", "ariannagunn17"); // ideally use env vars
        ltOptions.put("accessKey", "LT_IkSAv6xXr4Ynam4NULyVY7v5FhaRG2ixDy3TJm7GnmNrrMl");
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("video", true);
        ltOptions.put("build", "Gradle Build");
        ltOptions.put("name", "LoginTests");
        ltOptions.put("selenium_version", "4.0.0");
        ltOptions.put("w3c", true);

        capabilities.setCapability("LT:Options", ltOptions);

        RemoteWebDriver driver= new RemoteWebDriver(new URL(hubURL), capabilities);
    driver.get(url);
        System.out.println(">>> LambdaTest session started!");
        return driver;
    }

    @AfterMethod
    public void tearItDown() {
        try {
            if (getDriver() != null) {
                getDriver().quit();
            }
        } finally {
            threadDriver.remove();
        }
    }
}
