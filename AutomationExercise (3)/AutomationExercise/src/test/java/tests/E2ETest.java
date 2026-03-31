package tests;

import base.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class E2ETest extends BaseTest {

    WebDriverWait wait;

    // 🔥 COMMON METHOD (FIXED WITH WAITS)
    public void registerUser() {

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 🔥 ensure page ready
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Signup / Login')]"))).click();

        String randomEmail = "user" + System.currentTimeMillis() + "@test.com";

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@data-qa='signup-name']")))
                .sendKeys("Manoj kumar Settipalli");

        driver.findElement(By.xpath("//input[@data-qa='signup-email']"))
                .sendKeys(randomEmail);

        driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();

        // Account info
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")))
                .sendKeys("Pass123");

        driver.findElement(By.id("id_gender1")).click();

        driver.findElement(By.id("days")).sendKeys("10");
        driver.findElement(By.id("months")).sendKeys("May");
        driver.findElement(By.id("years")).sendKeys("2000");

        driver.findElement(By.id("newsletter")).click();
        driver.findElement(By.id("optin")).click();

        // Address
        driver.findElement(By.id("first_name")).sendKeys("Manoj");
        driver.findElement(By.id("last_name")).sendKeys("Settipalli");
        driver.findElement(By.id("company")).sendKeys("Test Company");
        driver.findElement(By.id("address1")).sendKeys("123 Automation Street");
        driver.findElement(By.id("address2")).sendKeys("Near Tech Park");
        driver.findElement(By.id("country")).sendKeys("India");
        driver.findElement(By.id("state")).sendKeys("Andhra Pradesh");
        driver.findElement(By.id("city")).sendKeys("Vijayawada");
        driver.findElement(By.id("zipcode")).sendKeys("520001");
        driver.findElement(By.id("mobile_number")).sendKeys("9876543210");

        js.executeScript("arguments[0].click();",
                driver.findElement(By.xpath("//button[@data-qa='create-account']")));

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Continue"))).click();
    }

    // ✅ TEST 1
    @Test
    public void testRegistration() {
        registerUser();
        System.out.println("Registration PASSED");
    }

    // ✅ TEST 2
    @Test
    public void testAddToCart() {

        registerUser();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement addToCart = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("(//a[text()='Add to cart'])[1]")));

        js.executeScript("arguments[0].click();", addToCart);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//u[text()='View Cart']"))).click();

        System.out.println("Add to Cart PASSED");
    }

    // ✅ TEST 3
    @Test
    public void testCheckout() {

        registerUser();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement addToCart = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("(//a[text()='Add to cart'])[1]")));

        js.executeScript("arguments[0].click();", addToCart);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//u[text()='View Cart']"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Proceed To Checkout"))).click();

        String address = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("address_delivery"))).getText();

        Assert.assertTrue(address.contains("123 Automation Street"));

        System.out.println("Checkout PASSED");
    }
}