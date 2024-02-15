package page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class CalculatorPage(driver: WebDriver) {

    private var driver: WebDriver? = null

    @FindBy(xpath = "//h1")
    private var greetingTitle: WebElement? = null

    init {
        this.driver = driver
        PageFactory.initElements(driver, this)
    }

    fun openPage() {
        driver!!.get("https://cloud.google.com/products/calculator/")
    }

    fun getPageUrl(): String? {
        return driver!!.currentUrl
    }

    fun getGreetingTitleText(): String? {
        return greetingTitle!!.text
    }
}