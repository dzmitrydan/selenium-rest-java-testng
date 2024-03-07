package frontend.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static frontend.driver.DriverManager.getDriver;

public abstract class BasePage {
    protected final int WAIT_TIMEOUT_SECONDS = 10;
    protected final Logger logger;
    protected WebDriverWait wait;
    protected JavascriptExecutor executor;

    protected BasePage() {
        wait = new WebDriverWait(getDriver(), WAIT_TIMEOUT_SECONDS);
        executor = (JavascriptExecutor) getDriver();
        logger = LogManager.getRootLogger();
        PageFactory.initElements(getDriver(), this);
    }

    // Waiting for data from the server
    protected void waitForChangingData(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
