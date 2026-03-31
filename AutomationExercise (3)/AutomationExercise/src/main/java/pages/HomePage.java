package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Subscription
    By subscriptionBox = By.id("susbscribe_email");
    By subscribeBtn = By.id("subscribe");

    // Search
    By productsBtn = By.xpath("//a[@href='/products']");
    By searchBox = By.id("search_product");
    By searchBtn = By.id("submit_search");

    // Recommended
    By addRecommended = By.xpath("(//a[contains(text(),'Add to cart')])[last()]");

    // Scroll
    By scrollUpArrow = By.id("scrollUp");

    public void subscribe(String email) {
        driver.findElement(subscriptionBox).sendKeys(email);
        driver.findElement(subscribeBtn).click();
    }

    public void searchProduct(String product) {
        driver.findElement(By.id("search_product")).sendKeys(product);
        driver.findElement(By.id("submit_search")).click();
    }

    public void addRecommendedItem() {

        driver.findElement(By.xpath("(//a[contains(text(),'Add to cart')])[last()]")).click();

        driver.findElement(By.xpath("//button[contains(text(),'Continue Shopping')]")).click();
    }
    public void clickScrollUp() {
        driver.findElement(scrollUpArrow).click();
    }
}