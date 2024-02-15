import driver.DriverFactory
import org.openqa.selenium.WebDriver
import org.testng.Assert
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeTest
import org.testng.annotations.Test
import page.CalculatorPage

class CalculatorSimpleTest {

    private var driver: WebDriver? = null
    private lateinit var calculatorPage: CalculatorPage

    @BeforeTest
    fun setUp() {
        driver = DriverFactory.browser
    }

    @Test
    fun checkUrlPage() {
        calculatorPage = CalculatorPage(driver!!)
        calculatorPage.openPage()
        val actualURL = calculatorPage.getPageUrl()
        Assert.assertEquals(actualURL, "https://cloud.google.com/products/calculator/")
    }

    @Test
    fun checkGreetingTitle() {
        calculatorPage = CalculatorPage(driver!!)
        calculatorPage.openPage()
        val actualGreetingTitleText = calculatorPage.getGreetingTitleText();
        Assert.assertEquals(actualGreetingTitleText, "Welcome to Google Cloud's pricing calculator");
    }

    @AfterTest
    fun tearDown() {
        driver!!.quit()
    }
}