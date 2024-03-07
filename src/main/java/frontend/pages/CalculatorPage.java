package frontend.pages;

import frontend.components.Dropdown;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static frontend.driver.DriverManager.getDriver;

@Component
public class CalculatorPage extends BasePage {
    private static final String URL = "https://cloud.google.com/products/calculator/";
    private static final String DROPDOWN_FILTER_SORT_BY_ITEM_XPATH = "//*[@role='menuitem']//*[text()='%s']";

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

    @Autowired
    private Dropdown dropdown;

    public CalculatorPage openPage() {
        getDriver().get(URL);
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
        return new EstimateComputeEnginePage();
    }

    public String getPageUrl() {
        return getDriver().getCurrentUrl();
    }

    public List<String> getSectionNameList() {
        return wait.until(ExpectedConditions.visibilityOfAllElements(buttonsOfSection))
                .stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public CalculatorPage selectFilterSortBy(String filter) {
        dropdown.selectDropdown(filterSortBy, DROPDOWN_FILTER_SORT_BY_ITEM_XPATH, filter);
        waitForChangingData(2);
        return this;
    }
}
