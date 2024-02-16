package frontend;

import org.testng.Assert;
import org.testng.annotations.Test;
import frontend.page.computeengine.CalculatorPage;

public class CalculatorTest extends BaseTest {

    @Test
    public void checkGreetingTitle() {
        String expectedGreetingTitleText = "Welcome to Google Cloud's pricing calculator";
        String actualGreetingTitleText = new CalculatorPage(driver).openPage()
                .getGreetingTitleText();
        Assert.assertEquals(actualGreetingTitleText, expectedGreetingTitleText);
    }

    @Test
    public void checkPageUrl() {
        String expectedUrl = "https://cloud.google.com/products/calculator/";
        String actualUrl = new CalculatorPage(driver).openPage().getPageUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }
}
