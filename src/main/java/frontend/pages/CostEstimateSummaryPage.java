package frontend.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class CostEstimateSummaryPage extends BasePage {

    @FindBy(xpath = "//*[@class='SeJRAd ZF0dQe']")
    private WebElement cost;

    public CostEstimateSummaryPage() {
        super();
    }

    public double getCost() {
        waitForChangingData(2);
        return Double.parseDouble(cost.getText().replace("$", ""));
    }
}
