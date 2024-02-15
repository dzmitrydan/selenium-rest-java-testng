package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ChromeDriverCreator implements WebDriverFactoryMethod {

    @Override
    public WebDriver createWebDriver() {
        WebDriverManager.chromedriver().setup();
        String downloadPath = Paths.get("target/download").toFile().getAbsolutePath();
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadPath);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        return new ChromeDriver(options);
    }
}
