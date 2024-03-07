package frontend.driver;

import org.openqa.selenium.WebDriver;
import frontend.utils.PropertyFileReader;

import java.util.Objects;

public class DriverManager {
    private static WebDriver driver;

    private DriverManager() {
    }

    public static synchronized WebDriver getDriver() {
        if (null == driver) {
            WebDriverFactory creator;
            String browser = PropertyFileReader.getProperty("environment.browser");
            switch (Objects.requireNonNull(browser)) {
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
