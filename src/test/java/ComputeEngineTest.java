import org.testng.Assert;
import org.testng.annotations.Test;
import page.computeengine.CalculatorPage;
import page.computeengine.EstimateComputeEnginePage;
import utility.CSVFileReader;

public class ComputeEngineTest extends BaseTest {

    @Test
    public void checkCostFromDownloadCsv() {
        double expectedPrice = new CalculatorPage(driver).openPage()
                .clickAddYoEstimateButton()
                .openComputeEngineSection()
                .inputNumberOfInstances(3)
                .selectOperatingSystem("Paid: Ubuntu Pro")
                .clickDownloadCsv()
                .getCost();
        double actualPrice = CSVFileReader.getPriceFromCsv();
        Assert.assertEquals(actualPrice, expectedPrice);
    }

    @Test
    public void checkCostFromCostEstimateSummaryPage() {
        EstimateComputeEnginePage estimateComputeEnginePage = new CalculatorPage(driver).openPage()
                .clickAddYoEstimateButton()
                .openComputeEngineSection()
                .inputNumberOfInstances(2)
                .selectOperatingSystem("Paid: SLES 12 for SAP");
        double expectedPrice = estimateComputeEnginePage.getCost();

        double actualPrice = estimateComputeEnginePage
                .clickOpenDetailedView()
                .getCost();
        Assert.assertEquals(actualPrice, expectedPrice);
    }
}
