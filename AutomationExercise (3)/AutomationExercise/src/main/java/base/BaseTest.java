package base;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.support.ui.ExpectedConditions;

import driver.DriverFactory;

public class BaseTest {

	public static WebDriver driver; 

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.initDriver();
        driver.get("https://automationexercise.com");

        waitForPageLoad(); // ensure page fully loaded
        handleAds();       // handle ads globally
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    // ✅ Wait for page to fully load
    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
            webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete")
        );
    }

    // ✅ Global Ad Handler
    public void handleAds() {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            Thread.sleep(1000);

            List<WebElement> closeButtons = driver.findElements(By.xpath(
                    "//button[contains(@class,'close') or contains(@id,'close') or text()='X']"
            ));

            if (!closeButtons.isEmpty()) {
                for (WebElement btn : closeButtons) {
                    if (btn.isDisplayed()) {
                        btn.click();
                        System.out.println("Ad closed");
                        break;
                    }
                }
            }

            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//iframe | //div[contains(@class,'overlay')]")
            ));

        } catch (Exception e) {
            System.out.println("No blocking ad");
        }
    }
}