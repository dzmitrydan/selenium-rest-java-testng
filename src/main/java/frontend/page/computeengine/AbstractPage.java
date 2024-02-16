package frontend.page.computeengine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public abstract class AbstractPage {
    protected final int WAIT_TIMEOUT_SECONDS = 10;
    protected final Logger logger;
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor executor;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        executor = (JavascriptExecutor) driver;
        logger = LogManager.getRootLogger();
        PageFactory.initElements(driver, this);
    }

    protected void clickWebElementByJS(WebElement element) {
        executor.executeScript("arguments[0].click();", element);
    }

    protected void waitForChangingData(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
