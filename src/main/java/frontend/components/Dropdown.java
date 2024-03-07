package frontend.components;

import frontend.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

@Component
public class Dropdown {

    public void selectDropdown(WebElement dropdown, String dropdownItemXpath, String item) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 10);
        JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();
        By dropdownItemBy = new By.ByXPath(String.format(dropdownItemXpath, item));
        wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownItemBy));
        executor.executeScript("arguments[0].click();", element);
        String dropdownSelectedItem = String.format(".//*[text()='%s']", item);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropdownSelectedItem)));
    }
}
