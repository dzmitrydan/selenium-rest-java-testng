import org.testng.Assert;
import org.testng.annotations.Test;
import page.computeengine.CalculatorPage;

public class SimpleTest extends BaseTest {

    @Test
    public void checkPageUrl() {
        String expectedUrl = "https://cloud.google.com/products/calculator/";
        String actualUrl = new CalculatorPage(driver).openPage().getPageUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }
}
