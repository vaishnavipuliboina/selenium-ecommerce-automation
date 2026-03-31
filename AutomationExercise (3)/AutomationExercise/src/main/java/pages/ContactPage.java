package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class ContactPage {

    WebDriver driver;

    public ContactPage(WebDriver driver) {
        this.driver = driver;
    }

    By contactLink = By.xpath("//a[@href='/contact_us']");
    By name = By.name("name");
    By email = By.name("email");
    By subject = By.name("subject");
    By message = By.name("message");
    By upload = By.name("upload_file");
    By submit = By.name("submit");

    public void submitContactForm() throws Exception {

        driver.findElement(contactLink).click();

        driver.findElement(name).sendKeys("Manoj");
        driver.findElement(email).sendKeys("manoj@gmail.com");
        driver.findElement(subject).sendKeys("Testing");
        driver.findElement(message).sendKeys("Automation Test");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(upload));

        // here i used Autoit
        Thread.sleep(2000);
        Runtime.getRuntime().exec("C:\\Users\\DELL\\OneDrive\\Desktop\\upload.exe");
        System.out.println("File upload triggered using AutoIt");
        Thread.sleep(2000);
        

        driver.findElement(submit).click();
    }
    
 
    By productsBtn = By.xpath("//a[contains(text(),'Products')]");
    By searchBox = By.id("search_product");
    By searchBtn = By.id("submit_search");

    public void contactToSearch(String product) {

        driver.findElement(productsBtn).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));

        driver.findElement(searchBox).sendKeys(product);
        driver.findElement(searchBtn).click();
    }
}