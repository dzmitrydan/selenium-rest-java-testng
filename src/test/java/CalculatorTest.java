import org.testng.Assert;
import org.testng.annotations.Test;
import page.computeengine.CalculatorPage;

public class CalculatorTest extends BaseTest {

    @Test
    public void checkGreetingTitle() {
        String expectedGreetingTitleText = "Welcome to Google Cloud's pricing calculator";
        String actualGreetingTitleText = new CalculatorPage(driver).openPage()
                .getGreetingTitleText();
        Assert.assertEquals(actualGreetingTitleText, expectedGreetingTitleText);
    }
}
