package frontend.pages;

import frontend.components.Dropdown;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static frontend.driver.DriverManager.getDriver;
import static frontend.utils.CustomWait.waitForTextToBe;

@Component
public class EstimateComputeEnginePage extends BasePage {
    private static final String SELECT_OPERATING_SYSTEM_ITEM_XPATH = "//span[@class='VfPpkd-rymPhb-fpDzbe-fmcmS' and text()='%s']/../*";
    private static final String PROVISIONING_MODEL_BUTTON_XPATH = "//*[text()='%s']";
    private static final String NUMBER_OF_INSTANCES_ID = "c11";

    @FindBy(xpath = "//*[@placeholder-id='ucc-24']")
    private WebElement selectOperatingSystemDropdown;

    @FindBy(xpath = "//*[text()='Download .csv']/ancestor::span")
    private WebElement downloadCsvButton;

    @FindBy(xpath = "//*[text()='Open detailed view']/ancestor::span")
    private WebElement openDetailedViewButton;

    @FindBy(xpath = "//span[@class='MyvX5d']")
    private WebElement cost;

    @FindBy(xpath = "//*[text()='Service cost updated']")
    private WebElement costUpdatedBanner;

    @Autowired
    private Dropdown dropdown;

    public EstimateComputeEnginePage inputNumberOfInstances(int number) {
        By numberOfInstancesIdBy = new By.ById(NUMBER_OF_INSTANCES_ID);
        wait.until(ExpectedConditions.presenceOfElementLocated(numberOfInstancesIdBy));
        getDriver().findElement(numberOfInstancesIdBy).clear();
        getDriver().findElement(numberOfInstancesIdBy).sendKeys(Integer.toString(number));
        waitForChangingData(2);
        return this;
    }

    public EstimateComputeEnginePage selectOperatingSystem(String item) {
        dropdown.selectDropdown(selectOperatingSystemDropdown, SELECT_OPERATING_SYSTEM_ITEM_XPATH, item);
        wait.until(ExpectedConditions.visibilityOf(costUpdatedBanner));
        return this;
    }

    public EstimateComputeEnginePage clickDownloadCsv() {
        waitForChangingData(2);
        downloadCsvButton.click();
        waitForChangingData(2);
        return this;
    }

    public CostEstimateSummaryPage clickOpenDetailedView() {
        openDetailedViewButton.click();
        waitForChangingData(1);
        return new CostEstimateSummaryPage();
    }

    public double getCost() {
        waitForChangingData(2);
        return Double.parseDouble(cost.getText().replace("$", ""));
    }

    public EstimateComputeEnginePage clickProvisioningModelButton(String provisioningModel) {
        String provisioningModelXpath = String.format(PROVISIONING_MODEL_BUTTON_XPATH, provisioningModel);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(provisioningModelXpath))).click();
        wait.until(ExpectedConditions.visibilityOf(costUpdatedBanner));
        return this;
    }

    public boolean isCostChangedTo(double checkedCost) {
        waitForTextToBe(getDriver(), cost, String.format("$%.2f", checkedCost), 5);
        return true;
    }
}
