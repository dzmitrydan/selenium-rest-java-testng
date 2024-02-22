package frontend.driver;

import org.openqa.selenium.WebDriver;

public interface WebDriverFactory {
    boolean browserModeHeadless = Boolean.parseBoolean(System.getProperty("browsermode.headless"));

    WebDriver createWebDriver();
}
