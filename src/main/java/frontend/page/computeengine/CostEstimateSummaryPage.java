package frontend.page.computeengine;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CostEstimateSummaryPage extends AbstractPage {

    @FindBy(xpath = "//*[@class='SeJRAd ZF0dQe']")
    private WebElement cost;

    public CostEstimateSummaryPage(WebDriver driver) {
        super(driver);
    }

    public double getCost() {
        return Double.parseDouble(cost.getText().replace("$", ""));
    }
}
