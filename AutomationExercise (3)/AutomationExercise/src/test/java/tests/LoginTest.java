package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.loginMain;


import java.time.Duration;

public class LoginTest {

    WebDriver driver;
    loginMain page;
    WebDriverWait wait;

    String email;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
        page = new loginMain(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void testRegisterUser() {
        page.openSignupLogin();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-qa='signup-name']")));
        email = "test" + System.currentTimeMillis() + "@gmail.com";
        page.signup("TestUser", email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        page.registerDetails();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[text()='Account Created!']")));
        Assert.assertTrue(page.isAccountCreated());
        System.out.println("Register User: PASSED");
    }
    @Test
    public void testValidLogin() {
        page.openSignupLogin();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-qa='login-email']")));
        page.login("manojkumar_settipalli@srmap.edu.in", "Manoj@000");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Logged in as')]")));

        Assert.assertTrue(page.isLoggedIn());
        System.out.println("Login Successful: PASSED");
    }
    @Test
    public void testInvalidLogin() {
        page.openSignupLogin();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-qa='login-email']")));
        page.login("wrong@gmail.com", "wrong123");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'incorrect')]")));
        Assert.assertTrue(page.isLoginError());
        System.out.println("Invalid Login: PASSED");
    }
    @Test
    public void testLogout() {

        page.openSignupLogin();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-qa='login-email']")));
        page.login("manojkumar_settipalli@srmap.edu.in", "Manoj@000");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Logged in as')]")));
        page.logout();
        wait.until(ExpectedConditions.urlContains("login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
        System.out.println("Logout: PASSED");
    }
    @Test
    public void testExistingEmail() {

        page.openSignupLogin();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-qa='signup-name']")));
        page.signup("TestUser", "manojkumar_settipalli@srmap.edu.in");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'already exist')]")));
        Assert.assertTrue(page.isExistingEmailError());
        System.out.println("Existing Email Validation: PASSED");
    }
    @Test
    public void testPlaceOrder() {

        page.openSignupLogin();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-qa='login-email']")));
        page.login("manojkumar_settipalli@srmap.edu.in", "Manoj@000");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Logged in as')]")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/products']"))).click();
        WebElement addToCart = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[contains(text(),'Add to cart')])[1]")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCart);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCart);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'View Cart')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Proceed To Checkout')]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("message"))).sendKeys("Test Order");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Place Order')]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name_on_card"))).sendKeys("Test");

        driver.findElement(By.name("card_number")).sendKeys("1234567812345678");
        driver.findElement(By.name("cvc")).sendKeys("123");
        driver.findElement(By.name("expiry_month")).sendKeys("12");
        driver.findElement(By.name("expiry_year")).sendKeys("2025");
        driver.findElement(By.id("submit")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Your order has been placed successfully!')]")));

        Assert.assertTrue(driver.getPageSource()
                .contains("Your order has been placed successfully!"));

        System.out.println("Place Order: PASSED");
    }}