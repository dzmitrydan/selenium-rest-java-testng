package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.nio.file.Paths;

public class FirefoxDriverCreator implements WebDriverFactoryMethod {

    @Override
    public WebDriver createWebDriver() {
        WebDriverManager.firefoxdriver().setup();
        String downloadPath = Paths.get("target/download").toFile().getAbsolutePath();
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("browser.download.dir", downloadPath);
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
        //options.setHeadless(true);
        return new FirefoxDriver(options);
    }
}
