package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.SearchPage;
import utils.ConfigReader;
import utils.DriverFactory;

public class SearchSteps {
    WebDriver driver;

    SearchPage searchPage  = new SearchPage();

    @Given("Navigate to ebay.com")
    public void navigateToEbay() {
        driver = DriverFactory.getDriver();
        String baseUrl = ConfigReader.getProperty("url");
        driver.get(baseUrl);
    }
    @When("Search for {string} and click on the first option")
    public void clickOnFirstBookOption(String string1) {
         searchPage.clickOnFirstBookOption(string1);
    }
    @And("click on add to cart button")
    public void clickAddToCartButton() {
        searchPage.clickOnAddToCart();

    }
    @Then("verify the added cart items")
    public void verifyTheCartUpdatedValue() {
        searchPage.verifyTheCartValue();
    }
}
