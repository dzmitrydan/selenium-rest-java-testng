package page.computeengine;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CalculatorPage extends AbstractPage {

    @FindBy(xpath = "//*[text()='Add to estimate']")
    private WebElement addToEstimateButton;

    @FindBy(xpath = "//h2[text()='Compute Engine']")
    private WebElement buttonComputeEngineSection;

    @FindBy(xpath = "//h1")
    private WebElement greetingTitle;

    public CalculatorPage(WebDriver driver) {
        super(driver);
    }

    public CalculatorPage openPage() {
        String url = "https://cloud.google.com/products/calculator/";
        driver.get(url);
        return this;
    }

    public CalculatorPage clickAddYoEstimateButton() {
        addToEstimateButton.click();
        return this;
    }

    public String getGreetingTitleText() {
        return greetingTitle.getText();
    }

    public EstimateComputeEnginePage openComputeEngineSection() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonComputeEngineSection)).click();
        wait.until(ExpectedConditions.titleIs("Google Cloud Pricing Calculator"));
        return new EstimateComputeEnginePage(driver);
    }
}
