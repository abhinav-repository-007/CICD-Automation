package rahulshettyacademy.TestComponents;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import rahulshettyacademy.pageObjects.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LandingPage landingPage;


    public WebDriver initializeDriver() throws IOException {

        Properties properties = new Properties();
        FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\rahulshettyacademy\\Resource\\GlobalData.properties");
        properties.load(ip);
        // String browser = (String) properties.get("browser");
        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : (String) properties.get("browser");
        ChromeOptions options = new ChromeOptions();
        if (browser.contains("chrome")) {
            if (browser.contains("headless")) {
                options.addArguments("--headless");
            }
            WebDriverManager.chromedriver().setup();

            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\eclipse-workspace\\SeleniumFrameworkDesign\\drivers\\chromedriver-win64\\chromedriver.exe");

            driver = new ChromeDriver(options);
driver.manage().window().setSize(new Dimension(1440,900));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
        }
        if (browser.equalsIgnoreCase("firefox")) {
            System.out.println("firefox....");
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
        } else {
            System.out.println("edge");
        }
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        //extent.flush();
        driver.close();

    }

    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File dest = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
        FileUtils.copyFile(source, dest);
        return System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";

    }
}
