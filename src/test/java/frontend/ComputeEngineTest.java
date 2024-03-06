package frontend;

import frontend.pages.CalculatorPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import frontend.pages.EstimateComputeEnginePage;
import frontend.utils.CSVFileReader;

import java.util.List;
import java.util.stream.Collectors;

public class ComputeEngineTest extends BaseTest {

    @Autowired
    private CalculatorPage calculatorPage;

    @Autowired
    private EstimateComputeEnginePage computeEnginePage;

    @DataProvider
    public Object[][] operatingSystem() {
        return new Object[][]{
                {3, "Paid: Ubuntu Pro", "Regular"},
                {2, "Paid: SLES", "Spot (Preemptible VM)"},
                {1, "Paid: Red Hat Enterprise Linux", "Regular"}
        };
    }

    @Test(dataProvider = "operatingSystem")
    public void checkCostFromDownloadCsv(int numberOfInstances, String operatingSystem, String provisioningModel) {
        EstimateComputeEnginePage estimateComputeEnginePage = calculatorPage.openPage()
                .clickAddYoEstimateButton()
                .openComputeEngineSection();
        computeEnginePage.inputNumberOfInstances(numberOfInstances)
                .selectOperatingSystem(operatingSystem)
                .clickProvisioningModelButton(provisioningModel)
                .clickDownloadCsv();
        double actualPrice = CSVFileReader.getPriceFromCsv();
        double expectedPrice = estimateComputeEnginePage.getCost();
        CSVFileReader.deleteAllFilesInDirectory();
        Assert.assertEquals(actualPrice, expectedPrice);
    }

    @Test
    public void checkCostFromCostEstimateSummaryPage() {
        int numberOfInstances = 2;
        String operatingSystem = "Paid: SLES 12 for SAP";
        EstimateComputeEnginePage estimateComputeEnginePage = calculatorPage.openPage()
                .clickAddYoEstimateButton()
                .openComputeEngineSection();
        computeEnginePage.inputNumberOfInstances(numberOfInstances)
                .selectOperatingSystem(operatingSystem);
        double expectedPrice = estimateComputeEnginePage
                .getCost();
        double actualPrice = estimateComputeEnginePage
                .clickOpenDetailedView()
                .getCost();
        Assert.assertEquals(actualPrice, expectedPrice);
    }

    @Test
    public void checkCostChanged() {
        String operatingSystem = "Paid: SLES 12 for SAP";
        calculatorPage.openPage()
                .clickAddYoEstimateButton()
                .openComputeEngineSection();
        boolean costChanged = computeEnginePage.selectOperatingSystem(operatingSystem)
                .isCostChangedTo(386.9);
        Assert.assertTrue(costChanged);
    }

    @Test
    public void checkSectionsSorting() {
        CalculatorPage pageWithSectionNames = calculatorPage.openPage()
                .clickAddYoEstimateButton();
        List<String> listBeforeFilter = pageWithSectionNames.getSectionNameList();
        List<String> listAfterFilter = pageWithSectionNames.selectFilterSortBy("Product name")
                .getSectionNameList();
        Assert.assertEquals(listAfterFilter, listBeforeFilter.stream().sorted().collect(Collectors.toList()));
    }
}
