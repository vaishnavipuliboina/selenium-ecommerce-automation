package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class ProductPage {

    WebDriver driver;
    WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Locators
    By productsLink = By.xpath("//a[@href='/products']");
    By searchBox = By.id("search_product");
    By searchBtn = By.id("submit_search");
    By firstProduct = By.xpath("(//a[contains(@href,'product_details')])[1]");
    By categoryWomen = By.xpath("//a[@href='#Women']");
    By brand = By.xpath("//a[@href='/brand_products/Polo']");
    By reviewName = By.id("name");
    By reviewEmail = By.id("email");
    By reviewText = By.id("review");
    By submitReview = By.id("button-review");
    By testCasesLink = By.xpath("//a[@href='/test_cases']");
    By testCasesHeading = By.xpath("//b[text()='Test Cases']");
 

    // 🔥 HANDLE ADS
    public void handleAds() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            js.executeScript("document.querySelectorAll('iframe').forEach(e => e.remove());");
            js.executeScript("document.querySelectorAll('[id^=\"aswift\"]').forEach(e => e.remove());");
            js.executeScript("document.querySelectorAll('div[id^=\"aswift\"]').forEach(e => e.remove());");

        } catch (Exception e) {
            System.out.println("No ads");
        }
    }

    public void clickProducts() {
        handleAds();
        wait.until(ExpectedConditions.elementToBeClickable(productsLink)).click();
    }

    public boolean isProductsPageDisplayed() {

        handleAds();

        try {
            WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(searchBox)
            );

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

            return element.isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    public void scrollDown() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
    }

 
    public void clickFirstProduct() {

        handleAds();

        WebElement element = wait.until(
            ExpectedConditions.elementToBeClickable(firstProduct)
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void selectCategory() {

        handleAds();

        WebElement element = wait.until(
            ExpectedConditions.elementToBeClickable(categoryWomen)
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void selectBrand() {

        handleAds();

        WebElement element = wait.until(
            ExpectedConditions.elementToBeClickable(brand)
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void addReview(String name, String email, String review) {

        handleAds();

        wait.until(ExpectedConditions.visibilityOfElementLocated(reviewName)).sendKeys(name);
        driver.findElement(reviewEmail).sendKeys(email);
        driver.findElement(reviewText).sendKeys(review);
        driver.findElement(submitReview).click();
    }
    public void clickTestCases() {
        handleAds();
        wait.until(ExpectedConditions.elementToBeClickable(testCasesLink)).click();
    }

    public boolean isTestCasesPageDisplayed() {
        handleAds();
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(testCasesHeading)
        ).isDisplayed();
    }
   
}