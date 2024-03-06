package frontend;

import frontend.pages.CalculatorPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalculatorTest extends BaseTest {

    @Autowired
    private CalculatorPage calculatorPage;

    @Test
    public void checkGreetingTitle() {
        String expectedGreetingTitleText = "Welcome to Google Cloud's pricing calculator";
        String actualGreetingTitleText = calculatorPage.openPage()
                .getGreetingTitleText();
        Assert.assertEquals(actualGreetingTitleText, expectedGreetingTitleText);
    }

    @Test
    public void checkPageUrl() {
        String expectedUrl = "https://cloud.google.com/products/calculator/";
        String actualUrl = calculatorPage.openPage().getPageUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }
}
