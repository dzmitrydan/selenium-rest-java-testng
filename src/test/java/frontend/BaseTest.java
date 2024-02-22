package frontend;

import frontend.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import testutility.TestListener;

@Listeners({TestListener.class})
public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeMethod()
    public void setUp(){
        driver = DriverManager.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser(){
        DriverManager.closeDriver();
    }
}
