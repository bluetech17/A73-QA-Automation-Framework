import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected String url;

    @BeforeSuite
    static void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    @Parameters({"BaseURL"})
    public void launchWeb(String BaseURL) throws MalformedURLException {
        url = BaseURL;

        // Always use Chrome with Grid
        driver = pickBrowser("grid-chrome");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        navigateToPage();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    public void navigateToPage() {
        driver.get(url);
    }

    public static WebDriver pickBrowser(String browser) throws MalformedURLException {
        String gridURL = "http://172.20.2.207:4444";

        if ("grid-chrome".equalsIgnoreCase(browser)) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browserName", "chrome");
            ChromeOptions options = new ChromeOptions();
            options.merge(caps);
            return new RemoteWebDriver(URI.create(gridURL).toURL(), options);
        } else {
            throw new IllegalArgumentException("Only grid-chrome is supported in this test");
        }
    }
}
