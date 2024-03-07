package frontend;

import frontend.driver.DriverManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import testutility.TestListener;
import frontend.utils.SpringBootApp;

@Listeners({TestListener.class})
@SpringBootTest(classes = SpringBootApp.class)
public abstract class BaseTest extends AbstractTestNGSpringContextTests {

    @BeforeMethod()
    public void setUp() {
        DriverManager.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        DriverManager.closeDriver();
    }
}
