package driver;

import org.openqa.selenium.WebDriver;
import utility.PropertyFileReader;

import java.util.ResourceBundle;

// Lazy Initialization
public class DriverSingleton {
    private static WebDriver driver;

    private DriverSingleton() {
    }

    // Thread Safe Singleton
    public static synchronized WebDriver getDriver() {
        if (null == driver) {
            WebDriverFactoryMethod creator;
            String browser = PropertyFileReader.getProperty("environment.browser");
            switch (browser) {
                case "firefox": {
                    creator = new FirefoxDriverCreator();
                    break;
                }
                case "chrome": {
                    creator = new ChromeDriverCreator();
                    break;
                }
                default: {
                    creator = new ChromeDriverCreator();
                }
            }
            driver = creator.createWebDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}