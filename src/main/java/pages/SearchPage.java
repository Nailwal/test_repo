package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.DriverFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SearchPage {
    WebDriver driver;

    public SearchPage() {
        driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    WebDriverWait wait;
    JavascriptExecutor js;

    String expectedcartItems = "1";

    // Locators using @FindBy annotation
    @FindBy(id = "gh-ac")
    private WebElement textFieldSearch;

    // Methods
    public void clickOnFirstBookOption(String name) {
        // wait = new WebDriverWait(driver, 10);
        WebElement searchBox = driver.findElement(By.id("gh-ac")); // Locate the search input box by ID
        searchBox.sendKeys("book");
        searchBox.submit();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> li = driver.findElements(By.xpath("//span[@role='heading']"));
        li.get(2).click();
    }

    public void clickOnAddToCart() {
        js = (JavascriptExecutor) driver;
        String mainWindow = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();

        //checking if child window has other child windows and will fetch the heading of the child window:
        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
            if (!mainWindow.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
                js.executeScript("window.scrollBy(0,500)");
                driver.findElement(By.xpath("(//*[contains(text(),'Add to cart')])[1]")).click();
            }
        }
    }

    public void verifyTheCartValue(){
      WebElement cartVal=  driver.findElement(By.xpath("//i[@id='gh-cart-n']"));
      String actualCartItemsvalue = cartVal.getText();
      //Assert to verify the actual and expected values
        Assert.assertEquals(actualCartItemsvalue, expectedcartItems,"Values of the added cart items are not matching");
      System.out.println(actualCartItemsvalue);
    }
}