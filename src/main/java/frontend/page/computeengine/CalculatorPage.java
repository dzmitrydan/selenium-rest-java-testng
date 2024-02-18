package frontend.page.computeengine;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class CalculatorPage extends AbstractPage {
    private String dropdownFilterSortByItemXpath = "//*[@role='menuitem']//*[text()='%s']";

    @FindBy(xpath = "//*[text()='Add to estimate']")
    private WebElement addToEstimateButton;

    @FindBy(xpath = "//h2[text()='Compute Engine']")
    private WebElement buttonComputeEngineSection;

    @FindBy(xpath = "//h1")
    private WebElement greetingTitle;

    @FindBy(xpath = "//*[@class='honxjf']")
    private List<WebElement> buttonsOfSection;

    @FindBy(xpath = "//*[@aria-label='Sort by']")
    private WebElement filterSortBy;

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

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public List<String> getSectionNameList() {
        return wait.until(ExpectedConditions.visibilityOfAllElements(buttonsOfSection))
                .stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public CalculatorPage selectFilterSortBy(String filter) {
        selectDropdown(filterSortBy, dropdownFilterSortByItemXpath, filter);
        waitForChangingData(2);
        return this;
    }
}
