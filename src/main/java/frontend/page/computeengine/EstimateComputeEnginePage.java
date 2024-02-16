package frontend.page.computeengine;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class EstimateComputeEnginePage extends AbstractPage {
    private String selectOperatingSystemItemXpath = "//span[@class='VfPpkd-rymPhb-fpDzbe-fmcmS' and text()='%s']/../*";
    private String provisioningModelButtonXpath = "//*[text()='%s']";
    private String numberOfInstancesId = "c7";

    @FindBy(xpath = "//div[@class='YgByBe']")
    private List<WebElement> selectOperatingSystem;

    @FindBy(xpath = "//*[text()='Download .csv']/ancestor::span")
    private WebElement downloadCsvButton;

    @FindBy(xpath = "//*[text()='Open detailed view']/ancestor::span")
    private WebElement openDetailedViewButton;

    @FindBy(xpath = "//span[@class='MyvX5d']")
    private WebElement cost;

    @FindBy(xpath = "//*[text()='Service cost updated']")
    private WebElement costUpdatedBanner;

    public EstimateComputeEnginePage(WebDriver driver) {
        super(driver);
    }

    public EstimateComputeEnginePage inputNumberOfInstances(int number) {
        By numberOfInstancesIdBy = new By.ById(numberOfInstancesId);
        wait.until(ExpectedConditions.presenceOfElementLocated(numberOfInstancesIdBy));
        driver.findElement(numberOfInstancesIdBy).clear();
        driver.findElement(numberOfInstancesIdBy).sendKeys(Integer.toString(number));
        waitForChangingData(2);
        return this;
    }

    public EstimateComputeEnginePage selectOperatingSystem(String item) {
        By selectOperatingSystemItemBy = new By.ByXPath(String.format(selectOperatingSystemItemXpath, item));
        clickWebElementByJS(driver.findElement(selectOperatingSystemItemBy));
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
        return new CostEstimateSummaryPage(driver);
    }

    public double getCost() {
        return Double.parseDouble(cost.getText().replace("$", ""));
    }

    public EstimateComputeEnginePage clickProvisioningModelButton(String provisioningModel) {
        String provisioningModelXpath = String.format(provisioningModelButtonXpath, provisioningModel);
        driver.findElement(By.xpath(provisioningModelXpath)).click();
        wait.until(ExpectedConditions.visibilityOf(costUpdatedBanner));
        return this;
    }

    private void waitForChangingData(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
